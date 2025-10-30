import java.util.Random;
import java.util.Scanner;

public class AIMD{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int cwnd;
        System.out.print("Enter initial congestion window size (cwnd): ");
        cwnd=scanner.nextInt();
        double ackProb;
        System.out.print("Enter acknowledgment probability (between 0 and 1): ");
        ackProb=scanner.nextDouble();
        double lossProb;
        System.out.print("Enter loss probability (between 0 and 100): ");
        lossProb=scanner.nextDouble();

        int ssthresh=10;
        while (true) {
            System.out.println("Current cwnd: " + cwnd+", Acknowledgment Probability: "+ackProb+", Loss Probability: "+lossProb);
            for(int i=0;i<cwnd;i++){
                System.out.print("Packet "+(i+1)+" sent successfully.");
            }
            Random random = new Random();
            double randAck = random.nextDouble();
            double randLoss = random.nextDouble();
            if(randAck<=ackProb && randLoss>lossProb){
                if(cwnd<ssthresh){
                    cwnd*=2;
                    System.out.println("Slow Start: cwnd doubled to " + cwnd);
                }else{
                    cwnd+=1;
                    System.out.println("Congestion Avoidance: cwnd increased by 1 to " + cwnd);
                }
            }
            if(randLoss<=lossProb){
                ssthresh=cwnd/2;
                cwnd=Math.max(1,(cwnd/2));
                System.out.println("Packet loss detected. ssthresh set to " + ssthresh + ", cwnd reset to 1.");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}