package threads.arraysum;

import java.util.Random;
import static threads.arraysum.KBInput.inputInt;
import static threads.arraysum.KBInput.inputSize;
import static threads.arraysum.KBInput.inputThreadsNum;

public abstract class ArrOperations extends Thread {
    public static final byte SUM = 1;
    public static final byte PRODUCT = 2;
    private static final int MAX_THREAD_NUM = 10;
    private static final int DURATION_INDEX = 0;
    private static final int TESTS_NUM = 3;
    private static long duration;
    private static long[][] durations = new long[MAX_THREAD_NUM+1][1];
    private static int threadNum;

    protected ArrOperations() {
        super();
    }

    public static boolean isNull(int[] arr) {
        if (arr == null) {
            System.out.println("Array is not initialized.");
            return true;
        } else return false;
    }
    public boolean isNull(int[][] arr) {
        if (arr == null) {
            System.out.println("Array is not initialized.");
            return true;
        } else return false;
    }


    public static int[] genArray() {
        int arr[] = new int[inputSize()];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }
    public static int[][] gen2DArray() {
        int [][] arr = new int[inputInt("Input size 1: ")][inputInt("Input size 2: ")];
        Random rand = new Random();
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = rand.nextInt(100);
            }
        }
        return arr;
    }
    public static int[][] gen2DArray(int size1) {
        int [][] arr = new int[size1][inputInt("Input size 2: ")];
        Random rand = new Random();
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                arr[i][j] = rand.nextInt(100);
            }
        }
        return arr;
    }
    public static void count(ArrOperations ao, byte operation) {
        threadNum = inputThreadsNum();
        count(ao, operation, threadNum);
    }
    private static void count(ArrOperations ao, byte operation, int num) {
        ArrOperations threadArr[] = new ArrOperations[0];
        threadNum = num;
        if (operation!=PRODUCT)
        {
            threadArr = new ArrSum[threadNum];
        } else {
            threadArr = new ArrProduct[threadNum];
        }
        if(threadNum>ao.getArrSize()) { threadNum = ao.getArrSize(); }
        Timer timer = new Timer();
        timer.start();
        for (int i=0; i<threadNum; i++) {
            if (operation!=PRODUCT)
            {
                threadArr[i] = new ArrSum();;
            } else {
                threadArr[i] = new ArrProduct();
            }

            threadArr[i].start();
        }
        for(int i=0; i<threadNum; i++) {
            try {
                threadArr[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread " + i + " interrupted.");
            }
        }
        timer.stop();
        System.out.println(threadNum + " thread(s):\n\t" + ao.getResult());
        System.out.println("\tTime passed: " + timer.getTime() + " ns");
        duration = timer.getTime();
    }

    public abstract String getResult();
    public abstract int getArrSize();

    //public static void showArray() {}

    public static int getThreadNum() {
        return threadNum;
    }
       public static void getOptimalThreadsNum(ArrOperations ao, byte operation) {
        System.out.println("\nCalculating the optimal number of threads:");
        for(int threadNum = 1; threadNum<=MAX_THREAD_NUM; threadNum++) {
            for (int i=0; i<TESTS_NUM; i++) {
                count(ao, operation, threadNum);
            }
            durations[threadNum][DURATION_INDEX] = duration;
        }
        long minTime = durations[1][DURATION_INDEX];
        int optimalThreadNum = 1;
        for(int i=2; i<MAX_THREAD_NUM; i++) {
            if (minTime > durations[i][DURATION_INDEX]) {
                minTime = durations[i][DURATION_INDEX];
                optimalThreadNum = i;
            }
        }
        System.out.println("Optimal number of threads : " + optimalThreadNum + " (time: " + minTime + " ns)");
    }
}
