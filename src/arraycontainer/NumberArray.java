package arraycontainer;

public class NumberArray<T extends Number & Comparable> extends AnyDataArray {

    private T[] arr;

    public NumberArray(T...obj) {
        super(obj);
        Number[] numArr = new Number[obj.length];
        arr = (T[]) numArr;
        for(int i=0; i<obj.length; i++) {
            arr[i] = obj[i];
        }
    }
 
    protected boolean isInit() {
        return super.isInit(arr);
    }

    public T[] getSortedArray() {
        if (!isInit()) {
            return null;
        }
        System.out.println("Sorted " + arr[0].getClass().getSimpleName() + " array:");
        Number[] numArr = new Number[arr.length];
        T[] sortedArr = (T[]) numArr;
        for (int i = 0; i < arr.length; i++) {
            sortedArr[i] = arr[i];
        }
        T min;
        for (int i=0; i<sortedArr.length; i++) {
            for (int j=i+1; j<sortedArr.length; j++) {
                if (sortedArr[i].compareTo(sortedArr[j]) > 0) {
                    min = sortedArr[j];
                    sortedArr[j] = sortedArr[i];
                    sortedArr[i] = min;
                }
            }
        }
        return sortedArr;
    }
    private void printArray(T[] array) {
        if (!isInit()) {
            return;
        }
        for (T t: array) {
            System.out.println("\t" + t);
        }
        System.out.println();
    }

    public Double getSum() {
        Double sum = 0.0;
        if (!isInit()) { return sum; }
        for (Number n: arr) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public T getMin() {
        if (!isInit()) {return (T) (Number) 0;}
        T min = arr[0];
        for(T t: arr) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
        }
        return min;
    }

    public T getMax() {
        if (!isInit()) {
            return (T) (Number) 0;
        }
        T max = arr[0];
        for(T t: arr) {
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return max;
    }

    public static void demo() {
        NumberArray<Integer> dataInt = new NumberArray<>(1, 56, 100, -12, 0);
        NumberArray<Double> dataDouble = new NumberArray<>(68.5, -9.99, 0.087);
        NumberArray<Long> dataLong = new NumberArray<>(699477L, -129541316L, 0L, 68764L);

        System.out.println(dataInt.getElementByIndex(2));
        System.out.println(dataDouble.getElementByIndex(0));
        System.out.println(dataLong.getElementByIndex(3));

        dataInt.printArray(dataInt.getSortedArray());
        dataDouble.printArray(dataDouble.getSortedArray());
        dataLong.printArray(dataLong.getSortedArray());

        System.out.println("Integer array, MIN: " + dataInt.getMin());
        System.out.println("Double array, MIN: " + dataDouble.getMin());
        System.out.println("Long array, MIN: " + dataLong.getMin());
        System.out.println();
        System.out.println("Integer array, MAX: " + dataInt.getMax());
        System.out.println("Double array, MAX: " + dataDouble.getMax());
        System.out.println("Long array, MAX: " + dataLong.getMax());
        System.out.println();
        System.out.println("Integer array, sum: " + dataInt.getSum());
        System.out.println("Double array, sum: " + dataDouble.getSum());
        System.out.println("Long array, sum: "+ dataLong.getSum());
        System.out.println();
    }
}
