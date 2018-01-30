public class DataArray<T> {

    private Object[] arr;

    public DataArray(T...obj) {
        arr = new Object[obj.length];
        for(int i=0; i<obj.length; i++) {
            arr[i] = obj[i];
        }
    }

    public T getElementByIndex (int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is not initialized.");
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

    public static void demo() {
        DataArray<Integer> dataInt = new DataArray<>(1, 56, 100, -12, 0);
        DataArray<Double> dataDouble = new DataArray<>(-9.99, 0.087);
        DataArray<String> dataStr = new DataArray<>("Java", "Generics");
        DataArray<Boolean> dataBool = new DataArray<>(true);
        DataArray<Character> dataChar = new DataArray<>();
        DataArray<DataArray> data = new DataArray(new DataArray('A')/*.getElementByIndex(0)*/);

        System.out.println(dataInt.getElementByIndex(2));
        System.out.println(dataDouble.getElementByIndex(0));
        System.out.println(dataStr.getElementByIndex(1));
        System.out.println(dataBool.getElementByIndex(10));
        System.out.println(dataBool.getElementByIndex(0));
        System.out.println(dataChar.getElementByIndex(0));
        System.out.println(data.getElementByIndex(0));
    }
}
