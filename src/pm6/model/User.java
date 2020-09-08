package pm6.model;

public class User {
	
	protected String UserName;
	protected String email;
	protected String password;
	protected GenderType gender;
	
	public enum GenderType {
		MALE, FEMALE, OTHER
		
	}
	
	public User( String UserName,  String email, String password, GenderType gender ) {
		
		this.UserName = UserName;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	
	public User( String UserName) {
		this.UserName = UserName;
	}
	
	
	public User(  String email, String password, GenderType gender ) {
		
		
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	

}
