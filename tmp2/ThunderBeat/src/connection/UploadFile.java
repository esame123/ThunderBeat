package connection;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

//**************************   INSERIRE FILE CHOOSER QUI (richiamando uploadFile)   ************************
//	uploadFile(username, 
//		"C:\\Users\\Utente\\Desktop\\progettojava\\thunder.jpg", 
//				"http://thunderbeat.altervista.org/uploadimg32.php");
 
public class UploadFile {
	public static void uploadFile(String username,String pathProfile,String pathView, String serverURL) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        FileBody profile = null;
        FileBody view = null;
        //post request to send file
        HttpPost httpPost = new HttpPost(serverURL);
        MultipartEntity reqEntity = new MultipartEntity();
        
        if (pathProfile!=null) {
        	profile=new FileBody(new File(pathProfile));
        	reqEntity.addPart("img_profile", profile);        
        }
        if (pathView!=null) {
        	view = new FileBody(new File(pathView));
        	reqEntity.addPart("img_view",view);
        }
        
        reqEntity.addPart("username", new StringBody(username));   
        httpPost.setEntity(reqEntity);
        httpClient.getConnectionManager().shutdown();
    }
}