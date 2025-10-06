import java.io.*;
import java.net.*;
import java.util.*;

public class ARPServer{
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            HashMap<String, String> arpTable = new HashMap<>();
            arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
            arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
            arpTable.put("192.168.1.3", "AA:BB:CC:DD:EE:03");

            while (true) {
                Socket socket = server.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String ip = dis.readUTF();
                String mac = arpTable.getOrDefault(ip, "Not Found");
                dos.writeUTF(mac);
                dis.close();
                dos.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
