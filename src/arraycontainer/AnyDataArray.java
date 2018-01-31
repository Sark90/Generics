package arraycontainer;

public class AnyDataArray<T> {
    private Object[] arr; //T[]

    public AnyDataArray(T...obj) {
        arr = new Object[obj.length];
        for(int i=0; i<obj.length; i++) {
            arr[i] = obj[i];
        }
    }

    protected boolean isInit(Object[] o) {
        if (o == null || o.length == 0) {
            System.out.println("Array is not initialized.");
            return false;
        } else {
            return true;
        }
    }

    public T getElementByIndex (int index) {
        if (!isInit(arr)) {
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
        AnyDataArray<Integer> dataInt = new AnyDataArray<>(22, -5, -2, 0);
        AnyDataArray<Double> dataDouble = new AnyDataArray<>(1118.5, -8.0, 0.087, 999.3);
        AnyDataArray<String> dataStr = new AnyDataArray<>("Java", "Generics");
        AnyDataArray<Boolean> dataBool = new AnyDataArray<>(true);
        AnyDataArray<Character> dataChar = new AnyDataArray<>();
        AnyDataArray<AnyDataArray> data = new AnyDataArray(new AnyDataArray('A')/*.getElementByIndex(0)*/);

        System.out.println(dataInt.getElementByIndex(2));
        System.out.println(dataDouble.getElementByIndex(0));
        System.out.println(dataStr.getElementByIndex(1));
        System.out.println(dataBool.getElementByIndex(10));
        System.out.println(dataBool.getElementByIndex(0));
        System.out.println(dataChar.getElementByIndex(0));
        System.out.println(data.getElementByIndex(0));
    }
}
