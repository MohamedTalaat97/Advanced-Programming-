import java.util.Arrays;
import java.util.Scanner;

public class Mul implements  Runnable {
    private Matrix A;
    private Matrix B;
    private int[][] C;

    public Mul(Matrix a, Matrix b, int[][] r) {
        A = a;
        B = b;
        C = r;
    }


    @Override
    public void run() {

        int id = Integer.parseInt(Thread.currentThread().getName());
        System.out.println("Hello from " + Thread.currentThread().getName());
        System.out.println("id  " + id);


        switch (id) {
            case 1:
                //rows of a * half columns of B in c
                for (int i = 0; i < A.getNRows(); i++) {
                    for (int j = 0; j < B.getNcols() / 2; j++) {
                        int[] first = A.getRow(i);
                        int[] second = B.getCol(j);
                        int value = 0;
                        for (int k = 0; k < first.length; k++)
                            value += first[k] * second[k];
                        C[i][j] = value;
                    }
                }
                break;
            case 2:
                //rows of a * half columns of B in c

                for (int i = 0; i < A.getNRows(); i++) {
                    for (int j = B.getNcols() / 2; j < B.getNcols(); j++) {
                        int[] first = A.getRow(i);
                        int[] second = B.getCol(j);
                        int value = 0;
                        for (int k = 0; k < first.length; k++)
                            value += first[k] * second[k];
                        C[i][j] = value;
                    }
                }
                break;
        }
    }


    public static void main(String args[]) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter no. of rows of matrix A ");
        int matrixA_rows = input.nextInt();
        System.out.println("Enter no. of columns of matrix A ");
        int matrixA_columns = input.nextInt();
        System.out.println("Enter no. of rows of matrix B ");
        int matrixB_rows = input.nextInt();
        System.out.println("Enter no. of columns of matrix B ");
        int matrixB_columns = input.nextInt();

        int[] arr1 = new int[matrixA_rows * matrixA_columns];
        int[] arr2 = new int[matrixB_rows * matrixB_columns];
        int[][] result = new int[matrixA_rows][matrixB_columns];

        if (matrixA_columns == matrixB_rows) {
            System.out.println(" enter matrix A elements ");

            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = input.nextInt();

            }

            System.out.println("enter matrix B elements ");
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = input.nextInt();
            }

        } else {
            System.out.println("matrix multiplication cannot be done as " +
                    "columns of A does not match rows of B");
        }


        Matrix m1 = new Matrix(matrixA_rows, matrixA_columns);
        Matrix m2 = new Matrix(matrixB_rows, matrixB_columns);
        m1.SetNumbers(arr1);
        m2.SetNumbers(arr2);

        Thread t0 = Thread.currentThread();
        // 4. Create 2 threads
        Thread t1 = new Thread(new Mul(m1, m2, result));
        Thread t2 = new Thread(new Mul(m1, m2, result));

        // 5. Set the name of each thread
        t0.setName("Main Thread");

        t1.setName("1");
        t2.setName("2");

        t1.start();
        t2.start();

        // 7. Wait for them to join the current thread
        t1.join();
        t2.join();


        for (int i = 0; i < matrixA_rows; i++) {
            for (int j = 0; j < matrixB_columns; j++) {
                System.out.print(result[i][j] + " ");
            }

            System.out.println("/n");
        }
    }

}