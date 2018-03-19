package com.urt.persistence.model.exam;

import java.util.Comparator;

import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;

public class ExamSchedulesComparator implements Comparator{
    public int compare(Object emp1, Object emp2){
    	ViewClassExamDetails subject1 = (ViewClassExamDetails)emp1;        
    	ViewClassExamDetails subject2 = (ViewClassExamDetails)emp2;
		if(!ObjectFunctions.isNullOrEmpty(subject1) && !ObjectFunctions.isNullOrEmpty(subject2)){
			if(subject1.getSortingOrder() > 0 && subject2.getSortingOrder() > 0){
				if(subject1.getSortingOrder() > subject2.getSortingOrder())
					return 1;
				else if(subject1.getSortingOrder() == subject2.getSortingOrder()){
					if(subject1.getSubTypeSortingOrder() >= subject2.getSubTypeSortingOrder())
						return 1;
					else 
						return 0;
				}else
					return 0;
			}else if(StringFunctions.isNotNullOrEmpty(subject1.getSubjectNumber()) && StringFunctions.isNotNullOrEmpty(subject2.getSubjectNumber()))
				return subject1.getSubjectNumber().compareToIgnoreCase(subject2.getSubjectNumber());
			else
				return subject1.getName().compareToIgnoreCase(subject2.getName());			
		}else
			return 0;
   }
       
   }

