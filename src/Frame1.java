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
	private Queue<String> queue;
	private Queue<String> sixqueue;
	private String content = "";
	

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
		content=content.trim();
		initialize();
	}
	
	private void mynextfunc(){
		sixqueue.remove();
		if(!queue.isEmpty()){
			sixqueue.add(queue.peek());
			queue.remove();
		}
		ArrayList<String> templist= new ArrayList<String>();
		for (String s : sixqueue){
			templist.add(s);
		}
		if(templist.size()==0){
			btnNext.setEnabled(false);
			btnQuit.setEnabled(true);
		}
		
		/*System.out.println(templist);*/
		if(templist.size()>5)
			lblIftxt.setText(templist.get(5));
		else
			lblIftxt.setText("");
		if(templist.size()>4)
		{
			
			lblIdtxt.setText(templist.get(4));
		}
		else
			lblIdtxt.setText("");
		if(templist.size()>3)
		lblRdtxt.setText(templist.get(3));
		else
			lblRdtxt.setText("");
		if(templist.size()>2)
		lblAlutxt.setText(templist.get(2));
		else
			lblAlutxt.setText("");
		if(templist.size()>1)
		lblMemtxt.setText(templist.get(1));
		else
			lblMemtxt.setText("");
		
		if(templist.size()>0)
		lblWbtxt.setText(templist.get(0));
		else
			lblWbtxt.setText("");
		
		
		
	}
	
	private void myfunc(){
		instructionset=	instructiontext.getText();
		String[] arr= instructionset.split("\n");
		queue=new LinkedList<String>();
		for(int i =0;i<arr.length;i++){
			queue.add(arr[i]);
		}
		System.out.println(queue);
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
		frame.setBounds(100, 100, 662, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHead= new JLabel("Scalar Pipelined Processor");
		lblHead.setFont(new Font("Abyssinica SIL", Font.PLAIN, 28));
		lblHead.setBounds(150, 17, 400, 40);
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
		btnStart.setBounds(455, 429, 87, 25);
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
		 btnNext.setBounds(375, 429, 87, 25);
		frame.getContentPane().add(btnNext);
		
		
		 btnQuit = new JButton("Exit");
		 btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		 btnQuit.setEnabled(false);
		 btnQuit.setBounds(290, 429, 87, 25);
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
		
	}
}
