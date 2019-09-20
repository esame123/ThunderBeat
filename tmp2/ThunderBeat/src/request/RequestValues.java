package request;

public class RequestValues {

	String name;
	double values;
	
	public RequestValues(String name, double values) {
		this.name = name;
		this.values = values;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the values
	 */
	public double getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(double values) {
		this.values = values;
	}

		
}
