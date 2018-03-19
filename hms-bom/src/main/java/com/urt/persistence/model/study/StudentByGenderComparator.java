package com.urt.persistence.model.study;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

public class StudentByGenderComparator implements Comparator {

	private static final Log log = LogFactory.getLog(StudentByGenderComparator.class);
    public int compare(Object studyClass1, Object studyClass2) {
    	ViewStudentClassDetails stud1=(ViewStudentClassDetails)studyClass1;
    	ViewStudentClassDetails stud2=(ViewStudentClassDetails)studyClass2;
    	if (!ObjectFunctions.isNullOrEmpty(stud1) && !ObjectFunctions.isNullOrEmpty(stud2))
        {
    		if(stud1.getClassSectionId() == stud2.getClassSectionId()){
    			if(StringFunctions.isNotNullOrEmpty(stud1.getGender()) && StringFunctions.isNotNullOrEmpty(stud2.getGender())){
    				log.debug(stud1.getGender());
    				return stud1.getGender().compareToIgnoreCase(stud2.getGender());
    			}/*else
    				return stud1.getStudentName().compareToIgnoreCase(stud2.getStudentName());*/
    		}
        }
        return 0;
	
    }
}