import javax.net.ssl.*;
import java.io.*;
import java.util.*;

public class FastTCPHttpsDownload {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter website (e.g., www.google.com): ");
        String host = sc.nextLine().trim();
        int port = 443;
        String path = "/";

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            String request = "GET " + path + " HTTP/1.1\r\n"
                    + "Host: " + host + "\r\n"
                    + "User-Agent: FastTCPClient\r\n"
                    + "Connection: close\r\n"
                    + "\r\n";
            out.write(request.getBytes());
            out.flush();

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] chunk = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(chunk)) != -1) {
                buffer.write(chunk, 0, bytesRead);
            }

            String response = buffer.toString("UTF-8");
            int split = response.indexOf("\r\n\r\n");
            String body = split != -1 ? response.substring(split + 4) : response;

            try (FileOutputStream fos = new FileOutputStream("download.html")) {
                fos.write(body.getBytes("UTF-8"));
            }

            System.out.println("✅ Downloaded successfully to download.html");

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
