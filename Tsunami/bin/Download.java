package provagui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class Download {
	private String song;
	private File filewav;
	public Download(String song) throws IOException {
		this.song=song;

	  File filemp3=new File("C:\\Users\\Utente\\AppData\\Local\\Temp\\"+song);
if (!filemp3.exists()) {		
		InputStream fis=new URL("http://thunderbeat.altervista.org/Sound/"+song).openStream();
		  
		    OutputStream outstream = new FileOutputStream(filemp3);
		    byte[] buffer = new byte[50000000];
		    int len;
		    while ((len = fis.read(buffer)) > 0) {
		        outstream.write(buffer, 0, len);
		    }
		    outstream.close();
	}
		    this.filewav=convertMP3toWAV(filemp3);
		    filemp3.delete();
	}
	public File getFileWav() {
			return this.filewav;
	}
	
	public static File convertMP3toWAV(File mp3){
		File temp=null;
		try {
			temp = File.createTempFile(mp3.getName().split("\\.")[0],".wav");
			temp.deleteOnExit();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Converter converter = new Converter();
		try {
			converter.convert(mp3.getAbsolutePath(), temp.getAbsolutePath());
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		return temp;
	}
	public void delfile() {
		filewav.delete();
	}

}
