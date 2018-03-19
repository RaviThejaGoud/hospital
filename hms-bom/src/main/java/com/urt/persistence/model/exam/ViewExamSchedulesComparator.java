package com.urt.persistence.model.exam;

import java.util.Comparator;

import com.churchgroup.util.string.StringFunctions;

public class ViewExamSchedulesComparator implements Comparator{
    public int compare(Object emp1, Object emp2){
    	ViewExamSchedule subject1 = (ViewExamSchedule)emp1;        
    	ViewExamSchedule subject2 = (ViewExamSchedule)emp2;
		if(subject1.getSubjectSortingOrder() > 0 && subject2.getSubjectSortingOrder() > 0){
			if(subject1.getSubjectSortingOrder() > subject2.getSubjectSortingOrder())
				return 1;
			else if(subject1.getSubjectSortingOrder() == subject2.getSubjectSortingOrder()){
				//if(subject1.getSubTypeSortingOrder() >= subject2.getSubTypeSortingOrder())
				if(subject1.getSubTypeSortingOrder() > subject2.getSubTypeSortingOrder())
					return 1;
				else 
					return 0;
			}else
				return 0;
		}else if(StringFunctions.isNotNullOrEmpty(subject1.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(subject2.getSubjectNumber()))
			return subject1.getSubjectNumber().compareToIgnoreCase(subject2.getSubjectNumber());
		else
			return subject1.getSubjectName().compareToIgnoreCase(subject2.getSubjectName());			
   }
       
   }

