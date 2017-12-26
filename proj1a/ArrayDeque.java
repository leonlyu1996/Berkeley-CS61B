public class ArrayDeque<Item> {

    private int size;
    private int nextFirst;
    private int nextLast;
    Item[] items;

    private int minusOne(int index) {
        if (index > 0) {
            return index - 1;
        } else {
            return index - 1 + items.length;
        }
    }

    private int plusOne(int index) {
        if (index < items.length - 1) {
            return index + 1;
        } else {
            return index + 1 - items.length;
        }
    }

    private void resize(int newSize) {
        Item[] resizedArray = (Item[]) new Object[newSize];

        for (int i = 0, nextCopied = plusOne(nextFirst); i < size; i++) {
            resizedArray[i] = items[nextCopied];
            nextCopied = plusOne(nextCopied);
        }

        items = resizedArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void setSize() {
        // If the size of the array is less than the number of items, double the size.
        if (size >= items.length) {
            resize(items.length * 2);
        }
        // For arrays of length 16 or more, the usage factor should always be at least 25%.
        else if (size * 4 < items.length && items.length >= 16) {
            resize(items.length / 2);
        }
    }

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(Item i) {
        setSize();
        size += 1;
        items[nextFirst] = i;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(Item i) {
        setSize();
        size += 1;
        items[nextLast] = i;
        nextLast = plusOne(nextLast);
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        size -= 1;
        setSize();
        return items[nextFirst];
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast);
        size -= 1;
        setSize();
        return items[nextLast];
    }

    public Item get(int index) {
        if (index >= size || index < 0 || isEmpty()) {
            return null;
        }
        int itemIndex = nextFirst + index + 1;
        if (itemIndex >= items.length) {
            itemIndex -= items.length;
        }
        return items[itemIndex];
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int n = plusOne(nextFirst); n != nextLast; n = plusOne(n)) {
            System.out.print(items[n]);
            System.out.print(' ');
        }
        System.out.println();
    }

}
