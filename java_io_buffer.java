import java.util.*;
import java.io.*;
import java.net.URL;

class DownloadFile {
	public static void main(String args[]) {
			String FILE_URL = "https://filesamples.com/samples/document/pdf/sample3.pdf";
			String FILE_NAME = "sample.pdf";
			try {
					BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
					FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
					long start = System.currentTimeMillis();
					byte dataBuffer[] = new byte[1024];
			    	int bytesRead;
			    	while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
			        		fileOutputStream.write(dataBuffer, 0, bytesRead);
		    		}
		    		long end = System.currentTimeMillis();
        			System.out.println(end-start);
			}
			catch (IOException e) {
	    			// handle exception
			}
	}
}