import java.util.*;

public class _2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message to be transmitted: ");
        String message = sc.nextLine().trim();
        sc.close();

        FrameSorter sorter = new FrameSorter();
        Frame[] frames = sorter.divideFrames(message);

        System.out.println("\nFrames are divided as follows:");
        for (Frame f : frames)
            System.out.println("Data: " + f.data + " | ID: " + f.id);

        frames = sorter.mergeSort(frames);

        System.out.println("\nThe sorted frames:");
        for (Frame f : frames)
            System.out.println("Data: " + f.data + " | ID: " + f.id);
    }
}

class Frame {
    String data;
    int id;
    Frame(String data, int id) {
        this.data = data;
        this.id = id;
    }
}

class FrameSorter {
    public Frame[] divideFrames(String in) {
        Random random = new Random();
        int frameCount = (int) Math.ceil(in.length() / 3.0);
        Frame[] frames = new Frame[frameCount];

        for (int i = 0; i < frameCount; i++) {
            int start = i * 3;
            int end = Math.min(start + 3, in.length());
            frames[i] = new Frame(in.substring(start, end), random.nextInt(in.length() * 10));
        }
        return frames;
    }

    public Frame[] mergeSort(Frame[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;

        Frame[] left = Arrays.copyOfRange(arr, 0, mid);
        Frame[] right = Arrays.copyOfRange(arr, mid, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private Frame[] merge(Frame[] left, Frame[] right) {
        int i = 0, j = 0, k = 0;
        Frame[] merged = new Frame[left.length + right.length];

        while (i < left.length && j < right.length)
            merged[k++] = (left[i].id < right[j].id) ? left[i++] : right[j++];

        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];
        return merged;
    }
}
