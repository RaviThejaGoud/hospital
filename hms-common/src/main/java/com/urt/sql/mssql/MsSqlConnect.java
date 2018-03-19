package com.urt.sql.mssql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MsSqlConnect  {
	
	public static Connection getMSSQLConnection(String params)
	{		
		try
		{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
		    Connection conn=(Connection)DriverManager.getConnection(params);
		    return conn;
		}    
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }		
	}	
}
