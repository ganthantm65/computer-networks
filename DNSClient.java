import java.net.*;
import java.util.*;

public class DNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter domain name: ");
            String host = sc.nextLine();
            byte[] sendData = host.getBytes();

            DatagramPacket request = new DatagramPacket(sendData, sendData.length, serverAddr, 2000);
            client.send(request);

            byte[] receiveData = new byte[1024];
            DatagramPacket response = new DatagramPacket(receiveData, receiveData.length);
            client.receive(response);

            String ip = new String(response.getData(), 0, response.getLength()).trim();
            System.out.println("IP Address: " + ip);

            client.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
