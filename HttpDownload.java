import java.io.*;
import java.net.*;

public class HttpDownload {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter website URL (e.g., https://example.com): ");
            String urlString = in.readLine().trim();

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);  // 5 sec timeout
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            System.out.println("Response code: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("download.html"));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            reader.close();
            conn.disconnect();

            System.out.println(" Page downloaded to download.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
