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


public class Frame1 {

	private JFrame frame;
	private JButton btnStart;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 662, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JEditorPane instructiontext = new JEditorPane();
		instructiontext.setBounds(40, 32, 196, 120);
		frame.getContentPane().add(instructiontext);
		
		 btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnStart.setBounds(76, 181, 117, 25);
		frame.getContentPane().add(btnStart);
		
		JLabel lblIf = new JLabel("IF");
		lblIf.setBounds(301, 30, 30, 15);
		frame.getContentPane().add(lblIf);
		
		JLabel lblId= new JLabel("ID");
		lblId.setBounds(301, 80, 30, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblRd = new JLabel("RD");
		lblRd.setBounds(301, 130, 30, 15);
		frame.getContentPane().add(lblRd);
		
		JLabel lblAlu= new JLabel("ALU");
		lblAlu.setBounds(301, 180, 40, 15);
		frame.getContentPane().add(lblAlu);
		
		JLabel lblMem = new JLabel("MEM");
		lblMem.setBounds(301, 230, 40, 15);
		frame.getContentPane().add(lblMem);
		
		JLabel lblWb= new JLabel("WB");
		lblWb.setBounds(301, 280, 30, 15);
		frame.getContentPane().add(lblWb);
		
		JLabel lblIftxt = new JLabel("");
		lblIftxt.setOpaque(true);
		lblIftxt.setBackground(Color.white);
		lblIftxt.setBounds(365, 25, 200, 25);
		frame.getContentPane().add(lblIftxt);
		
		JLabel lblIdtxt = new JLabel("");
		lblIdtxt.setOpaque(true);
		lblIdtxt.setBackground(Color.white);
		lblIdtxt.setBounds(365, 75, 200, 25);
		frame.getContentPane().add(lblIdtxt);
		
		JLabel lblRdtxt = new JLabel("");
		lblRdtxt.setOpaque(true);
		lblRdtxt.setBackground(Color.white);
		lblRdtxt.setBounds(365, 125, 200, 25);
		frame.getContentPane().add(lblRdtxt);
		
		JLabel lblAlutxt = new JLabel("");
		lblAlutxt.setOpaque(true);
		lblAlutxt.setBackground(Color.white);
		lblAlutxt.setBounds(365, 175, 200, 25);
		frame.getContentPane().add(lblAlutxt);
		
		JLabel lblMemtxt = new JLabel("");
		lblMemtxt.setOpaque(true);
		lblMemtxt.setBackground(Color.white);
		lblMemtxt.setBounds(365, 225, 200, 25);
		frame.getContentPane().add(lblMemtxt);
		
		JLabel lblWbtxt = new JLabel("");
		lblWbtxt.setOpaque(true);
		lblWbtxt.setBackground(Color.white);
		lblWbtxt.setBounds(365, 275, 200, 25);
		frame.getContentPane().add(lblWbtxt);
		
	}
}
