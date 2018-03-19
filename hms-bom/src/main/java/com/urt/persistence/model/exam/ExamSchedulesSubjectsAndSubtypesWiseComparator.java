package com.urt.persistence.model.exam;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

public class ExamSchedulesSubjectsAndSubtypesWiseComparator implements Comparator{
    public int compare(Object emp1, Object emp2){
    	ViewExamSchedule examSch1 = (ViewExamSchedule)emp1;        
    	ViewExamSchedule examSch2 = (ViewExamSchedule)emp2;
		if(!ObjectFunctions.isNullOrEmpty(examSch1) && !ObjectFunctions.isNullOrEmpty(examSch2)){
			if(examSch1.getSubjectSortingOrder() > 0 && examSch2.getSubjectSortingOrder() > 0){
				if(examSch1.getSubjectSortingOrder() > examSch2.getSubjectSortingOrder())
					return 1;
				else if(examSch1.getSubjectSortingOrder() == examSch2.getSubjectSortingOrder()){
					if(!ObjectFunctions.isNullOrEmpty(examSch1.getSubTypeSortingOrder()) && !ObjectFunctions.isNullOrEmpty(examSch2.getSubTypeSortingOrder())){
						if(examSch1.getSubTypeSortingOrder() >= examSch2.getSubTypeSortingOrder())
							return 1;
						else 
							return 0;
					}else
						return 1;
				}else
					return 0;
			}else if(StringFunctions.isNotNullOrEmpty(examSch1.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(examSch2.getSubjectNumber()))
				return examSch1.getSubjectNumber().compareToIgnoreCase(examSch2.getSubjectNumber());
			else
				return examSch1.getSubjectName().compareToIgnoreCase(examSch2.getSubjectName());			
		}else
			return 0;
   }
       
   }

