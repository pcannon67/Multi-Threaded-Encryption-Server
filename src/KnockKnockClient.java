import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import java.net.*;
import java.io.*;
import javax.swing.JScrollPane;

public class KnockKnockClient extends JFrame {

	private JPanel contentPane;
	private JTextField addressfield;
	private JTextField portfield;
	private JTextField messagefield;
	Socket sock = null;	    // Socket used to connect to Server
	PrintWriter writeSock;    // Used to write data to socket
	BufferedReader readSock;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KnockKnockClient frame = new KnockKnockClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the frame.
	 */
	public KnockKnockClient(){
		setTitle("Knock Knock Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("IP Address");
		
		addressfield = new JTextField();
		addressfield.setText("localhost");
		addressfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Port Number");
		
		portfield = new JTextField();
		portfield.setText("5520");
		portfield.setColumns(10);
		
		JTextArea msgarea = new JTextArea();
		msgarea.setEditable(false);
		
		
		JButton connectbtn = new JButton("Connect");
			connectbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					String btntext = connectbtn.getText();
					
					if(btntext.equals("Disconnect")) {
						try
						{
							readSock.close();
							writeSock.close();
							sock.close();
							sock = null;
							msgarea.append("Disconnected from server \n");
							
							connectbtn.setText("Connect");
						
						}
						catch (IOException ex1)
						{
							   System.out.println( "Error: " + ex1 );
							   sock = null;  
							}
					}
					
					if(btntext.equals("Connect")) {
						try
						{
						   int portNum = Integer.parseInt(portfield.getText());       
						   String hostAddress = addressfield.getText();
					
						   sock = new Socket(hostAddress, portNum );
						   writeSock = new PrintWriter( sock.getOutputStream(), true );
						   readSock = new BufferedReader( new InputStreamReader(
						                                  sock.getInputStream() ) );
			
						   msgarea.append("Connected to server \n");
						   connectbtn.setText("Disconnect");
						
						}
						catch (ConnectException ex2) {
						   System.out.println("ConnectException" + ex2);
							sock = null; 
						    
						} 
						catch( IOException ex ) {
							System.out.println( "Error: " + ex );
							sock = null;   
						}
					}	
					
					
				}
			});
			
		JLabel lblEnterMessageTo = new JLabel("Enter Message to Server Below:");
		
		messagefield = new JTextField();	
		messagefield.setColumns(10);
			
		JButton sendbtn = new JButton("Send");
		sendbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
			{
		
					if( sock != null ){
						 
						
					 String server_message = messagefield.getText();
		
					   writeSock.println( server_message );
					//  System.out.println(server_message);
					   msgarea.append("Client: " + server_message);
					   msgarea.append("\n");
						writeSock.flush();
					   
					   
					  String dataRead = readSock.readLine();
					 // System.out.println(dataRead);
						msgarea.append("Server: " + dataRead);
						msgarea.append("\n"); 
				
					   if(server_message.equalsIgnoreCase("quit")){   
							msgarea.append("Disconnected from server \n");	
							connectbtn.setText("Connect");
				
					   }
					 
					}
					

			}
				catch( IOException E3) {
					System.out.println( "Error: " + E3);
				    sock = null;   
				}
			}
		});
		
			
		
		JLabel lblNewLabel_2 = new JLabel("Client/Server Communcation");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(addressfield, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(portfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(connectbtn))
						.addComponent(lblEnterMessageTo))
					.addContainerGap(61, Short.MAX_VALUE))
				.addComponent(messagefield, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(sendbtn)
						.addComponent(lblNewLabel_2))
					.addContainerGap(202, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(addressfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(portfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(connectbtn))
					.addGap(29)
					.addComponent(lblEnterMessageTo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(messagefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sendbtn)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		scrollPane.setViewportView(msgarea);
		contentPane.setLayout(gl_contentPane);
	}
}
