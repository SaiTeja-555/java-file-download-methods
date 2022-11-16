import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class DownloadFile {
    public static void main(String[] args) throws IOException {

        URL fetchWebsite = new URL("https://filesamples.com/samples/document/pdf/sample3.pdf");

        Path path = Paths.get("sample.pdf");
        long start = System.currentTimeMillis();
        try (InputStream inputStream = fetchWebsite.openStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}