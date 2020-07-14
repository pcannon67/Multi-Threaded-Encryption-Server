import java.io.*;
import java.net.*;
import java.util.*;

class Server{
	
	public static void main(String argv[]){
		 Server server = new Server();
		 try {
			server.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	public void run() throws IOException{
		
		try {
			
			int portNum = 5520;
			ServerSocket servSock = new ServerSocket( portNum );
			File logfile = new File("./src/prog1b.log");
			FileOutputStream writelog = new FileOutputStream(logfile);
			PrintWriter print = new PrintWriter(writelog, true);
			
			while( true ){
				Socket sock = servSock.accept();   
				ServerThread servThread = new ServerThread( sock, print );
				servThread.start();
				
			}
			
			
		}catch( IOException serv_ex1) {
			System.out.println("Server exception 1");
		}
	
	}
}