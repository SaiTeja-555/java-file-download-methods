import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;


class DownloadFile {

	public static void DownloadWithResume(String fileURL, String filePath) throws IOException{
		File fileToCheck = new File(filePath);
		URL url = new URL(fileURL);
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		long DownloadedSoFar = 0;
		URLConnection urlconnection = url.openConnection();
		FileOutputStream FOS = null;
		if(fileToCheck.exists()){

			DownloadedSoFar = fileToCheck.length();
			System.out.println("exists "+DownloadedSoFar);
			urlconnection.setRequestProperty("Range", "bytes=" + DownloadedSoFar + "-");
			FOS = new FileOutputStream(fileToCheck, true);
			outputStream = new BufferedOutputStream(FOS);
		}else{
			FOS = new FileOutputStream(fileToCheck);
			outputStream = new BufferedOutputStream(FOS);
		}
		urlconnection.connect();
		inputStream = new BufferedInputStream(urlconnection.getInputStream());
		byte[] buffer = new byte[8192];
		int byteCount;
		long bytesDownloaded=0L;
		while ((byteCount = inputStream.read(buffer)) != -1){
			outputStream.write(buffer, 0, byteCount);
			bytesDownloaded += byteCount;
			if(bytesDownloaded >= 10000) {
				break;
			}
		} // while
		inputStream.close();
		outputStream.flush();
		outputStream.close();
		System.out.println(bytesDownloaded);
	}



	public static void main(String args[]) throws IOException {
			String FILE_URL = "https://filesamples.com/samples/document/pdf/sample3.pdf";
			String FILE_NAME = "sample.pdf";
			DownloadWithResume(FILE_URL, FILE_NAME);
	}
}