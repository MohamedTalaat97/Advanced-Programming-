import java.io.*;  
import java.net.*;  
public class client {  
	public static void main(String[] args) {  
		try{      
			Socket s=new Socket("localhost",6666);  // establish a connection with the server with IP address "localhost" or "127.0.0.1" and port 6666, it is the same port used in the server
			PrintWriter out = new PrintWriter(s.getOutputStream(), true); //what does this line do?
			out.println("Hello Server"); // The client sends a hello message to the server
			out.close();  
			s.close();  //close the socket
		}catch(Exception e){
			System.out.println(e);
		}  
	}  
}