package request;

public class LibraryRequest extends Request{
	
	public LibraryRequest() {
		urlLink = "https://thunderbeat.altervista.org/libreria.php?";
	}

	@Override
	public String getBaseURL() {
		return "https://thunderbeat.altervista.org/libreria.php?";
	}

}
