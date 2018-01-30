package arraycontainer;

public class DataArray<T extends Number & Comparable> {

    private /*Object*/Number[] arr; //T[]

    public DataArray(T...obj) {
        arr = new /*Object*/Number[obj.length];
        for(int i=0; i<obj.length; i++) {
            arr[i] = obj[i];
        }
    }
 
    private boolean isInit() {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is not initialized.");
            return false;
        } else {
            return true;
        }
    }
    public T getElementByIndex (int index) {
        if (!isInit()) {
            return null;
        }
        String paramType = arr[0].getClass().getSimpleName();
        String s = this.getClass().getSimpleName() + "<" + paramType + ">" + "[" + index + "]: ";
        System.out.print(s);
        if (index >= arr.length || index < 0) {
            System.out.println("no such index.");
            return null;
        }
        return (T) arr[index];

    }

    public T[] getSortedArray() {
        if (!isInit()) {
            return null;
        }
        Number[] sortedArr = new Number[arr.length];
        //T[] sortedArr = (T[]) sortedArrDouble;
        for (int i = 0; i < arr.length; i++) {
            sortedArr[i] = arr[i];
        }
        Number min;
        for (int i=0; i<sortedArr.length; i++) {
            for (int j=i+1; j<sortedArr.length; j++) {
                if (((T) sortedArr[i]).compareTo((T) sortedArr[j]) > 0) {
                    min = sortedArr[j];
                    sortedArr[j] = sortedArr[i];
                    sortedArr[i] = min;
                }
            }
        }
        return (T[]) sortedArr;
    }
    public void printArray(T[] array) {
        if (!isInit()) {
            return;
        }
        for (T t: (T[]) array) {
            System.out.println("\t" + t);
        }
        System.out.println();
    }

   /* public T getSum() {
        T sum = (T) (Number) 0;
        if (!isInit()) { return sum; }
        for (int i=0; i<arr.length; i++) {
            sum += (T) arr[i];
        }
        return sum;
    }*/

    public static void demo() {
        DataArray<Integer> dataInt = new DataArray<>(1, 56, 100, -12, 0);
        DataArray<Double> dataDouble = new DataArray<>(-9.99, 0.087);
        /*DataArray<String> dataStr = new DataArray<>("Java", "Generics");
        DataArray<Boolean> dataBool = new DataArray<>(true);
        DataArray<Character> dataChar = new DataArray<>();
        DataArray<DataArray> data = new DataArray(new DataArray('A')*//*.getElementByIndex(0)*//*);*/

        System.out.println(dataInt.getElementByIndex(2));
        System.out.println(dataDouble.getElementByIndex(0));
        /*System.out.println(dataStr.getElementByIndex(1));
        System.out.println(dataBool.getElementByIndex(10));
        System.out.println(dataBool.getElementByIndex(0));
        System.out.println(dataChar.getElementByIndex(0));
        System.out.println(data.getElementByIndex(0));*/
        System.out.println("Sorted Integer array:");
        dataInt.printArray(dataInt.getSortedArray());
        System.out.println("Sorted Double array:");
        dataDouble.printArray(dataDouble.getSortedArray());

    }
}
