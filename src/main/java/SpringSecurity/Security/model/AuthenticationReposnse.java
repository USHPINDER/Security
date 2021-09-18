package SpringSecurity.Security.model;

public class AuthenticationReposnse {

	private final String jwt;

	public AuthenticationReposnse(String jwt) {
		
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
