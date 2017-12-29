public class LinkedListDeque<Item> implements Deque<Item> {

    private class ItemNode {
        public Item item;
        public ItemNode prev;
        public ItemNode next;
        public ItemNode(Item i, ItemNode l, ItemNode n) {
            item = i;
            prev = l;
            next = n;
        }
    }

    private ItemNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(Item i) {
        ItemNode newNode = new ItemNode(i, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(Item i) {
        ItemNode newNode = new ItemNode(i, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ItemNode n = sentinel.next;
        for (int i = 0; i < this.size(); i++, n = n.next) {
            System.out.print(n.item);
            System.out.print(' ');
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ItemNode removedNode = sentinel.next;
        ItemNode nextNode = removedNode.next;
        sentinel.next = nextNode;
        nextNode.prev = sentinel;
        size -= 1;
        return removedNode.item;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        ItemNode removedNode = sentinel.prev;
        ItemNode prevNode = removedNode.prev;
        sentinel.prev = prevNode;
        prevNode.next = sentinel;
        size -= 1;
        return removedNode.item;
    }

    @Override
    public Item get(int index) {
        ItemNode n = sentinel.next;
        for (int i = 0; i < index ; i++) {
            if (n == sentinel) {
                return null;
            }
            n = n.next;
        }
        return n.item;
    }

    private Item getRecursiveHelper(ItemNode n, int i){
        if (i == 0) {
            if (n == sentinel) {
                return null;
            } else {
                return n.item;
            }
        }
        return getRecursiveHelper(n.next, i - 1);
    }

    public Item getRecursive(int index) {
        ItemNode first = sentinel.next;
        return getRecursiveHelper(first, index);
    }

}
