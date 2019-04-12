import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int choice =0;
        while (choice != 1 && choice != 2) {
            System.out.println("What service do you need please choose 1 for transpose or 2 for determinate");
            choice = input.nextInt();
        }
        // matrix
        System.out.println("Enter no. Rows");
        int matrixA_rows = input.nextInt();
        System.out.println("Enter no. Columns ");
        int matrixA_columns = input.nextInt();

        int[] arr1 = new int[matrixA_rows * matrixA_columns];

        for (int i=0 ;i<matrixA_rows * matrixA_columns;i++)
            arr1[i] =  input.nextInt();

        Matrix m1 = new Matrix(matrixA_rows, matrixA_columns);
        m1.SetNumbers(arr1);
        //port according to choice
        int port=9899;
        if (choice == 1) port = 9898;
        else port = 6666;

        //Connect to server
        Socket socket = new Socket("localhost", port);
        System.out.println("after socket ");


        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("after socket2 ");

        ObjectInputStream in=new ObjectInputStream(socket.getInputStream());

        System.out.println("socket done and sending object");
            out.writeObject(m1); //send message to server
        Thread.sleep((new Random()).nextInt(10000));
            if (port == 9898) {
                int response = (int) in.readObject();
                System.out.println(" det is = "+ response + "\n");
            }
            else
            {
                Matrix m = (Matrix) in.readObject();

                System.out.println(" transpose is " );
                m.print();
            }


        socket.close();
    }
}