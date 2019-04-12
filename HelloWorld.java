import mpi.MPI;

import java.io.File;
import java.util.Scanner;

public class HelloWorld {

    public static void main(String args[]) throws Exception {

        // data
        int arraySize,arraySize2,sum = 0;
        int[] array=null,data=null,sums=null,buffer=null,onevalue=null,onevalue2= null;

        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        if (me == 0) {

            sums = new int[3];
            System.out.println("Hi from <" + me + ">");
            // read array
            Scanner s = new Scanner(new File("n.txt"));
            arraySize = s.nextInt();
            arraySize2 = arraySize;

            while (arraySize2 % 3 != 0)  // adjust size
                arraySize2++;

            array = new int[arraySize2];

            onevalue = new int [1];
            onevalue[0] = arraySize2;

            MPI.COMM_WORLD.Bcast(onevalue,0, 1, MPI.INT, 0);  //broadcast size to other proc

            for (int i = 0; i < arraySize2; i++) {
                if (i < arraySize)
                    array[i] = s.nextInt();

                else array[i] = 0;
            }

            data = new int[arraySize2 / 3];

            MPI.COMM_WORLD.Scatter(array, 0, arraySize2 / 3, MPI.INT, data, 0, arraySize2 / 3, MPI.INT, 0);


            //calculate the share
            for (int i = 0; i < data.length; i++)
                sum += data[i];

            sums[0] = sum;
            buffer = new int[3];

            MPI.COMM_WORLD.Gather(sums, 0, 1, MPI.INT, buffer, 0, 1, MPI.INT, 0);

        } else {

            onevalue2 = new int [1];
            MPI.COMM_WORLD.Bcast(onevalue2,0, 1, MPI.INT, 0); //receive the size
            System.out.println("received size -->" +onevalue2[0]);

            int dummy []= new int [onevalue2[0]];
            int data_2 []= new int [onevalue2[0]/3];

            MPI.COMM_WORLD.Scatter(dummy, 0, onevalue2[0]/3, MPI.INT, data_2, 0, onevalue2[0]/3, MPI.INT, 0);

            for (int i = 0; i < data_2.length; i++)
                sum += data_2[i];

            System.out.println("sum -->"+sum);
            int [] b = new int [1];
            b[0] = sum;

            int [] b1 = new int [3];

            MPI.COMM_WORLD.Gather(b, 0, 1, MPI.INT,b1 , 0, 1, MPI.INT, 0);


        }


        if (me == 0) {
            int total = buffer[0] + buffer[1] + buffer[2];
            System.out.println("total is " + total);
        }

        MPI.Finalize();
    }
}

