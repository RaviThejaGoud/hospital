package com.urt.service.manager.impl.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FCMTest {
	private static final Log log = LogFactory.getLog(FCMTest.class);
	public static void main(String[] args) {
	    FirebaseOptions options = null;

	    try {
	        options = new FirebaseOptions.Builder()
	            //.setServiceAccount(new FileInputStream("/Development/Notifications/My-Notification_Server-serviceAccountCredentials.json"))
	        .setServiceAccount(new FileInputStream("D://eschool//hyniva-1363d-firebase-adminsdk-s8301-4e51830137.json"))
	            .setDatabaseUrl("https://hyniva-1363d.firebaseio.com/")
	            .build();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    FirebaseApp.initializeApp(options);

	    // Obtain serverKey from Project Settings -> Cloud Messaging tab
	    // for "My Notification Server" project in Firebase Console.
	    //String serverKey = <get_server_key_from_firebase_console>;
	    String serverKey = "AAAAyv6NfnM:APA91bEbKl78JWSXlRwXZ3YmpqGtuZXZ0qPZ_naZ4NiheAsD6Kko9L-GLGZ-_y1-8FI4Pp7GkD53VBjuBWksG62tlyPYozY3xt0F4LqPF3amHBeWXnQjWnWWjudpjJAcAWty42sPm-vx";
	            try {
	                Sender sender = new FCMSender(serverKey);
	                String textmessage = "{\"information\":{\"title\":\"Your Leave is Rejected From\",\"description\":\"Your Leave is Rejected. Click to see more info\",\"leavesVOList\":[{\"id\":659,\"startDate\":\"2016-09-08 00:00:00\",\"appliedBy\":\"ishitha\",\"approvalsComment\":\"Your leave rejected aa \",\"accountId\":43472,\"supervisor\":43711,\"leaveStatus\":\"R\",\"description\":\"Leave Rejected\",\"leavesCount\":0,\"endDate\":\"2016-09-08 00:00:00\",\"leaveType\":\"CL\"}]},\"notificationFor\":\"Leave\"}";
	                Message message = new Message.Builder()
	                                  .collapseKey("EazySchool")
	                                  .timeToLive(6000)
	                                  .delayWhileIdle(false)
	                                  .addData("message", textmessage)
	                                  .build();  
	                
	                // Use the same token(or registration id) that was earlier
	                // used to send the message to the client directly from
	                // Firebase Console's Notification tab.
	                Result result = sender.send(message,
	                		"cyt_DW0FNOg:APA91bG51bly99ZVC6EyrDd5NdzzNJ057Tvv35wB7kZVyNCgZT8TJUUtYYEHczugcliuSxABygTUiNDF7OxpAGM5hEELttnW8R_To-tkwQnt4rDmDZ4GTTtq-nKo_rd937FvAwGXrhh3",
	                		//"fcguSgo766M:APA91bHM-mP7FX7g1pmySp0nTRuwQWJTE-o65gzi65ZQGSodNDtu3qLO5Xe8f3CbwIRZhwoAvZo7gcmRvvPxl9RbomunCNsb1-tciG4jYFVqD4nMvcU72odogsQUoUB1ElMznPr7j6Lt",
	            //  "fuP4TG_fdcw:APA91bFq2CKpZ8U4bemK4ZQbt8Fn1TKSG1fU_FiplpKFeCsXVIfp7WNpSNdUhCXT7u-0B72zSGAMiDJCQDAUmtD_2xIwJKf1-8T3h3VrhYBLvD-FDUZdMHictud5OWBuI4qSBhARBvjb",

	                    0);
	               log.info("Result: " + result.toString());
	            } 
	            catch (Exception e) {
	                e.printStackTrace();
	            }
}
}
