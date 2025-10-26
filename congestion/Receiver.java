import java.io.*;
import java.net.*;

public class Receiver {
    Socket sender;
    ObjectOutputStream dos;
    ObjectInputStream din;
    String ack,packet,data="";
    int sequence=0;
    public void run(){
        try {
            ServerSocket receiver=new ServerSocket(2000);
            System.out.println("Waiting for sender to connect...");
            sender=receiver.accept();
            din=new ObjectInputStream(sender.getInputStream());
            dos=new ObjectOutputStream(sender.getOutputStream());

            dos.writeObject("Connection established with receiver.");
            dos.flush();
            do { 
                packet=(String)din.readObject();
                if(packet.equals("end")){
                    break;
                }
                System.out.println("Packet received: "+packet);
                int rev=Integer.parseInt(packet.substring(0,1));
                if(rev==sequence){
                    data+=packet.substring(1);
                    System.out.println("Packet :"+packet);
                    sequence=(sequence+1)%2;
                }else{
                    System.out.println("Duplicate packet received");
                }
                ack=String.valueOf(sequence);
                dos.writeObject(ack);
                dos.flush();
            
            } while (!packet.equals("end"));
            System.out.println("All packets received successfully.");
            System.out.println("Data received: "+data);
            sender.close();
            receiver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Receiver r=new Receiver();
        r.run();
    }
}
