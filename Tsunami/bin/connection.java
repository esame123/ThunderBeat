package check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class connection {
	String urlLink;
	
	public connection(String urlLink) {	
		this.urlLink=urlLink;
	}
	
	public String commit() {
		URL url = null;
		try {
			url = new URL(urlLink);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		StringBuffer sb=new StringBuffer();
		String line;
		
		try {
			while((line=in.readLine())!=null) {
				sb.append(line);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return sb.toString();
	}
}
