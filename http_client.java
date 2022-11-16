import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.FileUtils;
import java.io.*;

public class DownloadFile {
    public static void main(String[] args) throws IOException {

    	String url = "";
    	String fileLocalPath = "";

        CloseableHttpClient hTTPClient = HttpClients.createDefault();
        
        HttpGet hTTPGet = new HttpGet(url);
        
        CloseableHttpResponse hTTPResponse = null;
        
        long start = System.currentTimeMillis();
        try {
            hTTPResponse = hTTPClient.execute(hTTPGet);
            HttpEntity httpEntity = hTTPResponse.getEntity();
            
            if (httpEntity != null) {
                FileUtils.copyInputStreamToFile(httpEntity.getContent(), new File(fileLocalPath));
            }
        } catch (IOException e) {
            // IOException: Signals that an I/O exception of some sort has occurred.
            // This class is the general class of exceptions produced by failed or interrupted I/O operations.
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}