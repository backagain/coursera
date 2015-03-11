import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by apple on 15/3/10.
 */
public class Deque<Item> implements Iterable<Item> {
    private int N;       //size of the stack
    private Node first;  //top of the stack
    private Node last;   //bottom of the stack

    private class Node {
        private Item item;
        private Node next;
        private Node pre;
    }

    //init empty stack.
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null || last == null || N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else first.next.pre = first;
        N++;
    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.pre = oldlast;
        if (isEmpty()) first = last;
        else last.pre.next = last;
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node returnNode = first;
        first = first.next;
        N--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            first.pre = null;
        }

        return returnNode.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node returnNode = last;
        last = last.pre;
        N--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last.next = null;
        }

        return returnNode.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}
