package com.urt.persistence.model.study;

import java.util.Comparator;

public class ViewStudyClassSubjectsComparator implements Comparator{
    public int compare(Object emp1, Object emp2){
    	int subject1 = (int)((ViewStudyClassSubjects)emp1).getSortingOrder(); 
    	int subject2 = (int) ((ViewStudyClassSubjects)emp2).getSortingOrder();  
		if(subject1 >  subject2)
				return 1;
			else
				return 0;
   }
}

