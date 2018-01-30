package threads.arraysum;

public class ArrProduct extends ArrOperations {
    private static int[][] arr1, arr2, arrProduct;
    private static int startCol=0, startLine=0, end=0, part=1, product, size;
    static {
        System.out.println("Array 1 generation:");
        arr1 = ArrOperations.gen2DArray();
        System.out.println("\tArray 2 generation:\nSize 1 = array 1 size 2 = " + arr1[0].length);
        arr2 = ArrOperations.gen2DArray(arr1[0].length);
        arrProduct = new int[arr1.length][arr2[0].length];
        size = arrProduct.length * arrProduct[0].length;
    }
    public ArrProduct(){
        super();
    }
    @Override
    public void run() {
        product();
    }
    private int productCell (int lineA, int colB) {
        //if (isNull(arr1) || isNull(arr2)) return 0;
        product = 0;
        for(int i=0; i<arr1[0].length; i++) {
            product += arr1[lineA][i] * arr2[i][colB];
        }
        return arrProduct[lineA][colB] = product;
    }
    synchronized private void test() {
        if (startCol == 0 && startLine == 0) {
            part = size / getThreadNum();
        }
        for (int i = 1; i <= part; i++) {
            if (i == part && startCol < (arrProduct[i].length - 1) && startLine == arrProduct.length) {
                part++; //добавить остаток
            }
            if (startCol == arrProduct[0].length) {
                startLine++;
                startCol = 0;
            }
        }
    }
    synchronized public void product() {
        test();
        arrProduct[startLine][startCol] = productCell(startLine, startCol);
        startCol++;
    }

    @Override
    public String getResult() {
        String result = "Product:\n";
        for(int i=0; i<arrProduct.length; i++){
            for(int j=0; j<arrProduct[0].length; j++) {
                result = result + "\t" + arrProduct[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    @Override
    public int getArrSize() {
        return size;
    }

    public void showArray() {
        System.out.println("\tArray 1:");
        showArray(arr1);
        System.out.println("\tArray 2:");
        showArray(arr2);
        System.out.println("\tProduct of arrays:");
        showArray(arrProduct);
    }
    private void showArray(int arr[][]) {
        if(!isNull(arr[0])) {
            for(int i=0; i< arr1.length; i++) {
                for (int j=0; j<arr1[0].length; j++) {
                    System.out.println(arr1[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("- not initialized.");
        }
    }
}
