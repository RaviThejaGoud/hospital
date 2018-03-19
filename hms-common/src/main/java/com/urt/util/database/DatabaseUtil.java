/********************************************************************
 * Copyright (C) 2008-09
 * IFS
 * All Rights Reserved 
 *
 * File: DatabaseUtil.java
 ********************************************************************
 *
 *  Ver   Date              Name               Description
 *  ====  ========          ============       ==================
 *  1.0  Nov 23, 2008       Sreeram J           Created
/********************************************************************/
package com.urt.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.churchgroup.util.object.ObjectFunctions;



/**
 * @author Sreeram
 *
 */
public class DatabaseUtil {
	 
	protected final Log log = LogFactory.getLog(DatabaseUtil.class);
	private static final String driverName="com.mysql.jdbc.Driver";
	private static final String serverName = "localhost";
	private String dataBaseName;
	private String userName;
	private String password;
	
	private Connection connection;
	
	//private Statement  statement;
	 
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getURL()
	{
		return "jdbc:mysql://" + DatabaseUtil.serverName +  "/" + this.getDataBaseName();
	}
	
	public Connection getConnection() {
		if(ObjectFunctions.isNullOrEmpty(this.connection))
		{
			this.connection=getNewConnection();
		}
		return connection;
	}
	
	public Connection getNewConnection()
	{
		Connection connection = null;
	    try {
	    	
	        // Load the JDBC driver
	        Class.forName(driverName);
	    
	        // Create a connection to the database
	        connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
	    } catch (ClassNotFoundException e) {
	        // Could not find the database driver
	        log.debug(e.getMessage());
	    } catch (SQLException e) {
	        // Could not connect to the database
	    	log.debug(e.getMessage());
	    }
	    return connection;
	}
	
	public Statement getStatement() {
		
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*public void setStatement(Statement statement) {
		this.statement = statement;
	}*/
	
	public boolean executeInsert(String insertQuery)
	{
		try {
			int returnValue = getStatement().executeUpdate(insertQuery);
			if(returnValue >0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void executeStateRecords()
	{
	
		executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (1,'AL','Alabama')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES(2,'AK','Alaska')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (3,'AR','Arkansas')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(4,'AZ','Arizona')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(5,'CA','California')");
   	    executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (6,'CO','Colorado')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (7,'CT','Connecticut')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(8,'DE','Delaware')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(9,'DC','District of Columbia')");
   	    executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (10,'FL','Florida')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(11,'GA','Georgia')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (12,'HI','Hawaii')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (13,'IA','Iowa')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(14,'ID','Idaho')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(15,'IL','Illinois')");
   	    executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (16,'IN','Indiana')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(17,'KS','Kansas')");
   	    executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (18,'KY','Kentucky')");
    	executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (19,'LA','Louisiana')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(20,'MA','Massachusetts')");
   	    executeInsert("INSERT INTO state (id,stateCode,stateName) VALUES (21,'MD','Maryland')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(22,'ME','Maine')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(23,'MI','Michigan')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(24,'MN','Minnesota')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(25,'MS','Mississippi')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(26,'MO','Missouri')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(27,'MT','Montana')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(28,'NC','North Carolina')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(29,'ND','North Dakota')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(30,'NE','Nebraska')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(31,'NH','New Hampshire')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(32,'NJ','New Jersey')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(33,'NV','Nevada')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(34,'NM','New Mexico')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(35,'NY','New York')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(36,'OH','Ohio')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(37,'OK','Oklahoma')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(38,'OR','Oregon')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(39,'PA','Pennsylvania')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(40,'RI','Rhode Island')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(41,'SC','South Carolina')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(42,'SD','South Dakota')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(43,'TN','Tennessee')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(44,'TX','Texas')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(45,'UT','Utah')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(46,'VA','Virginia')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(47,'VT','Vermont')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(48,'WA','Washington')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(49,'WI','Wisconsin')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(50,'WV','West Virginia')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(51,'WY','Wyoming')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(52,'DC','Washington DC')");
    	executeInsert(" INSERT INTO state (id,stateCode,stateName) VALUES(53,'NA','----------')");
	}
	
}
