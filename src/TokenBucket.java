import java.util.Scanner;

public class TokenBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bucket capacity (in tokens): ");
        int bucketCapacity = sc.nextInt();

        System.out.print("Enter token generation rate (tokens/sec): ");
        int tokenRate = sc.nextInt();

        System.out.print("Enter number of incoming packets: ");
        int n = sc.nextInt();

        int[] packetSizes = new int[n];
        System.out.println("Enter size of each packet:");
        for (int i = 0; i < n; i++) {
            packetSizes[i] = sc.nextInt();
        }

        int tokens = 0;
        int time = 0;

        for (int i = 0; i < n; i++) {
            time++;

            // generate tokens for this time step
            tokens += tokenRate;
            if (tokens > bucketCapacity) {
                int overflow = tokens - bucketCapacity;
                tokens = bucketCapacity;
                System.out.println("\nTime " + time + " sec: Bucket full. " + overflow + " tokens overflowed and dropped.");
            } else {
                System.out.println("\nTime " + time + " sec: " + tokenRate + " tokens added. Current tokens = " + tokens);
            }

            System.out.println("Packet " + (i + 1) + " of size " + packetSizes[i] + " arrived.");

            if (packetSizes[i] <= tokens) {
                // enough tokens available
                tokens -= packetSizes[i];
                System.out.println("Packet fully sent. Tokens left = " + tokens);
            } else {
                // partial transfer case
                int transferred = tokens;
                int dropped = packetSizes[i] - tokens;
                tokens = 0; // all tokens used up

                System.out.println("Partial transfer: " + transferred + " units sent, " + dropped + " units dropped.");
                System.out.println("Tokens left = " + tokens);
            }
        }

        sc.close();
    }
}
