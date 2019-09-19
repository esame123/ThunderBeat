package connection;

import java.io.IOException;

public class check {
	String song;
	
	public check(String song) throws IOException {
		this.song=song; 	
	}
	
	
	public  String exists() throws IOException {
		
		String urlLink ="https://thunderbeat.altervista.org/refresh.php?intent=request&file="+java.net.URLEncoder.encode(song, "UTF-8");
		connection c=new connection(urlLink);
		return c.commit().substring(0,2);
		
		}
}
