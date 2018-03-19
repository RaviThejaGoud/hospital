package com.urt.util.excel.parser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;

import com.churchgroup.util.string.StringFunctions;

public class SheetParser {
	private HSSFHelper hssfHelper;
	private Map<String, Map<Integer, Field>> excelMapCache;
	private static final Logger LOGGER = Logger.getLogger(SheetParser.class);

	public SheetParser() {
		hssfHelper = new HSSFHelper();
		excelMapCache = new HashMap<String, Map<Integer, Field>>();
	}

	public <T> List<T> createEntity(Sheet sheet, String sheetName,
			Class<T> clazz,int curr, int end) throws ExcelParsingException {
		List<T> list = new ArrayList<T>();
		ExcelObject excelObject = getExcelObject(clazz);
		for (int currentLocation = curr; currentLocation <= end; currentLocation++) {
			T object = getNewInstance(sheet, sheetName, clazz,
					excelObject.parseType(), currentLocation,
					excelObject.zeroIfNull());
			List<Field> mappedExcelFields = getMappedExcelObjects(clazz);
			for (Field mappedField : mappedExcelFields) {
				Class<?> fieldType = mappedField.getType();
				List<?> fieldValue = createEntity(
						sheet,
						sheetName,
						fieldType.equals(Object.class) ? getFieldType(mappedField)
								: fieldType, curr, end);
				if (fieldType.equals(Object.class)) {
					setFieldValue(mappedField, object, fieldValue);
				} else if (!fieldValue.isEmpty()) {
					setFieldValue(mappedField, object, fieldValue.get(0));
				}
			}
			list.add(object);
		}
		return list;
	}

    public <T> List<T> createEntity(Sheet sheet, String sheetName, Class<T> clazz) throws ExcelParsingException {
	List<T> list = new ArrayList<T>();
	ExcelObject excelObject = getExcelObject(clazz);
	int end = sheet.getLastRowNum() + 1;

	for (int currentLocation = excelObject.start(); currentLocation <= end; currentLocation++) {
	    T object = getNewInstance(sheet, sheetName, clazz, excelObject.parseType(), currentLocation,
		    excelObject.zeroIfNull());
	    List<Field> mappedExcelFields = getMappedExcelObjects(clazz);
	    for (Field mappedField : mappedExcelFields) {
		Class<?> fieldType = mappedField.getType();
		List<?> fieldValue = createEntity(sheet, sheetName,
			fieldType.equals(Object.class) ? getFieldType(mappedField) : fieldType, currentLocation,
			currentLocation);
		if (fieldType.equals(Object.class)) {
		    setFieldValue(mappedField, object, fieldValue);
		} else if (!fieldValue.isEmpty()) {
		    setFieldValue(mappedField, object, fieldValue.get(0));
		}
	    }
	    if (!StringFunctions.isNullOrEmpty(object.toString())) {
		// log.debug("Current Row : " + currentLocation +
		// object.toString());
		list.add(object);
	    } /*else {
		 log.debug(" ********Object is Not added ******: " +
		 currentLocation);
	    }*/
	}
	return list;
    }

	private Class<?> getFieldType(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			return (Class<?>) pt.getActualTypeArguments()[0];
		}

		return null;
	}

	private <T> List<Field> getMappedExcelObjects(Class<T> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			MappedExcelObject mappedExcelObject = field
					.getAnnotation(MappedExcelObject.class);
			if (mappedExcelObject != null) {
				field.setAccessible(true);
				fieldList.add(field);
			}
		}
		return fieldList;
	}

	private <T> ExcelObject getExcelObject(Class<T> clazz)
			throws ExcelParsingException {
		ExcelObject excelObject = clazz.getAnnotation(ExcelObject.class);
		if (excelObject == null) {
			throw new ExcelParsingException(
					"Invalid class configuration - ExcelObject annotation missing - "
							+ clazz.getSimpleName());
		}
		return excelObject;
	}

	private <T> T getNewInstance(Sheet sheet, String sheetName, Class<T> clazz,
			ParseType parseType, Integer currentLocation, boolean zeroIfNull)
			throws ExcelParsingException {

		T object = getInstance(clazz);
		Map<Integer, Field> excelPositionMap = getExcelFieldPositionMap(clazz);
		for (Integer position : excelPositionMap.keySet()) {
			Field field = excelPositionMap.get(position);
			Object cellValue;
			if (ParseType.ROW == parseType) {
				cellValue = hssfHelper.getCellValue(sheet, sheetName,
						field.getType(), currentLocation, position, zeroIfNull);
			} else {
				cellValue = hssfHelper.getCellValue(sheet, sheetName,
						field.getType(), position, currentLocation, zeroIfNull);
			}
			setFieldValue(field, object, cellValue);
		}

		return object;
	}

	private <T> T getInstance(Class<T> clazz) throws ExcelParsingException {
		T object = null;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new ExcelParsingException(
					"Exception occured while instantiating the class "
							+ clazz.getName(), e);
		}
		return object;
	}

	private <T> void setFieldValue(Field field, T object, Object cellValue)
			throws ExcelParsingException {
		try {
			field.set(object, cellValue);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelParsingException(
					"Exception occured while setting field value ", e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelParsingException(
					"Exception occured while setting field value ", e);
		}
	}

	private <T> Map<Integer, Field> getExcelFieldPositionMap(Class<T> clazz) {
		Map<Integer, Field> existingMap = excelMapCache.get(clazz.getName());
		return existingMap == null ? loadCache(clazz) : existingMap;
	}

	/**
	 * Load cached for the given class.
	 * 
	 * @param clazz
	 *            Class object to investigate.
	 * @return Map.
	 */
	private <T> Map<Integer, Field> loadCache(Class<T> clazz) {
		Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelField excelField = field.getAnnotation(ExcelField.class);
			if (excelField != null) {
				field.setAccessible(true);
				fieldMap.put(excelField.position(), field);
			}
		}
		excelMapCache.put(clazz.getName(), fieldMap);
		return fieldMap;
	}
}