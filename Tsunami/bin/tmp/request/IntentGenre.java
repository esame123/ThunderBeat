package request;

public class IntentGenre extends URLDecorator{
	Request request;

	public IntentGenre(Request request) {
		this.request = request;

	}

	@Override
	public String getURLLink(){
		
		if("?".equals(request.getLastValue())) {
			request.setURLLink(request.getURLLink() + "intent=genre");
		}

		return request.getURLLink();
	}

	@Override
	public String getURLLink(String string) {
		return request.getURLLink();		
	}
}
