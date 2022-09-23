import java.util.Arrays;
public class Lab_MatrixMul {
    public static void main(String[] args) {
        int [][] inputA = {{5, 6, 7}, {4, 8, 9}};
        int [][] inputB = {{6, 4}, {5, 7}, {1, 1}};
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        //Q4 construct 2D
        Thread t1 = new Thread(new MatrixMulThread(0, 0, matA, matB, matC));
        t1.start();
        Thread t2 = new Thread(new MatrixMulThread(0, 1, matA, matB, matC));
        t2.start();
        Thread t3 = new Thread(new MatrixMulThread(1, 0, matA, matB, matC));
        t3.start();
        Thread t4 = new Thread(new MatrixMulThread(1, 1, matA, matB, matC));
        t4.start();
        try { //Q5 join each thread
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (Exception e) { System.out.println(e); }
            matC.show();
    }
    static class MatrixMulThread implements Runnable {
        int processing_row; int processing_col;
        MyData datA; MyData datB; MyData datC;
        MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
            //Q1
            processing_col = tRow;
            processing_col = tCol;
            datA = a; datB = b; datC = c; 
            if(tRow == tCol) {
                if(tRow == 0) {
                    for(int i = tCol; i <= b.data[tCol].length; i++) {
                        c.data[tRow][tCol] += a.data[tRow][i] * b.data[i][tRow];
                    }
                }
                else {
                    for(int i = tCol - 1; i <= b.data[tCol].length; i++) {
                        c.data[tRow][tCol] += a.data[tRow][i] * b.data[i][tRow];
                    }
                }
            }
            else if(tRow < tCol) {
                for(int i = tRow; i <= b.data[tRow].length; i++) {
                    c.data[tRow][tCol] += a.data[tRow][i] * b.data[i][tCol];
                }
            }
            else if(tRow > tCol) {
                for(int i = tCol; i <= b.data[tRow].length; i++) {
                        c.data[tRow][tCol] += a.data[tRow][i] * b.data[i][tCol];
                }
            }
        }  
        /* Q2 */ public void run() {
            /* Q3 */
            System.out.println("perform sum of multipication of assigned row and col");
        }
    }
    static class MyData {
        int [][] data;
        MyData(int [][] m) { data = m; }
        MyData(int r, int c) {
            data = new int[r][c];
            for(int [] aRow : data)
                Arrays.fill(aRow,9);
        }
        void show() {
            System.out.println(Arrays.deepToString(data));
        }
    }
}
