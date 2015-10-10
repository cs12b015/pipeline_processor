import java.util.Scanner;
import java.lang.*;
	
public class decode {
	public static String src;
	public String decode(String src){
		src=this.src;
		String ss = src.substring(0,3);
		String s0 = new String("000");
		String s1 = new String("001");
		String s2 = new String("010");
		String s3 = new String("011");
		String s4 = new String("100");
		String s5 = new String("101");
		String s6 = new String("110");
		String s7 = new String("111");
		String output="";
		
		if(ss.compareTo(s0)==0){
			output=parsingIns("ADD",src);
		}
		else if(ss.compareTo(s1)==0){
			output=parsingIns("SUB",src);
		}
		else if(ss.compareTo(s2)==0){
			output=parsingIns("MUL",src);
		}
		else if(ss.compareTo(s3)==0){
			output=parsingIns("LD",src);
		}
		else if(ss.compareTo(s4)==0){
			output=parsingIns("SD",src);
		}
		else if(ss.compareTo(s5)==0){
			output=parsingIns("JMP",src);
		}
		else if(ss.compareTo(s6)==0){
			output=parsingIns("BEQZ",src);
		}
		else if(ss.compareTo(s7)==0){
			output=parsingIns("HLT",src);
		}
		return output;
	}
	public static String parsingIns(String opcode,String src){
		String output;
		if(src.charAt(3)=='1'){
			String imm = src.substring(12,16);
			int r1 = Integer.parseInt(src.substring(4,8),2);
			int r2 = Integer.parseInt(src.substring(8,12),2);
			int immedia = Integer.parseInt(imm,2);
			output=opcode + " R"+ r1 + " R"+ r2 + " "+ immedia;
		}
		else{
			int r1 = Integer.parseInt(src.substring(4,8),2);
			int r2 = Integer.parseInt(src.substring(8,12),2);
			int r3 = Integer.parseInt(src.substring(12,16),2);
			output=opcode + " R" + r1 + " R"+ r2 +" R" + r3;
	 	}
		return output;
	}
}


