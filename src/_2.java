import java.util.Scanner;

public class _2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int num = sc.nextInt();

        try {
            Frame[] obj = new Frame[num];
            for (int i = 0; i < num; i++) {
                obj[i] = new Frame();
                obj[i].enterData(i + 10);
                obj[i].enterid(num - i);  // Random-ish IDs for sorting demo
            }

            sort sorter = new sort();
            Frame[] sortedFrames = sorter.mergeSort(obj);

            System.out.println("\nSorted Frames by ID (ascending):");
            for (Frame f : sortedFrames) {
                System.out.println("ID: " + f.id + "  Data: " + f.data);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}

class Frame {
    int data;
    int id;

    public void enterData(int data){
        this.data = data;
    }

    public void enterid(int id){
        this.id = id;
    }
}

class sort {

    public Frame[] mergeSort(Frame[] arr) {
        // Base case: if array has only 1 element
        if (arr.length <= 1) return arr;

        // Split array into two halves
        int mid = arr.length / 2;
        Frame[] left = new Frame[mid];
        Frame[] right = new Frame[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        if (arr.length - mid >= 0) System.arraycopy(arr, mid, right, 0, arr.length - mid);

        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge both sorted halves
        return merge(left, right);
    }

    private Frame[] merge(Frame[] left, Frame[] right) {
        Frame[] merged = new Frame[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Compare and merge in ascending order by ID
        while (i < left.length && j < right.length) {
            if (left[i].id <= right[j].id) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        // Add remaining elements
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }
}
