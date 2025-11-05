import java.util.Scanner;

public class leakyBucket {
    public static void main(String[] args) {
        leakyBucket obj = new leakyBucket();
        obj.helper();
    }

    public void helper() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();
        System.out.print("Enter bucket size: ");
        int bucketSize = sc.nextInt();
        System.out.print("Enter output rate: ");
        int rate = sc.nextInt();

        int[] packets = new int[n];
        System.out.println("Enter size of each packet:");
        for (int i = 0; i < n; i++) {
            packets[i] = sc.nextInt();
        }

        int currSize = 0; // current data in bucket

        for (int i = 0; i < n; i++) {
            System.out.println("\nPacket " + (i + 1) + " of size " + packets[i] + " arrives.");

            // Leak packets before new one arrives
            if (currSize > 0) {
                int leaked = Math.min(currSize, rate);
                currSize -= leaked;
                System.out.println(leaked + " units leaked before arrival. Current bucket: " + currSize);
            }

            // Check if new packet can fit
            if (packets[i] + currSize > bucketSize) {
                System.out.println("Bucket overflow! Packet " + (i + 1) + " dropped.");
            } else {
                currSize += packets[i];
                System.out.println("Packet " + (i + 1) + " added. Current bucket: " + currSize);
            }

            // Leak again after processing (simulate time passing)
            int leakedAfter = Math.min(currSize, rate);
            currSize -= leakedAfter;
            System.out.println(leakedAfter + " units leaked after processing. Remaining in bucket: " + currSize);
        }

        // Empty remaining bucket
        while (currSize > 0) {
            int leaked = Math.min(currSize, rate);
            currSize -= leaked;
            System.out.println("Leaking remaining data... " + leaked + " units. Bucket now: " + currSize);
        }
    }
}
