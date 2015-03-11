/**
 * Created by apple on 15/3/10.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue q = new RandomizedQueue();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
