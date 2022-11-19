import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.lang.*;

class DownloadThread extends Thread {
	public BufferedOutputStream outputStream;
	public BufferedInputStream inputStream;
	public HttpURLConnection conn;

    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running");
            conn.connect();
            byte[] buffer = new byte[8192];
			int byteCount;
			long bytesDownloaded=0L;
			while ((byteCount = inputStream.read(buffer)) != -1){
				outputStream.write(buffer, 0, byteCount);
				bytesDownloaded += byteCount;
			}
			System.out.println("Bytes downloaded " + bytesDownloaded);
			conn.disconnect();
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}


class DownloadFile {

	public static void DownloadWithResume(String fileURL, String filePath) throws IOException{
		FileOutputStream FOS = new FileOutputStream(filePath);
		URL url1 = new URL(fileURL);
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestMethod("HEAD");
		int fileSize = conn1.getContentLength();
		System.out.println("File size="+fileSize);
		BufferedOutputStream outputStream = new BufferedOutputStream(FOS);
		int chunkSize = 1000;
		for(int size=0; size<fileSize; size+=chunkSize) {
			URL url = new URL(fileURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Range", "bytes=" + size + "-" + (size+chunkSize));
			BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
			DownloadThread download
                = new DownloadThread();
            download.outputStream = outputStream;
            download.inputStream = inputStream;
            download.conn = conn;
            download.start();
		}
		
		// inputStream.close();
		// outputStream.flush();
		// outputStream.close();
		
	}



	public static void main(String args[]) throws IOException {
			String FILE_URL = "https://getsamplefiles.com/download/pdf/sample.pdf";
			String FILE_NAME = "sample.pdf";
			DownloadWithResume(FILE_URL, FILE_NAME);
	}
}