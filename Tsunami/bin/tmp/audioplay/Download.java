package audioplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class Download {
	private File filewav;

	public Download(String song) throws IOException {

		File filein=new File("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\Temp\\"+song);

		if (!filein.exists()) {		
			
			String c=song.substring(0,song.length() - 1);
		
			InputStream fis=new URL("http://thunderbeat.altervista.org/Sound/"+c).openStream();
		  
		    OutputStream outstream = new FileOutputStream(filein);
		    byte[] buffer = new byte[50000000];
		    int len;
		    while ((len = fis.read(buffer)) > 0) {
		        outstream.write(buffer, 0, len);
		    }
		    outstream.close();
		    System.out.println("Downloaded");
		}else System.out.println("esiste gia");
		// se non è un file wav lo converte
		if (!filein.getName().substring(filein.getName().lastIndexOf(".")+1).equals("wav")) {
			this.filewav=convertMP3toWAV(filein);
			System.out.println("Converted");
		}
		else System.out.println("not downloaded");
		filein.deleteOnExit();
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