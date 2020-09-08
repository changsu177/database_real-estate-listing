package pm6.model;


import java.sql.Timestamp;


public class Host extends User {


	protected Timestamp host_since;
	
	public Host (String UserName) {
		super(UserName);
	}
	
	public Host(String UserName, String email, String password, GenderType gender, Timestamp host_since ) {	
		  super( UserName,email,password,gender);
		  this.host_since = host_since;
	}
	


	public Timestamp getHost_since() {
		return host_since;
	}

	public void setHost_since(Timestamp host_since) {
		this.host_since = host_since;
	}

}
