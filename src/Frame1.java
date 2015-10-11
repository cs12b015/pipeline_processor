import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;


public class Frame1 {

	private JFrame frame;
	private JButton btnStart;
	private JButton btnNext;
	private JButton btnQuit;
	private String instructionset;
	private JEditorPane instructiontext;
	private JLabel lblIftxt;
	private JLabel lblIdtxt;
	private JLabel lblRdtxt;
	private JLabel lblCyclestxt;
	private JLabel lblAlutxt;
	private JLabel lblMemtxt;
	private JLabel lblWbtxt;
	private JLabel lblCpitxt;
	private JLabel lblStalltxt;
	private JLabel lblStallReasontxt;
	private Integer putstallnumb;
	private Integer cyclecount;
	private Integer JumpFlag=0;
	private ArrayList<Integer> registers =new ArrayList<Integer>();
	private ArrayList<Integer> datacache =new ArrayList<Integer>();
	private ArrayList<Integer> regflags =new ArrayList<Integer>();
	private ArrayList<Integer> checkregflags = new ArrayList<Integer>();
	private ArrayList<String> StallReason = new ArrayList<String>();
	private Integer InstructionCount;
	private ArrayList<String> instructioncache;
	private Queue<String> sixqueue;
	private String content = "";
	private int varpc=0;
	private JLabel lblR_1txt;
	private JLabel lblR_2txt;
	private JLabel lblR_3txt;
	private JLabel lblR_4txt;
	private JLabel lblR_5txt;
	private JLabel lblR_6txt;
	private JLabel lblR_7txt;
	private JLabel lblR_8txt;
	private JLabel lblR_9txt;
	private JLabel lblR_10txt;
	private JLabel lblR_11txt;
	private JLabel lblR_12txt;
	private JLabel lblR_13txt;
	private JLabel lblR_14txt;
	private JLabel lblR_15txt;
	private JLabel lblR_16txt;

	private HashMap<Integer,JLabel> regmap=new HashMap<Integer,JLabel>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Frame1() throws IOException {
		
		InstructionCount=0;
		BufferedReader br = new BufferedReader(new FileReader("testCases/test3.b"));		
        String line =  null;
		while((line=br.readLine())!=null){
		    content=content+"\n"+line;
		    InstructionCount++;
		}
		br.close();
		content=content.trim();
		
		for(int i=0;i<16;i++){
			registers.add(i,0);
		}
		for(int i=0;i<256;i++){
			datacache.add(i,1);
		}
		for(int i=0;i<16;i++){
			regflags.add(i,0);
		}
		putstallnumb=0;
		cyclecount=0;
		initialize();
	}
	
