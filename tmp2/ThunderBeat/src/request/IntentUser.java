package request;



public class IntentUser extends URLDecorator{
	Request request;

	public IntentUser(Request request) {
		this.request = request;
	}

	@Override
	public String getURLLink() {
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=user");
		}else {
			request.setURLLink(request.getBaseURL() + "intent=user");
		}

		return request.getURL();
	}

	@Override
	public String getURLLink(String name) {

		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=user" + "&name=" + name);
		}else {
			request.setURLLink(request.getBaseURL() + "&name=" + name);
		}

		return request.getURL();
	}


}