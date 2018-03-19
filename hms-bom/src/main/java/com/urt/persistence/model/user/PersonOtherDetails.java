package com.urt.persistence.model.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hyniva.sms.ws.vo.PersonOtherDetailsVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.ParseType;

	
	@Entity
	@Table(name = "PersonOtherDetails")
	@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
	public class PersonOtherDetails extends PersistentObject {

	     private static final long serialVersionUID = 1L;
	     
	    private String noOfDependents;
	  	private String scstCommunity;
	  	private String motherEmail;
	  	private String siblingName1;
	  	private String siblingName2;
	  	private String siblingName3;

	  	
	  	public String getNoOfDependents() {
			return noOfDependents;
		}
		public void setNoOfDependents(String noOfDependents) {
			this.noOfDependents = noOfDependents;
		}
		public String getScstCommunity() {
			return scstCommunity;
		}
		public void setScstCommunity(String scstCommunity) {
			this.scstCommunity = scstCommunity;
		}
		public String getMotherEmail() {
			return motherEmail;
		}
		public void setMotherEmail(String motherEmail) {
			this.motherEmail = motherEmail;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "";
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
		public int compareTo(Object object) {
			// TODO Auto-generated method stub
			return 0;
		}
		public String getSiblingName1() {
			return siblingName1;
		}
		public void setSiblingName1(String siblingName1) {
			this.siblingName1 = siblingName1;
		}
		public String getSiblingName2() {
			return siblingName2;
		}
		public void setSiblingName2(String siblingName2) {
			this.siblingName2 = siblingName2;
		}
		public String getSiblingName3() {
			return siblingName3;
		}
		public void setSiblingName3(String siblingName3) {
			this.siblingName3 = siblingName3;
		}
		public PersonOtherDetails deepCopyVoToEntity(
				PersonOtherDetailsVO personOtherDetailsVO) {

			this.noOfDependents = personOtherDetailsVO.getNoOfDependents();
			this.scstCommunity = personOtherDetailsVO.getScstCommunity();
			this.motherEmail = personOtherDetailsVO.getMotherEmail();
			this.siblingName1 = personOtherDetailsVO.getSiblingName1();
			this.siblingName2 = personOtherDetailsVO.getSiblingName2();
			this.siblingName3 = personOtherDetailsVO.getSiblingName3();
			return this;
		}

}
