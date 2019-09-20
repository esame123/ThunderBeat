package request;

public class LoginRequest extends Request{
	
	public LoginRequest() {
		urlLink = "https://thunderbeat.altervista.org/login.php?";
	}
	
	public String getBaseURL() {
		return "https://thunderbeat.altervista.org/login.php?";
	}
}
