package com.urt.persistence.model.study;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;

public class StudentStudyClassIdComparator implements Comparator {

    public int compare(Object studyClass1, Object studyClass2) {
    	Student stud1=(Student)studyClass1;
    	Student stud2=(Student)studyClass2;
    	if (!ObjectFunctions.isNullOrEmpty(stud1) && !ObjectFunctions.isNullOrEmpty(stud2))
        {
    		/*if(stud1.getClassSectionId() == stud2.getClassSectionId()){
    			if(StringFunctions.isNotNullOrEmpty(stud1.getRegisterNumber()) && StringFunctions.isNotNullOrEmpty(stud2.getRegisterNumber())){
    				return stud1.getRegisterNumber().compareToIgnoreCase(stud2.getRegisterNumber());
    			}else
    				return stud1.getStudentName().compareToIgnoreCase(stud2.getStudentName());
    		}*/
    		
    		//log.debug(Long.compare(stud1.getClassSectionId(), stud2.getClassSectionId()));
    		return (int)Long.compare(stud1.getClassSectionId(), stud2.getClassSectionId());
    		//return (int) (stud2.getClassSectionId() - stud1.getClassSectionId());
        }
		return 1;
	
    }
}