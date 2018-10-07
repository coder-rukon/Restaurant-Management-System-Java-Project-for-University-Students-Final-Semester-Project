package RestaurantManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	public String queryStringRs;
	public ResultSet queryResult;
	public Statement sqlStatement;
	public Db(){
		con();
	}
	public void con(){
		try{
			Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rms","root","");
			sqlStatement = mysqlConnection.createStatement();
		}catch(Exception e){
			
		}
	}
	public ResultSet getResult(String rsQl){
		try {
			return this.sqlStatement.executeQuery(rsQl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.queryResult;
	}
}
