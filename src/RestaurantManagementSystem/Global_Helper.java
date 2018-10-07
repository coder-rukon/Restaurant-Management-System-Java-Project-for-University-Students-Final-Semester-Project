package RestaurantManagementSystem;

import java.sql.SQLException;
import java.util.ArrayList;

public class Global_Helper extends Db {
	/*Get all Customers in array*/
	public ArrayList getAllCustomer(){
		ArrayList<ArrayList<String>> customers = new ArrayList<ArrayList<String>>();
		this.queryResult = this.getResult("select * from users where rules = 'customer'");
		try {
			int i = 0;
			while(this.queryResult.next()){
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(this.queryResult.getString("id"));
				tempList.add(this.queryResult.getString("user_name"));
				tempList.add(this.queryResult.getString("full_name"));
				tempList.add(this.queryResult.getString("email"));
				tempList.add(this.queryResult.getString("mobile"));
				customers.add(i,tempList);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	/*Get All Products in List Array*/
	public ArrayList getAllProducts(){
		ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
		this.queryResult = this.getResult("select * from products");
		try {
			int i = 0;
			while(this.queryResult.next()){
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(this.queryResult.getString("id"));
				tempList.add(this.queryResult.getString("name"));
				tempList.add(this.queryResult.getString("quantity"));
				tempList.add(this.queryResult.getString("unit_price"));
				products.add(i,tempList);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
