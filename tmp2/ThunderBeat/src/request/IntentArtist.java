package request;



public class IntentArtist extends URLDecorator{
	Request request;

	public IntentArtist(Request request) {
		this.request = request;
	}

	@Override
	public String getURLLink() {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=artist");
		}else {
			request.setURLLink(request.getBaseURL() + "intent=artist");
		}

		return request.getURL();
	}

	@Override
	public String getURLLink(String name) {

		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=artist" + "&name=" + name);
		}else {
			request.setURLLink(request.getBaseURL() + "&name=" + name);
		}

		return request.getURL();
	}


}
