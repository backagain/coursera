import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by apple on 15/3/10.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;


    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(0, N);
        Item item = a[random];
        if (a[random] == null) throw new NoSuchElementException();
        if (random != N - 1) {
            a[random] = a[N - 1];
        }
        a[N - 1] = null;
        N--;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(0, N);
        if (a[random] == null) throw new NoSuchElementException();
        return a[random];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Item b[];

        public RandomizedQueueIterator() {
            b = (Item[]) new Object[N];
            for (int j = 0; j < N; j++)
                b[j] = a[j];
            i = N - 1;
            StdRandom.shuffle(b);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return b[i--];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue q = new RandomizedQueue();
        q.dequeue();
    }
}
