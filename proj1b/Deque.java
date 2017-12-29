public interface Deque<Item> {

    /**
     * Adds an item to the front of the Deque.
     */
    public void addFirst(Item i);

    /**
     * Adds an item to the back of the Deque.
     */
    public void addLast(Item i);

    /**
     * Removes an item from the front of the Deque.
     */
    public Item removeFirst();

    /**
     * Removes an item from the back of the Deque.
     */
    public Item removeLast();

    /**
     *
     * @return true if the Deque is empty.
     */
    public boolean isEmpty();

    /**
     *
     * @return the number of items in the Deque.
     */
    public int size();

    /**
     * Prints the items in the Deque from first to last, seperated by a space.
     */
    public void printDeque();

    /**
     * Gets the item at the given index.
     * @param index 0 is the front, 1 is the next item, and so forth.
     * @return the item at the given index, return null if no such item.
     */
    public Item get(int index);

}
