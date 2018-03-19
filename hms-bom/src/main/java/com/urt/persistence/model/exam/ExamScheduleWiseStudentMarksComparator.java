package com.urt.persistence.model.exam;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

public class ExamScheduleWiseStudentMarksComparator implements Comparator{

   

    public int compare(Object emp1, Object emp2){
    	ViewExamScheduleWiseStudentMarks subject1 = (ViewExamScheduleWiseStudentMarks)emp1;        
    	ViewExamScheduleWiseStudentMarks subject2 = (ViewExamScheduleWiseStudentMarks)emp2;
		if(!ObjectFunctions.isNullOrEmpty(subject1) && !ObjectFunctions.isNullOrEmpty(subject2)){
			if(subject1.getSortingOrder() > 0 && subject2.getSortingOrder() > 0){
				if(subject1.getSortingOrder() > subject2.getSortingOrder())
					return 1;
				else if(subject1.getSortingOrder() == subject2.getSortingOrder()){
					if(subject1.getSubTypeId() >= subject2.getSubTypeId())
						return 1;
					else 
						return 0;
				}else
					return 0;
			}else if(StringFunctions.isNotNullOrEmpty(subject1.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(subject2.getSubjectNumber()))
				return subject1.getSubjectNumber().compareToIgnoreCase(subject2.getSubjectNumber());
			else
				return subject1.getSubjectName().compareToIgnoreCase(subject2.getSubjectName());			
		}else
			return 0;
        /*Date examDate1 = ((ViewClassExamDetails)emp1).getExamDate();        

        Date examDate2 = ((ViewClassExamDetails)emp2).getExamDate();
        
        boolean isBefore=false;
        int difference=0;
        if (examDate1!= null && examDate2!= null)
        {
        	isBefore = examDate1.before(examDate2);
        }
        if(isBefore)
        {
            difference = 0;
        }
        else
        {
            difference = 1;
        }
        return difference;   */     
   }
       
   }

