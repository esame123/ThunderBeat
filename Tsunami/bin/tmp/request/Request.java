package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import users.User;

public abstract class Request {

	protected String urlLink = "https://thunderbeat.altervista.org/?";
	private String lastValue = urlLink.substring(urlLink.length() - 1);
	private User user;

	public String getURLLink() {
		return this.urlLink;
		
	}
	
	public String getURLLink(String title) {
		this.urlLink = this.urlLink.concat(title);
		this.lastValue = urlLink.substring(urlLink.length() - 1);
		return this.urlLink;
	}

	public void setURLLink(String content) {
		this.urlLink = content;
		this.lastValue = urlLink.substring(urlLink.length() - 1);
	}
	public String getLastValue() {
		return this.lastValue;
	}

	public void setLastValue() {
		this.lastValue = "&";
	}	
	
	public String getURL() {
		return this.urlLink;
	}
	
	public String getBaseURL() {
		return "https://thunderbeat.altervista.org/?";
	}
	
	public ArrayList<String> doRequest() {
		ArrayList<String> songDetail = new ArrayList<String>();
		try {
			URL url=new URL(this.urlLink);
			
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();


			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer sb=new StringBuffer();
			String line;


			while((line=in.readLine())!=null) {
				sb.append(line);
			}
			in.close();		
			String[] string = sb.toString().split("%%%");
			for(String s : string) {
				songDetail.add(s);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return songDetail;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void postUser(String username, String password, String profilePhoto, String panoramicPhoto, String state) {
		try {
		    // open a connection to the site
		    URL url = new URL("https://thunderbeat.altervista.org/upload.php");
		    URLConnection con = url.openConnection();
		    // activate the output
		    con.setDoOutput(true);
		    PrintStream ps = new PrintStream(con.getOutputStream());
		    // send your parameters to your site
		    ps.print("username=" + username);		
		    ps.print("&password=" + password);
		    ps.print("&profile=" + profilePhoto);
		    ps.print("&view=" + panoramicPhoto);
		    ps.print("&bio=" + state);		    
		    ps.print("&intent=signup");				// PER FAR CAPIRE AL RICEVITORE CHE E' LOGIN
		    // we have to get the input stream in order to actually send the request
		
		    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer sb=new StringBuffer();
			String line;
			
			while((line=in.readLine())!=null) {
				sb.append(line);
				
			}
		    // close the print stream
		    ps.close();
		    } catch (MalformedURLException ex) {
		        ex.printStackTrace();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
	}
}
