package request;

public class IntentSong extends URLDecorator{
	Request request;
	
	public IntentSong(Request request) {
		this.request = request;
	}

	@Override
	public String getURLLink() {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURL() + "intent=song");
		}else {
			request.setURLLink(request.getBaseURL() + "intent=song");
		}
		return request.getURL();
	}
	
	public String getURLLink(String title) {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURL() + "intent=song&name=" + title);
		}else {
			request.setURLLink(request.getBaseURL() + "intent=song&name=" + title);
		}
		return request.getURL();
	}
	
	
}
