import java.awt.EventQueue;

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
	 */
	public Frame1() {
		initialize();
	}
	
	private void myfunc(){
		String[] arr= instructionset.split("\n");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
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
		frame.getContentPane().add(instructiontext);
		
		 btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				instructionset=	instructiontext.getSelectedText();
			}
		});
		btnStart.setBounds(455, 429, 117, 25);
		frame.getContentPane().add(btnStart);
		
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
