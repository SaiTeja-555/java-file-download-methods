import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

class DownloadFile {
    public static void main(String[] args) throws IOException {

        URL fetchWebsite = new URL("https://filesamples.com/samples/document/pdf/sample2.pdf");

        File file = new File("sample.pdf");
        
        long start = System.currentTimeMillis();

        FileUtils.copyURLToFile(fetchWebsite, file);

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}