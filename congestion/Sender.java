import java.io.*;
import java.net.*;
import java.util.*;

public class Sender {
    Socket sender;
    ObjectOutputStream dos;
    ObjectInputStream din;
    int n,i=0,sequence=0;
    String msg,packet,ack;
    public void run(){
        try {
            sender=new Socket("localhost",2000);
            dos=new ObjectOutputStream(sender.getOutputStream());
            dos.flush();
            din=new ObjectInputStream(sender.getInputStream());

            String str=(String)din.readObject();
            System.out.println(str);

            Scanner sc=new Scanner(System.in);
            System.out.print("Enter the packet data: ");
            packet=sc.nextLine();
            n=packet.length();
            while (i<=n) {
                if(i<n){
                    msg=sequence+packet.substring(i,i+1);
                }else{
                    msg="end";
                }
                dos.writeObject(msg);
                dos.flush();
                ack=(String)din.readObject();
                System.out.println("Acknowledgement from receiver: "+ack);
                if(ack.equals(String.valueOf((sequence+1)%2))){
                    sequence=(sequence+1)%2;
                    i++;
                    System.out.println("Packet "+msg+" sent successfully.");
                }else{
                    System.out.println("Packet "+msg+" not sent successfully. Resending...");
                }
            }
            System.out.println("All packets sent successfully.");
            sender.close();
            sc.close();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        Sender s=new Sender();
        s.run();
    }
}
