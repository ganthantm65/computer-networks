
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket client=new Socket("localhost",3200);
            System.out.println("Connected to Server");
            DataInputStream din=new DataInputStream(client.getInputStream());
            DataOutputStream dout=new DataOutputStream(client.getOutputStream());
            Scanner scanner=new Scanner(System.in);
            String str,str2;
            System.out.println("Enter a message to pass:");
            str=scanner.nextLine();
            dout.writeUTF(str);
            str2=din.readUTF();
            System.out.println("Message from Server: "+str2);
        } catch (IOException e) {
        }
    }
}
