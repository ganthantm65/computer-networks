import java.net.*;
import java.util.*;

public class DNSServer {
    static HashMap<String, String> mapping = new HashMap<>();

    public static void main(String[] args) {
        try {
            fillMap();
            DatagramSocket server = new DatagramSocket(2000);
            System.out.println("DNS Server started...");
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);

                String host = new String(request.getData(), 0, request.getLength()).trim();
                System.out.println("Query received for: " + host);

                String ip = mapping.getOrDefault(host, "Not Found");
                byte[] responseData = ip.getBytes();

                DatagramPacket response = new DatagramPacket(
                        responseData,
                        responseData.length,
                        request.getAddress(),
                        request.getPort()
                );

                server.send(response);
                System.out.println("Response sent: " + ip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void fillMap() {
        mapping.put("zoho.com", "192.168.1.1");
        mapping.put("google.com", "192.168.1.2");
        mapping.put("amazon.com", "192.168.1.3");
        mapping.put("facebook.com", "192.168.1.4");
    }
}
