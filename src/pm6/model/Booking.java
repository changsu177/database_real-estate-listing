package pm6.model;

import java.util.Date;

public class Booking {
	
	protected int id;
	protected Property property;
	protected Date start_date;
	protected Date end_date;
	protected int price;
	protected int review_id;
	protected int user_id;
	
	public Booking (int id, Property property, Date start_date2, Date end_date2 , int price, int uid, int rid) {
		this.id = id;
		this.property = property;
		this.start_date = start_date2;
		this.end_date = end_date2;
		this.price = price;
		this.review_id = rid;	
		this.user_id = uid;
	}
	
	public Booking (int id, Property property, Date start_date2, Date end_date2 , int price, int uid) {
		this.id = id;
		this.property = property;
		this.start_date = start_date2;
		this.end_date = end_date2;
		this.price = price;
		this.user_id = uid;
	}
	
	public Booking (int id) {
		this.id = id;
	}
	
	public Booking ( Property property,  Date start_date, Date end_date , int price, int rid) {
	
		this.property = property;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
		this.review_id = rid;	
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Property getProperty() {
		return property;
	}
	
	public int getPropId() {
		return property.getPropertyKey();
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUID() {
		return this.user_id;
	}
	public void setRID(int uid) {
		this.user_id = uid;
	}
	public int getRID() {
		return this.review_id;
	}
	

}
