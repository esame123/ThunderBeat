package provagui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




public class Init {
	private String songname;
	public Init(String songname) {
		this.songname=songname;
	}
	
	
	public String  Go() throws IOException {
		

	System.out.println(songname);
	String urlLink ="http://thunderbeat.altervista.org/trova.php?title="+java.net.URLEncoder.encode(songname, "UTF-8");
	URL url=new URL(urlLink);

	HttpURLConnection conn= (HttpURLConnection) url.openConnection();

	
	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	StringBuffer sb=new StringBuffer();
	String line;
	
	while((line=in.readLine())!=null) {
		sb.append(line);
	}
	in.close();
	return sb.toString();
	}
}