	private void mynextfunc(){
		
		int checkingstall=putstallnumb;
		
		for(int i=0;i<checkregflags.size();i++){
			if(regflags.get(checkregflags.get(i)).equals(0)){
				/*System.out.println(regflags.get(checkregflags.get(i))+"----------"+i+"------------0");*/
				checkregflags.remove(i);
				i--;
			}
		}
		
		
		if(JumpFlag==1){
			putjumpstall();
		}
		else{
			if(!checkregflags.isEmpty()){			
				PutStall(3);
			}
			else{
				sixqueue.remove();
				if(varpc < instructioncache.size()){
					sixqueue.add(instructioncache.get(varpc));
					varpc++;
				}
			}
		}		

		
		ArrayList<String> templist= new ArrayList<String>();
		for (String s : sixqueue){
			templist.add(s);
		}
		
		
		if(templist.size()==0){
			btnNext.setEnabled(false);
			btnQuit.setEnabled(true);
			float temp = (float)cyclecount/InstructionCount;
			lblCpitxt.setText(" "+temp);
		}else{
			cyclecount++;
			lblCyclestxt.setText(" "+cyclecount);
		}
		
		
		
		if(templist.size()>5){
			if(varpc>511){
				System.out.println("PC is greater than 512");
				System.exit(0);
			}
			lblIftxt.setText(templist.get(5));
		}
		else
			lblIftxt.setText("");
		
		
		
		if(templist.size()>4){	
			if(!templist.get(4).trim().equals("")){
				decode idinst = new decode(templist.get(4));
				String temp =idinst.getResult();
				lblIdtxt.setText(temp);
				checkthestall(temp,templist.get(4));
			}
			else{
				lblIdtxt.setText("");
			}
		}
		else
			lblIdtxt.setText("");
		
		
		
		
		if(templist.size()>3){	
			if(!templist.get(3).trim().equals("")){
				String relreg = relregister(templist.get(3));
				lblRdtxt.setText(relreg);
			}
			else{
				lblRdtxt.setText("");
			}
		}
		else
			lblRdtxt.setText("");
		
		if(templist.size()>2){	
			if(!templist.get(2).trim().equals("")){
				decode idinst = new decode(templist.get(2));
				String aluinst = aluregister(idinst.getResult(),templist.get(2));
				lblAlutxt.setText(aluinst);
			}
			else{
				lblAlutxt.setText("");
			}
		}
		else
			lblAlutxt.setText("");
		
		
		if(templist.size()>1){	
			if(!templist.get(1).trim().equals("")){
				decode idinst = new decode(templist.get(1));
				String meminst = memregister(idinst.getResult(),templist.get(1));
				lblMemtxt.setText(meminst);
			}
			else{
				lblMemtxt.setText("");
			}
		}
		else
			lblMemtxt.setText("");
		
		if(templist.size()>0){	
			if(!templist.get(0).trim().equals("")){
				decode idinst = new decode(templist.get(0));
				String wbinst = wbregister(idinst.getResult(),templist.get(0));
				lblWbtxt.setText(wbinst);
			}
			else{
				lblWbtxt.setText("");
			}
		}
		else
			lblWbtxt.setText("");
		
		lblStalltxt.setText(" "+putstallnumb+"");
		
		if (checkingstall==putstallnumb){
			lblStallReasontxt.setText("");
		}
		
		/*System.out.println(putstallnumb);*/
	}
	
	public void PutStall(int num) {
		num=num+1;
        ArrayList<String> array = new ArrayList<String>();
            while(!sixqueue.isEmpty()){
            	array.add(sixqueue.remove());
            }
            int temp=array.size();
            array.add(num, "");
            for(int i=0;i<array.size();i++){
            	sixqueue.add(array.get(i));
            }
            sixqueue.remove();
            
            StallReason.add("RAW");
            putstallnumb++;
            String Temp=StallReason.get(StallReason.size()-1);
            lblStallReasontxt.setText(Temp);
/*            lblStallReasontxt.setText("RAW");
*/    }
	
