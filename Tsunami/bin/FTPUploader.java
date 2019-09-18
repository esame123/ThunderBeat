package check;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
 

 
public class FTPUploader {
 
    private String server = "thunderbeat.altervista.org";
    private String user = "thunderbeat";
    private String pass = "gGddwj33HVX2";
    private int port = 21;
    private FTPClient ftpClient;

	static MP3File f;
 
    public FTPUploader() 
    {
        this.ftpClient = new FTPClient();
    }
 
    public void makeUploader(File file1) {
    	

		check c=null;
		try {
			c = new check(file1.getName().replace(" ","_"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String res=null;
		try {
			res = c.exists();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// se non esiste ovunque
		if (!res.equals("11")) {
				// da nessuna parte
			if (res.equals("00")) {	
				
// TODO FileChooser
				System.out.println("Uploading...");
			
				if (this.sendFile(file1)) {
					System.out.println("Upload Complete");
				}
						
			} 
			// esiste solo nel server quindi inserisco nel db
			try {
				f = (MP3File)AudioFileIO.read(file1);
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Tag tag = f.getTag();
			MP3AudioHeader ah=f.getMP3AudioHeader();
			Scanner s;
			String genere;
			if ((genere=tag.getFirst(FieldKey.GENRE))=="") {
				 s=new Scanner(System.in);
				 genere=s.nextLine();
			}
			
			String urlLink=null;				
			try {
				urlLink = "https://thunderbeat.altervista.org/refresh.php?intent=insert"
					+ "&title="+java.net.URLEncoder.encode(file1.getName().split("\\.")[0].replace("'", "\\'"),"UTF-8")
					+ "&artist="+java.net.URLEncoder.encode(tag.getFirst(FieldKey.ARTIST).replace("'", "\\'"),"UTF-8")
					+ "&duration="+java.net.URLEncoder.encode(ah.getTrackLengthAsString(),"UTF-8")
					+ "&url="+java.net.URLEncoder.encode(file1.getName().replace(" ","_").replace("'", "\\'"),"UTF-8")
					+ "&genre="+java.net.URLEncoder.encode(genere,"UTF-8");
			} catch (UnsupportedEncodingException | KeyNotFoundException e1) {
				e1.printStackTrace();
			}
			connection con = new connection(urlLink);
			System.out.println(con.commit());
		}
		else System.out.println("File esistente sia nel database sia nel server");
    }
    
    
    public FTPUploader(String server, String user, String pass)
    {
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.port = 21;
 
        this.ftpClient = new FTPClient();
    }
    
    public boolean deleteFile(String filename) {
    	boolean done = false;
    	String filedb=filename;
    	try {
    		ftpClient.connect(server, port);
    	    ftpClient.login(user, pass);
    	    ftpClient.changeWorkingDirectory("Sound");
    	   
    	    Init in=new Init(filename);
   
    	    done = ftpClient.deleteFile((filename=in.Go().substring(1).replace(" ","")));
    	    System.out.println(filename);
    	    if (done) {
    	    	connection c=new connection("http://thunderbeat.altervista.org/delete.php?title="+java.net.URLEncoder.encode(filedb,"UTF-8"));
    	    	System.out.println(c.commit().toString());
    	    	
    	    }else System.out.println("no");
    	    
    	    
    	}catch(IOException e) {
    		e.printStackTrace();
    	}finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    	}
    	return done;
    }
 
	private boolean sendFile(File localFile)
    {
        boolean done = false;
 
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.changeWorkingDirectory("Sound");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            String remoteFile = localFile.getName().replace(" ", "_");
            InputStream inputStream = new FileInputStream(localFile);
 
            done = ftpClient.storeFile(remoteFile, inputStream);
            inputStream.close();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
 
        return done;
    }
}