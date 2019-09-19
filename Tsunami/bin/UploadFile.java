package acaso;
import java.io.File;
import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

//**************************   INSERIRE FILE CHOOSER QUI (richiamando uploadFile)   ************************
//	uploadFile("C:\\Users\\Utente\\Desktop\\progettojava\\thunder.jpg", "http://thunderbeat.altervista.org/uploadimg32.php");
 
public class UploadFile {
	public static void uploadFile(String filePath, String serverURL) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        //post request to send file
        HttpPost httpPost = new HttpPost(serverURL);

        FileBody uploadFile = new FileBody(new File(filePath));
        MultipartEntity reqEntity = new MultipartEntity();

        reqEntity.addPart("userfile", uploadFile);
        reqEntity.addPart("username", new StringBody("thomas"));
        reqEntity.addPart("id", new StringBody("cacca!"));
        httpPost.setEntity(reqEntity);

        httpClient.getConnectionManager().shutdown();
    }
}