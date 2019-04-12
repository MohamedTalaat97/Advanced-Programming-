import java.io.Serializable;

interface Adorable <T>{
    T add ( T matrix);
}
/////////////////////////////////////////////////
public class Matrix  implements  Adorable<Matrix>, Serializable {

    int[][] numbers; // 2darray
    int [] array;
    int m;//rows
    int n;//columns
    /////////////////////////////////////////
    public Matrix(int row, int col) {
        m = row;
        n = col;
        numbers = new int[row][col];
    }
    //////////////////////////////////////////
    public boolean SetNumbers(int arr[]) {

        array = new int [n*m];
        if (arr.length == m * n) {
            int c = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    numbers[i][j] = arr[c];
                    array[c] =arr[c];
                    c++;
                }
            return true;
        } else
            return false;
    }

    //////////////////////////////////////////////
    public void print() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(numbers[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    ///////////////////////////////////////////////////////////////////
    public void Transpose() {

        int t[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) t[i][j] = numbers[j][i];
        }
        numbers = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) numbers[i][j] = t[i][j];
        }

        int temp = m;
        m=n;
        n=temp;
    }
    ///////////////////////////////////////////////////////////
    @Override
    public Matrix add(Matrix f) {

        Matrix k = new Matrix(m,n);

        if (m==f.m && n == f.n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    k.numbers[i][j] = numbers[i][j] + f.numbers[i][j];
            }
            return k;
        }
        else return null;

    }

    public int[] getRow (int i) {
        if (i < m)
            return numbers[i];
        else System.out.println("error in get row ");

        return null;
    }


    public int[] getCol (int j)
    {

        int [] col = new int [m];
        if  (j<n)
        {
            for (int i = 0; i < m; i++) {
                col [i] = numbers[i][j];
            }
      return col;
        }
        else System.out.println("error in get row ");

        return null;

    }


    public int getNRows()
    {
        return  m;
    }
    public int getNcols()
    {
        return  n;
    }

    public int [][] getNumbers ()
    {
        return  numbers;
    }


     int determinantOfMatrix(int [][] mat,int n)
    {
        int D = 0; // Initialize result

        // Base case : if matrix contains single
        // element
        if (n == 1)
            return numbers[0][0];


        // To store cofactors
        int temp[][] = new int[m][m];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of mat[0][f]
            getCofactor(numbers, temp, 0, f, n);
            D += sign * numbers[0][f]
                    * determinantOfMatrix(temp, n - 1);

            // terms are to be added with
            // alternate sign
            sign = -sign;
        }

        return D;
    }
     void getCofactor(int mat[][],int temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of
        // the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {

                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = mat[row][col];

                    // Row is filled, so increase
                    // row index and reset col
                    //index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
}