	public void putjumpstall(){
		ArrayList<String> array = new ArrayList<String>();
        while(!sixqueue.isEmpty()){
        	array.add(sixqueue.remove());
        }
        int temp=array.size();
        array.add(temp, "");

        for(int i=0;i<array.size();i++){
        	sixqueue.add(array.get(i));
        }
        sixqueue.remove();  
        putstallnumb++;
        StallReason.add("CONTROL STALL");
        String Temp=StallReason.get(StallReason.size()-1);
        lblStallReasontxt.setText(Temp);
           
   }      
		
	
	public void checkthestall(String instruction,String bitcode){
		String array[]=instruction.split(" ");
		if(bitcode.charAt(3)=='1'){
			String imm = bitcode.substring(12,16);
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(array[0].compareTo("JMP")==0){
				removeprev();
			}
			else if(regflags.get(r2-1)==1)
			{	
				regflags.set(r1-1, 1);
				checkregflags.add(r2-1);
			}
		}else{			
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			int r3 = Integer.parseInt(bitcode.substring(12,16),2);
			if(array[0].compareTo("LD")==0){
				regflags.set(r1-1, 1);
				if(regflags.get(r2-1)==1)
				{
					checkregflags.add(r2-1);
				}
			}
			else if(array[0].compareTo("SD")==0){
				if(regflags.get(r1-1)==1)
				{
					checkregflags.add(r1-1);
				}
				if(regflags.get(r2-1)==1)
				{
					checkregflags.add(r2-1);
				}
			}
			else if(array[0].compareTo("JMP")==0){
				removeprev();
			}
			else if(array[0].compareTo("BEQZ")==0){
				if(regflags.get(r1-1)==1)
				{
					checkregflags.add(r1-1);
				}
			}
			else if(array[0].compareTo("HLT")==0){
			}
			else {
				regflags.set(r1-1, 1);
				if(regflags.get(r2-1)==1)
				{
					checkregflags.add(r2-1);
				}
				if(regflags.get(r3-1)==1)
				{
					checkregflags.add(r3-1);
				}
			}		
		}	
	}
	
	
	public void removeprev(){
		 ArrayList<String> array = new ArrayList<String>();
         while(!sixqueue.isEmpty()){
         	array.add(sixqueue.remove());
         }
         int temp=array.size();
         array.set(temp-1, "");
         for(int i=0;i<array.size();i++){
         	sixqueue.add(array.get(i));
         }
         JumpFlag=1;
	}
	
	
	public String wbregister(String instruction, String bitcode){
		String output="";
		String[] array= instruction.split(" ");
		
		if(array[0].equals("ADD"))
		{
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				int temp=registers.get(r2-1)+immedia;
				output ="WB : R"+r1+" <-----"+temp;
				regflags.set(r1-1, 0);
				registers.set(r1-1, temp);
				regmap.get(r1).setText(""+temp);
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				int temp=registers.get(r2-1)+registers.get(r3-1);
				output ="WB : R"+r1+" <-----"+temp;
				regflags.set(r1-1, 0);
				registers.set(r1-1, temp);
				regmap.get(r1).setText(""+temp);
			}		
		}else if(array[0].equals("SUB")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				int temp=registers.get(r2-1)-immedia;
				output ="WB : R"+r1+" <-----"+temp;
				regflags.set(r1-1, 0);
				registers.set(r1, temp);
				regmap.get(r1).setText(""+temp);
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				int temp=registers.get(r2-1)-registers.get(r3-1);
				regflags.set(r1-1, 0);
				output ="WB : R"+r1+" <-----"+temp;
				registers.set(r1-1, temp);
				regmap.get(r1).setText(""+temp);
			}
			
		}else if(array[0].equals("MUL")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				int temp=registers.get(r2-1)*immedia;
				output ="WB : R"+r1+" <-----"+temp;
				regflags.set(r1-1, 0);
				registers.set(r1-1, temp);
				regmap.get(r1).setText(""+temp);
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				int temp=registers.get(r2-1)*registers.get(r3-1);
				output ="WB : R"+r1+" <-----"+temp;
				regflags.set(r1-1, 0);
				registers.set(r1-1, temp);
				regmap.get(r1).setText(""+temp);
			}
			
		}else if(array[0].equals("BEQZ")){
			output = instruction;
		}else if(array[0].equals("JMP")){
			output=instruction;
		}else if(array[0].equals("LD")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			regflags.set(r1-1, 0);
			output="WB <-- LOAD";
		}else if(array[0].equals("SD")){
			output="WB <-- STORE";
		}else{
			output=instruction;
		}
		return output;
	}
	
	
	
	
	
	
	
	
	
	public String memregister(String instruction, String bitcode){
		String output="";
		String[] array = instruction.split(" ");
		if(array[0].equals("LD")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			registers.set(r1-1, datacache.get(registers.get(r2-1)));
			output="LOAD R"+r1+" <--- "+datacache.get(registers.get(r2-1));
			
		}else if(array[0].equals("SD")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			datacache.set(r1-1, registers.get(r2-1));
			output="STORE R"+r2+" <--- "+datacache.get(registers.get(r1-1));
		}else if(array[0].equals("JMP")){
			JumpFlag=0;
			int l1 ;
			String ls1 = bitcode.substring(4,12);
			if(bitcode.charAt(4)=='1'){
				l1 = Integer.parseInt(ls1,2)-16;
			}
			else{												
				l1 = Integer.parseInt(ls1,2);
			}
			varpc=varpc-2+l1;
			output=instruction;
		}else{
			output =instruction;
		}
		return output;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String aluregister(String instruction,String bitcode){
		String output="";
		String[] array= instruction.split(" ");
		
		if(array[0].equals("ADD"))
		{
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				output ="ALUOUT <-- "+registers.get(r2-1)+" + "+immedia;
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				output ="ALUOUT <-- "+registers.get(r2-1)+" + "+registers.get(r3-1);
			}		
		}else if(array[0].equals("SUB")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				output ="ALUOUT <-- "+registers.get(r2-1)+" - "+immedia;
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				output ="ALUOUT <-- "+registers.get(r2-1)+" - "+registers.get(r3-1);
			}
			
		}else if(array[0].equals("MUL")){
			int r1 = Integer.parseInt(bitcode.substring(4,8),2);
			int r2 = Integer.parseInt(bitcode.substring(8,12),2);
			if(bitcode.charAt(3)=='1'){
				String imm = bitcode.substring(12,16);
				int immedia ;
				if(bitcode.charAt(12)=='1'){
					immedia = Integer.parseInt(imm,2)-16;
				}
				else{												
					immedia = Integer.parseInt(imm,2);
				}
				output ="ALUOUT <-- "+registers.get(r2-1)+" * "+immedia;
			}else{
				int r3 = Integer.parseInt(bitcode.substring(12,16),2);
				output ="ALUOUT <-- "+registers.get(r2-1)+" * "+registers.get(r3-1);
			}
			
		}else if(array[0].equals("BEQZ")){
			int l1 ;
			String ls1 = bitcode.substring(8,16);
			if(bitcode.charAt(8)=='1'){
				l1 = Integer.parseInt(ls1,2)-16;
			}
			else{												
				l1 = Integer.parseInt(ls1,2);
			}
			int temp=l1+varpc-3;
			output = "AlUOUT <--"+temp;
		}else if(array[0].equals("JMP")){
			int l1 ;
			String ls1 = bitcode.substring(4,12);
			if(bitcode.charAt(4)=='1'){
				l1 = Integer.parseInt(ls1,2)-16;
			}
			else{												
				l1 = Integer.parseInt(ls1,2);
			}
			int temp=l1+varpc-2;
			output="ALUOUT <-- "+temp;
		}else if(array[0].equals("LD")){
			output="ALUOUT <-- LOAD";
		}else if(array[0].equals("SD")){
			output="ALUOUT <-- STORE";
		}else{
			output=instruction;
		}	
		return output;
	}
	
	
	
	
	
	
	public String relregister(String instruction){
		String output="";
		String ss = instruction.substring(0,3);
		String s3 = new String("011");
		String s4 = new String("100");
		String s5 = new String("101");
		String s6 = new String("110");
		String s7 = new String("111");
		
		if(instruction.charAt(3)=='1'){
			int r2 = Integer.parseInt(instruction.substring(8,12),2);
			output="READ R"+r2+" ==> "+registers.get(r2-1);
		}
		else{
			int r1 = Integer.parseInt(instruction.substring(4,8),2);
			int r2 = Integer.parseInt(instruction.substring(8,12),2);
			int r3 = Integer.parseInt(instruction.substring(12,16),2);
			if(ss.compareTo(s3)==0){
				output = "READ R"+r2+" ==> "+registers.get(r2-1);
			}
			else if(ss.compareTo(s4)==0){
				output = "READ R"+r1+" ==> "+registers.get(r1-1);
			}
			else if(ss.compareTo(s5)==0){
				int l1 ;
				String ls1 = instruction.substring(4,12);
				if(instruction.charAt(4)=='1'){
					l1 = Integer.parseInt(ls1,2)-16;
				}
				else{												
					l1 = Integer.parseInt(ls1,2);
				}
				output = "JMP" + " " + l1;
			}
			else if(ss.compareTo(s6)==0){
				output = "READ R"+r1+" ==> "+registers.get(r1-1);
			}
			else if(ss.compareTo(s7)==0){
				output = "HLT" ;
			}
			else {
				output="READ R"+ r2+" ==> "+registers.get(r2-1) +" AND "+"R" + r3+" ==> "+registers.get(r3-1);
			}
	 	}
		return output;
	}
	
	
	
	private void myfunc(){
		instructionset=	instructiontext.getText();
		String[] arr= instructionset.split("\n");
		instructioncache=new ArrayList<String>();
		for(int i =0;i<arr.length;i++){
			instructioncache.add(arr[i]);
		}
		/*System.out.println(queue);*/
		btnStart.setEnabled(false);
		btnNext.setEnabled(true);
		mynextfunc();
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		sixqueue=new LinkedList<String>();
		for(int i=0;i<6;i++){
			sixqueue.add("");
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHead= new JLabel("Scalar Pipelined Processor");
		lblHead.setFont(new Font("Abyssinica SIL", Font.PLAIN, 28));
		lblHead.setBounds(260, 17, 400, 40);
		frame.getContentPane().add(lblHead);
		
		instructiontext = new JEditorPane();
		instructiontext.setFont(new Font("Dialog", Font.BOLD, 15));
		instructiontext.setBounds(40, 72, 250, 220);
		instructiontext.setText(content);
		frame.getContentPane().add(instructiontext);
		
		
		
		
		
		 btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myfunc();
			}
		});
		btnStart.setBounds(480, 455, 87, 25);
		frame.getContentPane().add(btnStart);
		
		 btnNext = new JButton("Next");
		 btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!sixqueue.isEmpty())
				mynextfunc();
				else{
					btnNext.setEnabled(false);
				}
			}
		});
		 btnNext.setEnabled(false);
		 btnNext.setBounds(400, 455, 87, 25);
		frame.getContentPane().add(btnNext);
		
		
		 btnQuit = new JButton("Exit");
		 btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		 btnQuit.setEnabled(false);
		 btnQuit.setBounds(315, 455, 87, 25);
		frame.getContentPane().add(btnQuit);
		
		JLabel lblIf = new JLabel("IF");
		lblIf.setBounds(301, 70, 30, 15);
		frame.getContentPane().add(lblIf);
		
		JLabel lblId= new JLabel("ID");
		lblId.setBounds(301, 120, 30, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblRd = new JLabel("RD");
		lblRd.setBounds(301, 170, 30, 15);
		frame.getContentPane().add(lblRd);
		
		JLabel lblAlu= new JLabel("ALU");
		lblAlu.setBounds(301, 220, 40, 15);
		frame.getContentPane().add(lblAlu);
		
		JLabel lblMem = new JLabel("MEM");
		lblMem.setBounds(301, 270, 40, 15);
		frame.getContentPane().add(lblMem);
		
		JLabel lblWb= new JLabel("WB");
		lblWb.setBounds(301, 320, 30, 15);
		frame.getContentPane().add(lblWb);
		
		lblIftxt = new JLabel("");
		lblIftxt.setOpaque(true);
		lblIftxt.setBackground(Color.white);
		lblIftxt.setBounds(365, 65, 220, 25);
		frame.getContentPane().add(lblIftxt);
		
		lblIdtxt = new JLabel("");
		lblIdtxt.setOpaque(true);
		lblIdtxt.setBackground(Color.white);
		lblIdtxt.setBounds(365, 115, 220, 25);
		frame.getContentPane().add(lblIdtxt);
		
		lblRdtxt = new JLabel("");
		lblRdtxt.setOpaque(true);
		lblRdtxt.setBackground(Color.white);
		lblRdtxt.setBounds(365, 165, 220, 25);
		frame.getContentPane().add(lblRdtxt);
		
		lblAlutxt = new JLabel("");
		lblAlutxt.setOpaque(true);
		lblAlutxt.setBackground(Color.white);
		lblAlutxt.setBounds(365, 215, 220, 25);
		frame.getContentPane().add(lblAlutxt);
		
		lblMemtxt = new JLabel("");
		lblMemtxt.setOpaque(true);
		lblMemtxt.setBackground(Color.white);
		lblMemtxt.setBounds(365, 265, 220, 25);
		frame.getContentPane().add(lblMemtxt);
		
		lblWbtxt = new JLabel("");
		lblWbtxt.setOpaque(true);
		lblWbtxt.setBackground(Color.white);
		lblWbtxt.setBounds(365, 315, 220, 25);
		frame.getContentPane().add(lblWbtxt);
		
		JLabel lblCpi= new JLabel("CPI");
		lblCpi.setBounds(40, 330, 40, 15);
		frame.getContentPane().add(lblCpi);
		
		lblCpitxt = new JLabel("");
		lblCpitxt.setOpaque(true);
		lblCpitxt.setBackground(Color.white);
		lblCpitxt.setBounds(170, 325, 120, 25);
		frame.getContentPane().add(lblCpitxt);
		
		JLabel lblCycles= new JLabel("CYCLES");
		lblCycles.setBounds(40, 360, 160, 25);
		frame.getContentPane().add(lblCycles);
		
		lblCyclestxt = new JLabel("");
		lblCyclestxt.setOpaque(true);
		lblCyclestxt.setBackground(Color.white);
		lblCyclestxt.setBounds(170, 355, 120, 25);
		frame.getContentPane().add(lblCyclestxt);
		
		JLabel lblStall= new JLabel("NUM OF STALLS");
		lblStall.setBounds(40, 390, 160, 25);
		frame.getContentPane().add(lblStall);
		
		lblStalltxt = new JLabel("");
		lblStalltxt.setOpaque(true);
		lblStalltxt.setBackground(Color.white);
		lblStalltxt.setBounds(170, 385, 120, 25);
		frame.getContentPane().add(lblStalltxt);
		
		JLabel lblStallReason= new JLabel("STALL REASON");
		lblStallReason.setBounds(40, 420, 160, 25);
		frame.getContentPane().add(lblStallReason);
		
		lblStallReasontxt = new JLabel("");
		lblStallReasontxt.setOpaque(true);
		lblStallReasontxt.setBackground(Color.white);
		lblStallReasontxt.setBounds(170, 415, 120, 25);
		frame.getContentPane().add(lblStallReasontxt);
		
		JLabel lblR_1 = new JLabel("R1");
		lblR_1.setBounds(608, 70, 30, 15);
		frame.getContentPane().add(lblR_1);
		
		lblR_1txt = new JLabel("0");
		lblR_1txt.setOpaque(true);
		lblR_1txt.setBackground(Color.cyan);
		lblR_1txt.setBounds(640, 65, 60, 25);
		frame.getContentPane().add(lblR_1txt);
		
		JLabel lblR_2 = new JLabel("R2");
		lblR_2.setBounds(608, 120, 30, 15);
		frame.getContentPane().add(lblR_2);
		
		lblR_2txt = new JLabel("0");
		lblR_2txt.setOpaque(true);
		lblR_2txt.setBackground(Color.cyan);
		lblR_2txt.setBounds(640, 115, 60, 25);
		frame.getContentPane().add(lblR_2txt);
		
		JLabel lblR_3 = new JLabel("R3");
		lblR_3.setBounds(608, 170, 30, 15);
		frame.getContentPane().add(lblR_3);
		
		lblR_3txt = new JLabel("0");
		lblR_3txt.setOpaque(true);
		lblR_3txt.setBackground(Color.cyan);
		lblR_3txt.setBounds(640, 165, 60, 25);
		frame.getContentPane().add(lblR_3txt);
		
		JLabel lblR_4 = new JLabel("R4");
		lblR_4.setBounds(608, 220, 30, 15);
		frame.getContentPane().add(lblR_4);
		
		lblR_4txt = new JLabel("0");
		lblR_4txt.setOpaque(true);
		lblR_4txt.setBackground(Color.cyan);
		lblR_4txt.setBounds(640, 215, 60, 25);
		frame.getContentPane().add(lblR_4txt);
		
		JLabel lblR_5 = new JLabel("R5");
		lblR_5.setBounds(608, 270, 30, 15);
		frame.getContentPane().add(lblR_5);
		
		lblR_5txt = new JLabel("0");
		lblR_5txt.setOpaque(true);
		lblR_5txt.setBackground(Color.cyan);
		lblR_5txt.setBounds(640, 265, 60, 25);
		frame.getContentPane().add(lblR_5txt);
		
		JLabel lblR_6 = new JLabel("R6");
		lblR_6.setBounds(608, 320, 30, 15);
		frame.getContentPane().add(lblR_6);
		
		lblR_6txt = new JLabel("0");
		lblR_6txt.setOpaque(true);
		lblR_6txt.setBackground(Color.cyan);
		lblR_6txt.setBounds(640, 315, 60, 25);
		frame.getContentPane().add(lblR_6txt);
		
		JLabel lblR_7 = new JLabel("R7");
		lblR_7.setBounds(608, 375, 30, 15);
		frame.getContentPane().add(lblR_7);
		
		lblR_7txt = new JLabel("0");
		lblR_7txt.setOpaque(true);
		lblR_7txt.setBackground(Color.cyan);
		lblR_7txt.setBounds(640, 365, 60, 25);
		frame.getContentPane().add(lblR_7txt);
		
		JLabel lblR_8 = new JLabel("R8");
		lblR_8.setBounds(608, 420, 30, 15);
		frame.getContentPane().add(lblR_8);
		
		lblR_8txt = new JLabel("0");
		lblR_8txt.setOpaque(true);
		lblR_8txt.setBackground(Color.cyan);
		lblR_8txt.setBounds(640, 415, 60, 25);
		frame.getContentPane().add(lblR_8txt);
		
		JLabel lblR_9 = new JLabel("R9");
		lblR_9.setBounds(720, 70, 30, 15);
		frame.getContentPane().add(lblR_9);
		
		lblR_9txt = new JLabel("0");
		lblR_9txt.setOpaque(true);
		lblR_9txt.setBackground(Color.cyan);
		lblR_9txt.setBounds(750, 65, 60, 25);
		frame.getContentPane().add(lblR_9txt);
		
		JLabel lblR_10 = new JLabel("R10");
		lblR_10.setBounds(720, 120, 30, 15);
		frame.getContentPane().add(lblR_10);
		
		lblR_10txt = new JLabel("0");
		lblR_10txt.setOpaque(true);
		lblR_10txt.setBackground(Color.cyan);
		lblR_10txt.setBounds(750, 115, 60, 25);
		frame.getContentPane().add(lblR_10txt);
		
		JLabel lblR_11 = new JLabel("R11");
		lblR_11.setBounds(720, 170, 30, 15);
		frame.getContentPane().add(lblR_11);
		
		lblR_11txt = new JLabel("0");
		lblR_11txt.setOpaque(true);
		lblR_11txt.setBackground(Color.cyan);
		lblR_11txt.setBounds(750, 165, 60, 25);
		frame.getContentPane().add(lblR_11txt);
		
		JLabel lblR_12 = new JLabel("R12");
		lblR_12.setBounds(720, 220, 30, 15);
		frame.getContentPane().add(lblR_12);
		
		lblR_12 = new JLabel("0");
		lblR_12.setOpaque(true);
		lblR_12.setBackground(Color.cyan);
		lblR_12.setBounds(750, 215, 60, 25);
		frame.getContentPane().add(lblR_12);
		
		JLabel lblR_13 = new JLabel("R13");
		lblR_13.setBounds(720, 270, 30, 15);
		frame.getContentPane().add(lblR_13);
		
		lblR_13txt = new JLabel("0");
		lblR_13txt.setOpaque(true);
		lblR_13txt.setBackground(Color.cyan);
		lblR_13txt.setBounds(750, 265, 60, 25);
		frame.getContentPane().add(lblR_13txt);
		
		JLabel lblR_14 = new JLabel("R14");
		lblR_14.setBounds(720, 320, 30, 15);
		frame.getContentPane().add(lblR_14);
		
		lblR_14txt = new JLabel("0");
		lblR_14txt.setOpaque(true);
		lblR_14txt.setBackground(Color.cyan);
		lblR_14txt.setBounds(750, 315, 60, 25);
		frame.getContentPane().add(lblR_14txt);
		
		JLabel lblR_15 = new JLabel("R15");
		lblR_15.setBounds(720, 375, 30, 15);
		frame.getContentPane().add(lblR_15);
		
		lblR_15txt = new JLabel("0");
		lblR_15txt.setOpaque(true);
		lblR_15txt.setBackground(Color.cyan);
		lblR_15txt.setBounds(750, 365, 60, 25);
		frame.getContentPane().add(lblR_15txt);
		
		JLabel lblR_16 = new JLabel("R16");
		lblR_16.setBounds(720, 420, 30, 15);
		frame.getContentPane().add(lblR_16);
		
		lblR_16txt = new JLabel("0");
		lblR_16txt.setOpaque(true);
		lblR_16txt.setBackground(Color.cyan);
		lblR_16txt.setBounds(750, 415, 60, 25);
		frame.getContentPane().add(lblR_16txt);
		regmap.put(1, lblR_1txt);
		regmap.put(2, lblR_2txt);
		regmap.put(3, lblR_3txt);
		regmap.put(4, lblR_4txt);
		regmap.put(5, lblR_5txt);
		regmap.put(6, lblR_6txt);
		regmap.put(7, lblR_7txt);
		regmap.put(8, lblR_8txt);
		regmap.put(9, lblR_9txt);
		regmap.put(10, lblR_10txt);
		regmap.put(11, lblR_11txt);
		regmap.put(12, lblR_12txt);
		regmap.put(13, lblR_13txt);
		regmap.put(14, lblR_14txt);
		regmap.put(15, lblR_15txt);
		regmap.put(16, lblR_16txt);
		
		
	}
}





