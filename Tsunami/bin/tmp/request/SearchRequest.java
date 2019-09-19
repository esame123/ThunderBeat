package request;

public class SearchRequest extends Request{
	
	public SearchRequest() {
		urlLink = "https://thunderbeat.altervista.org/cerca.php?";
	}
	
	@Override
	public String getBaseURL() {
		return "https://thunderbeat.altervista.org/cerca.php?";
	}

}
