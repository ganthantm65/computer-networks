package tcp;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket=new ServerSocket(3200);
            System.out.println("Server is waiting for client request");
            Socket socket=serverSocket.accept();
            DataInputStream din=new DataInputStream(socket.getInputStream());
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
            String str,str2;
            str=din.readUTF();
            System.out.print("Message from client:"+str);
            str2="Hello Client";
            dout.writeUTF(str2);
        } catch (Exception e) {
        }
    }
}