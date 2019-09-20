package request;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import connection.UploadFile;
import music.Song;
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
			e.printStackTrace();
		}

		return songDetail;
	}

	public ArrayList<String> doRequestArtist() {
		ArrayList<String> songDetail = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
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
			if("0 results".equals(sb.toString())) {
				return null;
			}
			String[] string = sb.toString().split("<br>");
			for(String s : string) {
				songDetail.add(s);
			}
			String[] secondSplit;

			for(int i = 0; i < songDetail.size(); i++) {
				secondSplit = songDetail.get(i).split("%%%");
				list.add(secondSplit[1]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Song> doRequestSongs(){
		ArrayList<String> songDetail = new ArrayList<String>();
		ArrayList<Song> list = new ArrayList<Song>();
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
			if(sb.toString().equals("0 results")) {
				return null;
			}
			String[] string = sb.toString().split("<br>");
			for(String s : string) {
				songDetail.add(s);
			}
			String[] secondSplit;


			for(int i = 0; i < songDetail.size(); i++) {
				secondSplit = songDetail.get(i).split("%%%");
				list.add(new Song(secondSplit[0], secondSplit[1], secondSplit[2]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}


	public boolean postUser(String username, String password, String profilePhoto, String panoramicPhoto, String state) {
		try {
			//			// open a connection to the site
			//			URL url = new URL("http://thunderbeat.altervista.org/login.php?");
			//			URLConnection con = url.openConnection();
			//			// activate the output
			//			con.setDoOutput(true);
			//			PrintStream ps = new PrintStream(con.getOutputStream());
			//			// send your parameters to your site
			//			ps.print("intent=signup");
			//			ps.print("&user=" + java.net.URLEncoder.encode(username, "UTF-8"));		
			//			ps.print("&password=" + java.net.URLEncoder.encode(password, "UTF-8"));
			//			ps.print("&bio=" + java.net.URLEncoder.encode(state, "UTF-8"));			
			//			UploadFile.uploadFile(username, profilePhoto, panoramicPhoto, "http://thunderbeat.altervista.org/uploadimg32.php");
			//			 we have to get the input stream in order to actually send the request
			//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			//			StringBuffer sb=new StringBuffer();
			//			String line;
			//
			//			while((line=in.readLine())!=null) {
			//				sb.append(line);
			//
			//			}
			//			if(sb.toString().equals("1")) {
			//				return false;
			//			}
			//			// close the print stream
			//			ps.close();

			String data = "&user=" + java.net.URLEncoder.encode(username, "UTF-8") 
			+ "&password=" + java.net.URLEncoder.encode(password, "UTF-8")
			+ "&bio=" + java.net.URLEncoder.encode("\'" + state + "\'", "UTF-8");
			Request request = new LoginRequest();
			request = new IntentSignUp(request);
			request.setURLLink(request.getURLLink(data));
			UploadFile.uploadFile(username, profilePhoto, panoramicPhoto, "http://thunderbeat.altervista.org/uploadimg32.php");
			ArrayList<String> result = request.doRequest();
			if("1".equals(result.get(0))) {
				return false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean logIn(String username, String password) {
		try {
			//			// open a connection to the site
			//			URL url = new URL("https://thunderbeat.altervista.org/login.php?");
			//			URLConnection con = url.openConnection();
			//			// activate the output
			//			con.setDoOutput(true);
			//			PrintStream ps = new PrintStream(con.getOutputStream());
			//			// send your parameters to your site
			//			ps.print("intent=login");
			//			ps.print("&user=" + java.net.URLEncoder.encode(username, "UTF-8"));
			//			ps.print("&password=" + java.net.URLEncoder.encode(password, "UTF-8"));
			//			// we have to get the input stream in order to actually send the request
			//			ps.println();
			//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			//			StringBuffer sb = new StringBuffer();
			//			String line;
			//			while((line = in.readLine()) != null) {
			//				sb.append(line);
			//			}
			//			System.out.println(sb.toString());
			//			if(sb.toString().endsWith("1")) {
			//				return false;
			//			}
			//			// close the print stream
			//			ps.close();
			String data = "&user=" + java.net.URLEncoder.encode(username, "UTF-8") 
			+ "&password=" + java.net.URLEncoder.encode(password, "UTF-8");
			Request request = new LoginRequest();
			request = new IntentLogin(request);
			request.setURLLink(request.getURLLink(data));
			ArrayList<String> result = request.doRequest();
			if("1".equals(result.get(0))) {
				return false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;

	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}


	public static String getImageRequest(String string) {

		InputStream in;
		try {
			in = new BufferedInputStream(new URL("https://thunderbeat.altervista.org/" +"Images/thunder.jpg").openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		    byte[] buf = new byte[1024];
		    int n = 0;
		    while (-1!=(n=in.read(buf)))
		    {
		       out.write(buf, 0, n);
		    }
		    out.close();
		    in.close();
		    byte[] response = out.toByteArray();
		    FileOutputStream fos = new FileOutputStream("C:\\Users\\Amrit\\Documents\\" + string + ".jpg");
		    fos.write(response);
		    fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return string = "C:\\Users\\Amrit\\Documents\\" + string + ".jpg"; 

	}
	
	
	
}
