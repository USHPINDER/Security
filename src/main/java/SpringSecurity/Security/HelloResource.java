/*Testing*/
package SpringSecurity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import SpringSecurity.Security.model.AuthenicationRequest;
import SpringSecurity.Security.model.AuthenticationReposnse;
import SpringSecurity.Security.services.MyUserDetailsService;

@RestController
public class HelloResource{

	@Autowired
	private AuthenticationManager authenticationManager;
	 
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	
	public ResponseEntity<?> creatAuthenticationToken(@RequestBody AuthenicationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO Auto-generated catch block
			throw new Exception("Incorrect username or password",e);
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt=jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationReposnse(jwt));
	}
	
	
}
