import java.io.*;
import java.net.*;
import java.util.*;

public class RARPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter MAC Address: ");
            String mac = sc.nextLine();
            dos.writeUTF(mac);
            String ip = dis.readUTF();
            System.out.println("IP Address: " + ip);
            dis.close();
            dos.close();
            socket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
