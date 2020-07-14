import java.io.*;
import java.net.*;
import java.util.*;

class ServerThread extends Thread{

	   Socket sock;	              // Socket connected to the Client
	   PrintWriter writeSock, logfile;
	   BufferedReader readSock;


	public ServerThread (Socket clientSock, PrintWriter logfile){

		   this.sock = clientSock;
		   this.logfile = logfile;
		   
	
	}
	
	
	public void run(){
		try {
			 
		
			writeSock = new PrintWriter(sock.getOutputStream(), true);
			readSock = new BufferedReader( new InputStreamReader(sock.getInputStream() ) );
			String time = new java.util.Date().toString();
			String addr = sock.getInetAddress().toString();
			int port = sock.getPort();
			
			this.logfile.println("Got a connection: time: " + time + ", IP Address: " + addr + ", Port: " + port);
			String inLine = null;
			int bytey_boi = sock.getInputStream().read();
			
			if(bytey_boi == -1) {
				this.logfile.println("ServerThread Exception");
			}
			
			char byte_char = (char) bytey_boi;
			
			while((inLine = readSock.readLine()) != null){
				inLine = byte_char + inLine;
				byte_char = 0;
			

				if(sock != null) {
					
					if(inLine.equalsIgnoreCase("quit")){  
						this.logfile.println("Connection closed, Port: " + port);
						
						Polyalphabetic_Encryption pe = new Polyalphabetic_Encryption();
						String outLine = pe.encrypt(inLine);
						writeSock.println( outLine );
						writeSock.close();
						sock.close();
						return;
						
					}
				
					Polyalphabetic_Encryption pe = new Polyalphabetic_Encryption();
					String outLine = pe.encrypt(inLine);
					writeSock.println( outLine );
					writeSock.flush();
					
				}
				
			}
			
			
	
		    
		}catch( IOException stex2) {
			
			System.out.println("ServerThread exception 2");
		}


	}
}
