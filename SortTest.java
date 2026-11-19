
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import java.util.Map;

public class SortTest {

    /* ----Sort-Methoods------------------------------------------ */
    public void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            /* Move elements of data[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    public void bubbleSort(int[] data) {
        int n = data.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    
                    // Swap data[j] and data[j+1]
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }


    public void shellSort(int[] arr) {
        int n = arr.length;

        // Start with a large gap, then reduce it step by step
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // Perform a "gapped" insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                
                // Current element to be placed correctly
                int temp = arr[i]; 
                int j = i;

                // Shift earlier elements that are greater than temp
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                // Place temp in its correct position
                arr[j] = temp;
            }
        }
    }


    /* ----Quick-Sort--------------------------------------------- */
    static int partition(int[] data, int low, int high)
    {
        // median-of-three: put the median of low/mid/high at data[high]
        int mid = low + (high - low) / 2;
        if (data[mid] < data[low])   swap(data, mid, low);
        if (data[high] < data[low])  swap(data, high, low);
        if (data[high] < data[mid])  swap(data, high, mid);
        swap(data, mid, high);       // median is now the pivot at high

        int pivot = data[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (data[j] < pivot) {
                i++;
                swap(data, i, j);
            }
        }

        swap(data, i + 1, high);
        return i + 1;
    }

    // swap function
    static void swap(int[] data, int i, int j)
    {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    static void quickSort(int[] data, int low, int high)
    {
        while (low < high) {
            int pi = partition(data, low, high);

            // recurse on the smaller side, loop on the larger
            if (pi - low < high - pi) {
                quickSort(data, low, pi - 1);
                low = pi + 1;
            } else {
                quickSort(data, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    public void quickSortCall(int[] data) {
        quickSort(data, 0, data.length - 1);
    }


    /* ----Merge-Sort--------------------------------------------- */
    static void merge(int arr[], int l, int m, int r){
        
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    static void mergeSort(int arr[], int l, int r){
        
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public void mergeSortCall(int[] data) {
        mergeSort(data, 0, data.length - 1);
    }


    /* ----Create-Data-------------------------------------------- */
    public static void printData(int[] data) {
        for (int x : data) {
            System.out.print(x + " ");
        }
        System.out.println("--------------------------");
        System.out.println();
    }

    public static int[] createAscendingData(int n) {
        int[] data = new int[n];

        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        return data;
    }

    public static int[] createDescendingData(int n) {
        int[] data = new int[n];

        for (int i = 0; i < data.length; i++) {
            data[i] = n - i;
        }

        return data;
    }

    public static int[] createRandomData(int n) {
        int[] data = new int[n];

        Random gen = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = gen.nextInt(n);
        }

        return data;
    }
    

    /* ----Function-Print----------------------------------------- */
    public void printAscending(HashMap<String, Long> data) {
        System.out.println("Ascending order:\n");
        data.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEach(tm -> {
            System.out.printf("%-15s time: %9.2f ms%n", tm.getKey(), tm.getValue() / 1000000.0);
            System.out.printf("%-15s time: %9.2f s%n%n", tm.getKey(), tm.getValue() / 1000000000.0);
        });
    }

    public void printDescending(HashMap<String, Long> data) {
        System.out.println("Descending order:\n");
        data.entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .forEach(tm -> {
            System.out.printf("%-15s time: %9.2f ms%n", tm.getKey(), tm.getValue() / 1000000.0);
            System.out.printf("%-15s time: %9.2f s%n%n", tm.getKey(), tm.getValue() / 1000000000.0);
        });
    }


    /* ----Function-Time------------------------------------------ */
    public HashMap<String, Long> getTime(int [] data, FunctionPointer func, String funcStr, HashMap<String, Long> timeMap) {
        int [] data2 = data.clone();
        long start = System.nanoTime();
        func.methodSignature(data2);
        long end = System.nanoTime();

        timeMap.put(funcStr, end - start);

        return timeMap;
    }


    /* ----Function-Pointer--------------------------------------- */
    private interface FunctionPointer {
        void methodSignature(int[] a);
    }
    

    /* ----Main--------------------------------------------------- */
    public void main(String[] args) {
        int size = 500000;
        String dataType = "asceing"; // "ascending", "descending", "random"

        FunctionPointer pointerInsertion = this::insertionSort;
        FunctionPointer pointerBubble = this::bubbleSort;
        FunctionPointer pointerShell = this::shellSort;
        FunctionPointer pointerQuick = this::quickSortCall;
        FunctionPointer pointerMerge = this::mergeSortCall;

        // create the data
        HashMap<String, Long> timeMap = new HashMap<>();
        int[] data;
        if (dataType.equals("ascending")) {
            data = createAscendingData(size);
            System.out.println("\nAscending data | size: " + size + "\n");
        } else if (dataType.equals("descending")) {
            data = createDescendingData(size);
            System.out.println("\nDescending data | size: " + size + "\n");
        } else {
            data = createRandomData(size);
            System.out.println("\nRandom data | size: " + size + "\n");
        }
        int[] data2 = data.clone();

        if (size <= 100000) {
            timeMap = getTime(data, pointerInsertion, "Insertion sort", timeMap);
            timeMap = getTime(data, pointerBubble, "Bubble sort", timeMap);
        }
        timeMap = getTime(data, pointerShell, "Shell sort", timeMap);
        timeMap = getTime(data, pointerQuick, "Quick sort", timeMap);
        timeMap = getTime(data, pointerMerge, "Merge sort", timeMap);

        long start = System.nanoTime();
        Arrays.sort(data2);
        long end = System.nanoTime();
        timeMap.put("Array sort", end - start);

        printAscending(timeMap);
        System.out.println("\n");
        printDescending(timeMap);

    }
}