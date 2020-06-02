import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {

        size = 0;
        items = (Item[]) new Object[2];

        // "2" is the initial container size (space), "size" is the real item numbers.

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        items[size++] = item;
        if (size == items.length) resize(2 * items.length);
    }

    // remove and return a random item
    public Item dequeue() {
        int random = StdRandom.uniform(size);
        Item item = items[random];
        if (random == size - 1) size--;
        else items[random] = items[--size];
        if (size > 0 && size <= items.length/4) resize(items.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int random = StdRandom.uniform(size);
        return items[random];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        
        private Item[] queue;
        private int current;

        public QueueIterator() {
            Item[] copy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                copy[i] = items[i];
            queue = copy;
            StdRandom.shuffle(queue);
            current = 0;
        }
        public boolean hasNext() { return current < queue.length; } // do not use size here, use local variable.
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("no more data");
            return queue[current++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        for (String s : queue)
            System.out.print(s);
        System.out.println("");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("");
        for (String s : queue)
            System.out.print(s);
    }

}