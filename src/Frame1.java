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
	private JLabel lblAlutxt;
	private JLabel lblMemtxt;
	private JLabel lblWbtxt;
	private JLabel lblCpitxt;
	private JLabel lblStalltxt;
	private JLabel lblStallReasontxt;
	private ArrayList<Integer> registers =new ArrayList<Integer>();
	private ArrayList<Integer> datacache =new ArrayList<Integer>();
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
		
		BufferedReader br = new BufferedReader(new FileReader("testCases/test1.b"));		
        String line =  null;
		while((line=br.readLine())!=null){
		    content=content+"\n"+line;
		}
		br.close();
		content=content.trim();
		initialize();
	}
	
	private void mynextfunc(){
		sixqueue.remove();
		if(varpc < instructioncache.size()){
			sixqueue.add(instructioncache.get(varpc));
			varpc++;
		}
		ArrayList<String> templist= new ArrayList<String>();
		for (String s : sixqueue){
			templist.add(s);
		}
		if(templist.size()==0){
			btnNext.setEnabled(false);
			btnQuit.setEnabled(true);
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
			}
			else{
				lblIdtxt.setText("");
			}
		}
		else
			lblIdtxt.setText("");
		
		if(templist.size()>3){	
			if(!templist.get(3).trim().equals("")){
				decode idinst = new decode(templist.get(3));
				lblRdtxt.setText(idinst.getResult());
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
				lblAlutxt.setText(idinst.getResult());
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
				lblMemtxt.setText(idinst.getResult());
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
				lblWbtxt.setText(idinst.getResult());
			}
			else{
				lblWbtxt.setText("");
			}
		}
		else
			lblWbtxt.setText("");
		
		
		
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
		lblIftxt.setBounds(365, 65, 200, 25);
		frame.getContentPane().add(lblIftxt);
		
		lblIdtxt = new JLabel("");
		lblIdtxt.setOpaque(true);
		lblIdtxt.setBackground(Color.white);
		lblIdtxt.setBounds(365, 115, 200, 25);
		frame.getContentPane().add(lblIdtxt);
		
		lblRdtxt = new JLabel("");
		lblRdtxt.setOpaque(true);
		lblRdtxt.setBackground(Color.white);
		lblRdtxt.setBounds(365, 165, 200, 25);
		frame.getContentPane().add(lblRdtxt);
		
		lblAlutxt = new JLabel("");
		lblAlutxt.setOpaque(true);
		lblAlutxt.setBackground(Color.white);
		lblAlutxt.setBounds(365, 215, 200, 25);
		frame.getContentPane().add(lblAlutxt);
		
		lblMemtxt = new JLabel("");
		lblMemtxt.setOpaque(true);
		lblMemtxt.setBackground(Color.white);
		lblMemtxt.setBounds(365, 265, 200, 25);
		frame.getContentPane().add(lblMemtxt);
		
		lblWbtxt = new JLabel("");
		lblWbtxt.setOpaque(true);
		lblWbtxt.setBackground(Color.white);
		lblWbtxt.setBounds(365, 315, 200, 25);
		frame.getContentPane().add(lblWbtxt);
		
		JLabel lblCpi= new JLabel("CPI");
		lblCpi.setBounds(40, 330, 40, 15);
		frame.getContentPane().add(lblCpi);
		
		lblCpitxt = new JLabel("");
		lblCpitxt.setOpaque(true);
		lblCpitxt.setBackground(Color.white);
		lblCpitxt.setBounds(170, 325, 120, 25);
		frame.getContentPane().add(lblCpitxt);
		
		JLabel lblStall= new JLabel("NUM OF STALLS");
		lblStall.setBounds(40, 360, 160, 25);
		frame.getContentPane().add(lblStall);
		
		lblStalltxt = new JLabel("");
		lblStalltxt.setOpaque(true);
		lblStalltxt.setBackground(Color.white);
		lblStalltxt.setBounds(170, 355, 120, 25);
		frame.getContentPane().add(lblStalltxt);
		
		JLabel lblStallReason= new JLabel("STALL REASON");
		lblStallReason.setBounds(40, 390, 160, 25);
		frame.getContentPane().add(lblStallReason);
		
		lblStallReasontxt = new JLabel("");
		lblStallReasontxt.setOpaque(true);
		lblStallReasontxt.setBackground(Color.white);
		lblStallReasontxt.setBounds(170, 385, 120, 25);
		frame.getContentPane().add(lblStallReasontxt);
		
		JLabel lblR_1 = new JLabel("R1");
		lblR_1.setBounds(608, 70, 30, 15);
		frame.getContentPane().add(lblR_1);
		
		lblR_1txt = new JLabel("");
		lblR_1txt.setOpaque(true);
		lblR_1txt.setBackground(Color.cyan);
		lblR_1txt.setBounds(640, 65, 60, 25);
		frame.getContentPane().add(lblR_1txt);
		
		JLabel lblR_2 = new JLabel("R2");
		lblR_2.setBounds(608, 120, 30, 15);
		frame.getContentPane().add(lblR_2);
		
		lblR_2txt = new JLabel("");
		lblR_2txt.setOpaque(true);
		lblR_2txt.setBackground(Color.cyan);
		lblR_2txt.setBounds(640, 115, 60, 25);
		frame.getContentPane().add(lblR_2txt);
		
		JLabel lblR_3 = new JLabel("R3");
		lblR_3.setBounds(608, 170, 30, 15);
		frame.getContentPane().add(lblR_3);
		
		lblR_3txt = new JLabel("");
		lblR_3txt.setOpaque(true);
		lblR_3txt.setBackground(Color.cyan);
		lblR_3txt.setBounds(640, 165, 60, 25);
		frame.getContentPane().add(lblR_3txt);
		
		JLabel lblR_4 = new JLabel("R4");
		lblR_4.setBounds(608, 220, 30, 15);
		frame.getContentPane().add(lblR_4);
		
		lblR_4txt = new JLabel("");
		lblR_4txt.setOpaque(true);
		lblR_4txt.setBackground(Color.cyan);
		lblR_4txt.setBounds(640, 215, 60, 25);
		frame.getContentPane().add(lblR_4txt);
		
		JLabel lblR_5 = new JLabel("R5");
		lblR_5.setBounds(608, 270, 30, 15);
		frame.getContentPane().add(lblR_5);
		
		lblR_5txt = new JLabel("");
		lblR_5txt.setOpaque(true);
		lblR_5txt.setBackground(Color.cyan);
		lblR_5txt.setBounds(640, 265, 60, 25);
		frame.getContentPane().add(lblR_5txt);
		
		JLabel lblR_6 = new JLabel("R6");
		lblR_6.setBounds(608, 320, 30, 15);
		frame.getContentPane().add(lblR_6);
		
		lblR_6txt = new JLabel("");
		lblR_6txt.setOpaque(true);
		lblR_6txt.setBackground(Color.cyan);
		lblR_6txt.setBounds(640, 315, 60, 25);
		frame.getContentPane().add(lblR_6txt);
		
		JLabel lblR_7 = new JLabel("R7");
		lblR_7.setBounds(608, 375, 30, 15);
		frame.getContentPane().add(lblR_7);
		
		lblR_7txt = new JLabel("");
		lblR_7txt.setOpaque(true);
		lblR_7txt.setBackground(Color.cyan);
		lblR_7txt.setBounds(640, 365, 60, 25);
		frame.getContentPane().add(lblR_7txt);
		
		JLabel lblR_8 = new JLabel("R8");
		lblR_8.setBounds(608, 420, 30, 15);
		frame.getContentPane().add(lblR_8);
		
		lblR_8txt = new JLabel("");
		lblR_8txt.setOpaque(true);
		lblR_8txt.setBackground(Color.cyan);
		lblR_8txt.setBounds(640, 415, 60, 25);
		frame.getContentPane().add(lblR_8txt);
		
		JLabel lblR_9 = new JLabel("R9");
		lblR_9.setBounds(720, 70, 30, 15);
		frame.getContentPane().add(lblR_9);
		
		lblR_9txt = new JLabel("");
		lblR_9txt.setOpaque(true);
		lblR_9txt.setBackground(Color.cyan);
		lblR_9txt.setBounds(750, 65, 60, 25);
		frame.getContentPane().add(lblR_9txt);
		
		JLabel lblR_10 = new JLabel("R10");
		lblR_10.setBounds(720, 120, 30, 15);
		frame.getContentPane().add(lblR_10);
		
		lblR_10txt = new JLabel("");
		lblR_10txt.setOpaque(true);
		lblR_10txt.setBackground(Color.cyan);
		lblR_10txt.setBounds(750, 115, 60, 25);
		frame.getContentPane().add(lblR_10txt);
		
		JLabel lblR_11 = new JLabel("R11");
		lblR_11.setBounds(720, 170, 30, 15);
		frame.getContentPane().add(lblR_11);
		
		lblR_11txt = new JLabel("");
		lblR_11txt.setOpaque(true);
		lblR_11txt.setBackground(Color.cyan);
		lblR_11txt.setBounds(750, 165, 60, 25);
		frame.getContentPane().add(lblR_11txt);
		
		JLabel lblR_12 = new JLabel("R12");
		lblR_12.setBounds(720, 220, 30, 15);
		frame.getContentPane().add(lblR_12);
		
		lblR_12 = new JLabel("");
		lblR_12.setOpaque(true);
		lblR_12.setBackground(Color.cyan);
		lblR_12.setBounds(750, 215, 60, 25);
		frame.getContentPane().add(lblR_12);
		
		JLabel lblR_13 = new JLabel("R13");
		lblR_13.setBounds(720, 270, 30, 15);
		frame.getContentPane().add(lblR_13);
		
		lblR_13txt = new JLabel("");
		lblR_13txt.setOpaque(true);
		lblR_13txt.setBackground(Color.cyan);
		lblR_13txt.setBounds(750, 265, 60, 25);
		frame.getContentPane().add(lblR_13txt);
		
		JLabel lblR_14 = new JLabel("R14");
		lblR_14.setBounds(720, 320, 30, 15);
		frame.getContentPane().add(lblR_14);
		
		lblR_14txt = new JLabel("");
		lblR_14txt.setOpaque(true);
		lblR_14txt.setBackground(Color.cyan);
		lblR_14txt.setBounds(750, 315, 60, 25);
		frame.getContentPane().add(lblR_14txt);
		
		JLabel lblR_15 = new JLabel("R15");
		lblR_15.setBounds(720, 375, 30, 15);
		frame.getContentPane().add(lblR_15);
		
		lblR_15txt = new JLabel("");
		lblR_15txt.setOpaque(true);
		lblR_15txt.setBackground(Color.cyan);
		lblR_15txt.setBounds(750, 365, 60, 25);
		frame.getContentPane().add(lblR_15txt);
		
		JLabel lblR_16 = new JLabel("R16");
		lblR_16.setBounds(720, 420, 30, 15);
		frame.getContentPane().add(lblR_16);
		
		lblR_16txt = new JLabel("");
		lblR_16txt.setOpaque(true);
		lblR_16txt.setBackground(Color.cyan);
		lblR_16txt.setBounds(750, 415, 60, 25);
		frame.getContentPane().add(lblR_16txt);
		
	}
}
