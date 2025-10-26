package http;
import java.io.*;
import java.util.*;
import javax.net.ssl.*;

public class HttpDownload {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter website name (e.g. www.google.com): ");
        String website = scanner.nextLine().trim();
        String path = "/";

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(website, 443);

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw.print("GET " + path + " HTTP/1.1\r\n");
            pw.print("Host: " + website + "\r\n");
            pw.print("User-Agent: JavaSocketClient\r\n");
            pw.print("Connection: close\r\n");
            pw.print("\r\n");
            pw.flush();

            String line;
            boolean isBody = false;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (isBody) {
                    content.append(line).append("\n");
                } else if (line.isEmpty()) {
                    isBody = true;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("download.html"))) {
                writer.write(content.toString());
            }

            System.out.println("Page saved to download.html");

            br.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
 