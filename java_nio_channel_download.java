import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

class DownloadFile {
    public static void main(String[] args) throws IOException {

        URL fetchWebsite = new URL("https://filesamples.com/samples/document/pdf/sample3.pdf");
        long start = System.currentTimeMillis();
        ReadableByteChannel readableByteChannel = Channels.newChannel(fetchWebsite.openStream());
        try (FileOutputStream fos = new FileOutputStream("sample.pdf")) {
            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}