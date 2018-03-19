package com.urt.persistence.model.study;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.churchgroup.util.object.ObjectFunctions;
import com.urt.persistence.model.common.VWStudentClassAssignment;

public class StudentRollNumberComparator implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		if((o1 instanceof ViewStudentPersonAccountDetails) && (o2 instanceof ViewStudentPersonAccountDetails)){
			ViewStudentPersonAccountDetails vw1 = (ViewStudentPersonAccountDetails)o1;
			ViewStudentPersonAccountDetails vw2 = (ViewStudentPersonAccountDetails)o2;
			if(!ObjectFunctions.isNullOrEmpty(vw1.getRollNumber()) && !ObjectFunctions.isNullOrEmpty(vw2.getRollNumber()) && StringUtils.isNumeric(vw1.getRollNumber()) && StringUtils.isNumeric(vw2.getRollNumber())){
				return (int)(Long.valueOf(vw1.getRollNumber())-Long.valueOf(vw2.getRollNumber()));
			}
			return 1;
		}
		else if((o1 instanceof ViewStudentClassDetails) && (o2 instanceof ViewStudentClassDetails)){
			ViewStudentClassDetails vw1 = (ViewStudentClassDetails)o1;
			ViewStudentClassDetails vw2 = (ViewStudentClassDetails)o2;
			if(!ObjectFunctions.isNullOrEmpty(vw1.getRollNumber()) && !ObjectFunctions.isNullOrEmpty(vw2.getRollNumber()) && StringUtils.isNumeric(vw1.getRollNumber()) && StringUtils.isNumeric(vw2.getRollNumber())){
				return (int)(Long.valueOf(vw1.getRollNumber())-Long.valueOf(vw2.getRollNumber()));
			}
			return 1;
		}
		else if((o1 instanceof VWStudentClassAssignment) && (o2 instanceof VWStudentClassAssignment)){
			VWStudentClassAssignment vw1 = (VWStudentClassAssignment)o1;
			VWStudentClassAssignment vw2 = (VWStudentClassAssignment)o2;
			if(!ObjectFunctions.isNullOrEmpty(vw1.getRollNumber()) && !ObjectFunctions.isNullOrEmpty(vw2.getRollNumber()) && StringUtils.isNumeric(vw1.getRollNumber()) && StringUtils.isNumeric(vw2.getRollNumber())){
				return (int)(Long.valueOf(vw1.getRollNumber())-Long.valueOf(vw2.getRollNumber()));
			}
			return 1;
		}else if((o1 instanceof Student) && (o2 instanceof Student)){
			Student vw1 = (Student)o1;
			Student vw2 = (Student)o2;
			if(!ObjectFunctions.isNullOrEmpty(vw1.getRollNumber()) && !ObjectFunctions.isNullOrEmpty(vw2.getRollNumber()) && StringUtils.isNumeric(vw1.getRollNumber()) && StringUtils.isNumeric(vw2.getRollNumber())){
				return (int)(Long.valueOf(vw1.getRollNumber())-Long.valueOf(vw2.getRollNumber()));
			}
			return 1;
		}else if((o1 instanceof Object)&& (o2 instanceof Object)){
			Object[] obj1 = (Object[])o1;
			Object[] obj2 = (Object[])o2;
			if(!ObjectFunctions.isNullOrEmpty(obj1[3].toString()) && !ObjectFunctions.isNullOrEmpty(obj2[3].toString()) && StringUtils.isNumeric(obj1[3].toString()) && StringUtils.isNumeric(obj2[3].toString())){
				return (int) (Long.valueOf((obj1[3].toString()))-(Long.valueOf(obj2[3].toString())));
			} 
			return 1;    	
		}
		return 0;
	}
	}
