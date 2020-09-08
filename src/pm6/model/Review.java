package pm6.model;

public class Review {

	protected int id;
	protected Double rating;
	protected String comments;
	protected Property property;
	
	
	public Review (int id, Double rating, String comments,  Property property ) {
		this.id = id;
		this.rating = rating;
		this.comments = comments;
		this.property = property;
			
	}
	
	public Review (int id) {
		this.id = id;
		
	}
	
	public Review ( Double rating, String comments,  Property property ) {
	
		this.rating = rating;
		this.comments = comments;
		this.property = property;
			
	}
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public Property getProperty() {
		return property;
	}



	public void setProperty(Property property) {
		this.property = property;
	}

	

}
