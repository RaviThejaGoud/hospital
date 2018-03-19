package com.urt.persistence.model.study;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;

public class StudyClassClassNameComparator implements Comparator {

    @Override
	public int compare(Object studyClass1, Object studyClass2) {
    	StudyClass class1=(StudyClass)studyClass1;
		StudyClass class2=(StudyClass)studyClass2;
    	if (!ObjectFunctions.isNullOrEmpty(class1) && !ObjectFunctions.isNullOrEmpty(class2))
        {
    		if(class1.getClassNameClassId().getId() < class2.getClassNameClassId().getId())
    				return 1;
    		else if(class1.getClassNameClassId().getId() == class2.getClassNameClassId().getId() && class1.getSection().compareTo(class2.getSection()) < 0){
    			//if(class1.getSection().compareTo(class2.getSection()) < 0)
    				return 1;
    		}
        }
		return 0;
	
    }
}