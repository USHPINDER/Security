package SpringSecurity.Security.model;

public class AuthenicationRequest {

	private String username;
	public AuthenicationRequest(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	
public AuthenicationRequest() {
		
		
	}
	public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
