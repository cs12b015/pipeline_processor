import java.util.Scanner;
import java.lang.*;
public class decode{
	public static void main(String args[]){
		String src;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a String");
		src = in.nextLine();
		System.out.println("Entered String is " + src);
		int len = src.length();
		if(len != 16){
			System.out.println("Exit as entered String is not 16 bits");
			System.exit(0);
		}
		System.out.println("String Length is "+ len);	
		String ss = src.substring(0,3);
		String s0 = new String("000");
		String s1 = new String("001");
		String s2 = new String("010");
		String s3 = new String("011");
		String s4 = new String("100");
		String s5 = new String("101");
		String s6 = new String("110");
		String s7 = new String("111");
		if(ss.compareTo(s0)==0){
			parsingIns("ADD",src);
		}
		else if(ss.compareTo(s1)==0){
			parsingIns("SUB",src);
		}
		else if(ss.compareTo(s2)==0){
			parsingIns("MUL",src);
		}
		else if(ss.compareTo(s3)==0){
			parsingIns("LD",src);
		}
		else if(ss.compareTo(s4)==0){
			parsingIns("SD",src);
		}
		else if(ss.compareTo(s5)==0){
			parsingIns("JMP",src);
		}
		else if(ss.compareTo(s6)==0){
			parsingIns("BEQZ",src);
		}
		else if(ss.compareTo(s7)==0){
			parsingIns("HLT",src);
		}
	}
	public static void parsingIns(String opcode,String src){
		if(src.charAt(3)=='1'){
			String imm = src.substring(12,16);
			int r1 = Integer.parseInt(src.substring(4,8),2);
			int r2 = Integer.parseInt(src.substring(8,12),2);
			int immedia = Integer.parseInt(imm,2);
			System.out.println(opcode + " R"+ r1 + " R"+ r2 + " "+ immedia);
		}
		else{
			int r1 = Integer.parseInt(src.substring(4,8),2);
			int r2 = Integer.parseInt(src.substring(8,12),2);
			int r3 = Integer.parseInt(src.substring(12,16),2);
			System.out.println(opcode + " R" + r1 + " R"+ r2 +" R" + r3);
	 	}
	}
}
