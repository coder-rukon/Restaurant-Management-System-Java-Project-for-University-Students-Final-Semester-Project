package customer;

import javafx.beans.property.SimpleStringProperty;

public class Rs_Foods {
	private String FoodName,Price,Option,Id,date,quantity;
	
	public Rs_Foods(String foodName, String price, String option, String id, String date,String quantity) {
		super();
		FoodName = foodName;
		Price = price;
		Option = option;
		Id = id;
		this.date = date;
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFoodName() {
		return FoodName;
	}

	public void setFoodName(String foodName) {
		FoodName = foodName;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getOption() {
		return Option;
	}

	public void setOption(String option) {
		Option = option;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Rs_Foods(String id,String foodName, String price, String option) {
		Id = id;
		FoodName = foodName;
		Price = price;
		Option = option;
	}

}
