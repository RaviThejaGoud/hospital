package com.urt.persistence.model.common;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.urt.persistence.model.base.PersistentObject;

/*
 * @create new table Syllabus.
 */
/**
 * @author urt
 *
 */
@Entity
@Table(name = "tcSettings")
public class TcSettings  extends PersistentObject {
    private static final long serialVersionUID = 3832626162173359411L;
	
    

    private String fileName;
    private long custId;
    //private long academicYearId;
    /*private String fontName;
    private String fontFileName;*/
    private String classNames;
    //private List<TCTemplateFonts> tcTemplateFonts;
    private String orderClause;
    private String type;
    private String filePath;

    
    
    
	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	public String getClassNames() {
		return classNames;
	}

	
	public String getOrderClause() {
		return orderClause;
	}


	public void setOrderClause(String orderClause) {
		this.orderClause = orderClause;
	}


	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/*public String getFontName() {
		return fontName;
	}


	public void setFontName(String fontName) {
		this.fontName = fontName;
	}


	public String getFontFileName() {
		return fontFileName;
	}


	public void setFontFileName(String fontFileName) {
		this.fontFileName = fontFileName;
	}*/

	public TcSettings() {
        
    }
    public TcSettings(long id) {
        setId(id);
    }
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public int compareTo(Object object) {
		TcSettings days = (TcSettings) object;
        return new CompareToBuilder().append(this.getId(), days.getId()).toComparison();
	}

}

