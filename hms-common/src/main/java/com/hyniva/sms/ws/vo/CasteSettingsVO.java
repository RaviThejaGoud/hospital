package com.hyniva.sms.ws.vo;

import java.util.ArrayList;
import java.util.List;

import com.churchgroup.util.object.ObjectFunctions;


public class CasteSettingsVO {
	
	private long id;
	private String casteName;
	private String subCasteName;
    protected List<SubCasteSettingsVO> subCasteSettingsVOList;
	
	

	public List<SubCasteSettingsVO> getSubCasteSettingsVOList() {
		if(ObjectFunctions.isNullOrEmpty(this.subCasteSettingsVOList))
		{
			this.subCasteSettingsVOList = new ArrayList<SubCasteSettingsVO>(); 
		}
		return subCasteSettingsVOList;
	}

	public void setSubCasteSettingsVOList( List<SubCasteSettingsVO> subCasteSettingsVOList) {
		this.subCasteSettingsVOList = subCasteSettingsVOList;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getSubCasteName() {
		return subCasteName;
	}
	public void setSubCasteName(String subCasteName) {
		this.subCasteName = subCasteName;
	}
	

	 

}
