package request;

public class IntentLogin extends URLDecorator{
	Request request;

	public IntentLogin(Request request) {
		this.request = request;
	}

	@Override
	public String getURLLink() {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=login");
		}else {
			request.setURLLink(request.getBaseURL() + "intent=login");
		}

		return request.getURL();
	}

	@Override
	public String getURLLink(String data) {

		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=login" + data);
		}else {
			request.setURLLink(request.getBaseURL() + data);
		}

		return request.getURL();
	}
}
