import java.io.*;  
import java.net.*;

/**
 * A simple client for the capitalization server.
 */
public class client {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
		int choice =0;
		while (choice != 1 && choice != 2) {
		System.out.println("What service do you need please choose 1 or 2");
		 choice = input.nextInt();
		}
		int port=9899;
		if (choice == 1) port = 9898;
		//Connect to server
        Socket socket = new Socket("localhost", 9898);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
        System.out.println(in.readLine());
        
        BufferedReader consolereader = new BufferedReader (new InputStreamReader(System.in));
        String message = "";
        while(true)
        {
            System.out.println("Enter a message in lower case or a period '.' to quit");
            message = consolereader.readLine();
            out.println(message); //send message to server
            if(message.equals("."))
                break;
            String response = in.readLine();            
            System.out.println("Capitalized message = "+ response + "\n");
        }
        socket.close();
    }
}