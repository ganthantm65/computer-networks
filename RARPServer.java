import java.io.*;
import java.net.*;
import java.util.*;

public class RARPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6000);
            HashMap<String, String> rarpTable = new HashMap<>();
            rarpTable.put("AA:BB:CC:DD:EE:01", "192.168.1.1");
            rarpTable.put("AA:BB:CC:DD:EE:02", "192.168.1.2");
            rarpTable.put("AA:BB:CC:DD:EE:03", "192.168.1.3");
            while (true) {
                Socket socket = server.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String mac = dis.readUTF();
                String ip = rarpTable.getOrDefault(mac, "Not Found");
                dos.writeUTF(ip);
                dis.close();
                dos.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
