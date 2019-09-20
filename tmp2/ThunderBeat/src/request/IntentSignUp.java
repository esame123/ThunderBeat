package request;

public class IntentSignUp extends URLDecorator{
	Request request;

	public IntentSignUp(Request request) {
		this.request = request;
	}

	@Override
	public String getURLLink() {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=signup");
		}else {
			request.setURLLink(request.getBaseURL() + "intent=signup");
		}

		return request.getURL();
	}

	@Override
	public String getURLLink(String data) {

		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=signup" + data);
		}else {
			request.setURLLink(request.getBaseURL() + data);
		}

		return request.getURL();
	}
}
