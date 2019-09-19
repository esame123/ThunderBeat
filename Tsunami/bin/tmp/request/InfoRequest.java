package request;

public class InfoRequest extends Request{
	public InfoRequest() {
		urlLink = "https://thunderbeat.altervista.org/info.php?";
	}

	@Override
	public String getBaseURL() {
		return "https://thunderbeat.altervista.org/info.php?";
	}
}
