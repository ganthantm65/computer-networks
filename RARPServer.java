import java.io.*;
import java.net.*;
import java.util.*;

public class RARPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6000);
            System.out.println("RARP Server started. Waiting for client requests...");
            HashMap<String, String> rarpTable = new HashMap<>();
            rarpTable.put("AA:BB:CC:DD:EE:01", "192.168.1.1");
            rarpTable.put("AA:BB:CC:DD:EE:02", "192.168.1.2");
            rarpTable.put("AA:BB:CC:DD:EE:03", "192.168.1.3");
            while (true) {
                Socket socket = server.accept();
                System.out.println("\nClient connected: " + socket.getInetAddress().getHostAddress());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String mac = dis.readUTF();
                System.out.println("Received MAC Address: " + mac);
                String ip = rarpTable.getOrDefault(mac, "Not Found");
                System.out.println("Mapped IP Address: " + ip);
                dos.writeUTF(ip);
                System.out.println("Response sent to client.");
                dis.close();
                dos.close();
                socket.close();
                System.out.println("Client disconnected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
