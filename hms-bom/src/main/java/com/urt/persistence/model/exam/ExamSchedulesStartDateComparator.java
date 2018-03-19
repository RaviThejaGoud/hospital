package com.urt.persistence.model.exam;

import java.util.Comparator;
import java.util.Date;

import com.churchgroup.util.object.ObjectFunctions;

public class ExamSchedulesStartDateComparator implements Comparator<ViewExamScheduleDetails>{
    public int compare(ViewExamScheduleDetails schedule1, ViewExamScheduleDetails schedule2){
    	if(!ObjectFunctions.isNullOrEmpty(schedule1) && !ObjectFunctions.isNullOrEmpty(schedule2)){
    		Date date1 = schedule1.getStartDate();        
        	Date date2 = schedule2.getStartDate();
    		if(!ObjectFunctions.isNullOrEmpty(date1) && !ObjectFunctions.isNullOrEmpty(date2)){
    			 long n1 = date1.getTime();
    		        long n2 = date2.getTime();
    		        if (n1 < n2) 
    		        	return -1;
    		        else if (n1 > n2) 
    		        	return 1;
    		}
    	}
		return 0;
   }
}

