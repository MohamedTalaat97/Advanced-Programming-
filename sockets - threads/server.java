import java.io.*;  
import java.net.*; 

public class server {


    public static void main(String[] args) throws Exception {

        System.out.println("The server is running.");
        int clientNumber = 0;
        ServerSocket listener1 = new ServerSocket(9898);
        ServerSocket listener2 = new ServerSocket(6666);

        try {
            while (true) {

                new Transpose(listener2, clientNumber++).start();
                System.out.println("The server is between ");
                new Determinate(listener1.accept(), clientNumber++).start();

            }
        } finally {
            listener1.close();
            listener2.close();
        }
    }

    private static class Determinate extends Thread {
        private Socket socket;
        private int clientNumber;

        public Determinate(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.println("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {

            System.out.println(" new thread is made ");

            Matrix m = null;
            try {

                ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
                //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());

                try {
                    System.out.println("recieving object from cleint");
                     m = (Matrix) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                    if (m == null ) {
                        System.out.println("messege received null");
                    }
                    m.print();
                    out.writeObject(m.det());

            } catch (IOException e) {
                System.out.println("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + clientNumber + " closed");
            }
        }

    }

    private static class Transpose extends Thread {
        private Socket socket;
        private int clientNumber;
        private ServerSocket listener;

        public Transpose( ServerSocket listener,int clientNumber) {
           this.listener = listener;
            this.clientNumber = clientNumber;
            System.out.println("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {

            try {
                this.socket = listener.accept();

            }catch (IOException e){

            }
            System.out.println(" new thread is made ");

            Matrix m = null;
            try {

                ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
                //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());

                try {
                    System.out.println("recieving object from cleint");
                    m = (Matrix) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                if (m == null ) {
                    System.out.println("messege received null");
                }
                m.print();
                m.Transpose();
                out.writeObject(m);

            } catch (IOException e) {
                System.out.println("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + clientNumber + " closed");
            }
        }






    }

}
