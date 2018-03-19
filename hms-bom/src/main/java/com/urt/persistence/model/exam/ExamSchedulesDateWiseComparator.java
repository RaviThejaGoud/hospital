package com.urt.persistence.model.exam;

import java.util.Comparator;
import java.util.Date;

import com.churchgroup.util.object.ObjectFunctions;

public class ExamSchedulesDateWiseComparator implements Comparator{

   

    public int compare(Object emp1, Object emp2){
    	Date date1 = ((ExamSchedules)emp1).getExamDate();        
    	Date date2 = ((ExamSchedules)emp2).getExamDate();
		if(!ObjectFunctions.isNullOrEmpty(date1) && !ObjectFunctions.isNullOrEmpty(date2)){
			 long n1 = date1.getTime();
		        long n2 = date2.getTime();
		        if (n1 < n2) return -1;
		        else if (n1 > n2) return 1;
		        else return 0;
			/*long timeDifference = 0;
	        boolean isBefore;
	        isBefore = (date1).before(date2);
	        int difference;
	        if(timeDifference == 0)
	        {
	            difference = 0;
	        }
	        else if(timeDifference < 0)
	        {
	            difference = -1;
	        }
	        else
	        {
	            difference = 1;
	        }
	        return difference;   */    
		}
		return 0;
   }
       
   }

