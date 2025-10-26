import java.io.*;
import java.net.*;
import java.util.*;

public class ARPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter IP Address: ");
            String ip = sc.nextLine();
            dos.writeUTF(ip);
            String mac = dis.readUTF();
            System.out.println("MAC Address: " + mac);
            dis.close();
            dos.close();
            socket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
