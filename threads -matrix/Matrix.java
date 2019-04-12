interface Adorable <T>{
    T add ( T matrix);
}
/////////////////////////////////////////////////
public class Matrix  implements  Adorable<Matrix>{

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

    public int [] getNumbers ()
    {
        return  array;
    }
}













