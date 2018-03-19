package com.urt.sql.mssql;

import java.sql.Connection;
import java.sql.DriverManager;


public class MSSqlUtils  {
	
	public static String BSF_DB_NAME="jdbc:sqlserver://97.77.83.50:1433;databaseName=BSFGroups;";
	public static String BSF_DB_USER="urt793";
	public static String BSF_DB_PWD="Git4Bsfg";
	
	public static Connection getMSSQLConnection(String params)
	{		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		    return (Connection)DriverManager.getConnection(params);
		}    
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }		
	}
	public static Connection getMSSQLConnection() throws UserSQLServerException
	{		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		    return (Connection)DriverManager.getConnection(MSSqlUtils.BSF_DB_NAME,MSSqlUtils.BSF_DB_USER,MSSqlUtils.BSF_DB_PWD);
		}   
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        throw new UserSQLServerException("CONN");
	    }		
	}
}