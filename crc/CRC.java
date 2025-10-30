import java.util.*;

public class CRC {
    static String divide(String dividend,String divisor){
        int l=divisor.length();
	String temp=dividend.substring(0,l);
	for(int i=0;i<dividend.length()-l+1;i++){
	    StringBuilder result=new StringBuilder();
	    for(int j=0;j<l;j++){
		result.append(temp.charAt(j)==divisor.charAt(j)?'0':'1');
	    }
	    temp=result.substring(1);
	    if(i+l<dividend.length()){
		temp+=dividend.charAt(i+l);
	    }
	}
	return temp;
    }
    public static void main(String args[]){
	Scanner sin=new Scanner(System.in);
	System.out.print("Enter the generator:");
	String gen=sin.nextLine();
	System.out.print("Enter the data:");
	String data=sin.nextLine();
	String code=data+"0".repeat(gen.length()-1);
	String remainder=divide(code,gen);

	String transmitted=data+remainder;
	System.out.println("Transmitted Code:"+transmitted);
	System.out.println("Enter the received code:");
	String received=sin.nextLine();
	String res=divide(received,gen);
	if(res.contains("1")){
		System.out.println("Error");
	}else{
	        System.out.println("No Error");
	}
   }
}
