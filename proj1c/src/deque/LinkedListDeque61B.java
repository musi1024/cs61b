package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {


    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    final private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator();
    }

    private class LinkedListDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDeque61BIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public void addFirst(T x) {
        Node node = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node target = sentinel.next;
        while (target != sentinel) {
            returnList.add(target.item);
            target = target.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node target = sentinel.next;
        target.next.prev = sentinel;
        sentinel.next = target.next;
        size -= 1;
        return target.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node target = sentinel.prev;
        target.prev.next = sentinel;
        sentinel.prev = target.prev;
        size -= 1;
        return target.item;
    }

    @Override
    public T get(int index) {
        Node target = sentinel.prev;
        while (index > 0 && target.item != null) {
            index -= 1;
            target = target.prev;
        }
        return target.item;
    }

    private T getRecursiveHelper(int index, Node node) {
        if (index > size - 1 || index < 0 || node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque61B<?> other) {
            if (other.size != this.size) {
                return false;
            }
            Node curTarget = this.sentinel.next;
            Node otherTarget = (Node) other.sentinel.next;
            while (curTarget != sentinel) {
                if (curTarget.item != otherTarget.item) {
                    return false;
                }
                curTarget = curTarget.next;
                otherTarget = otherTarget.next;
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("[");
        for (T x : this) {
            returnSB.append(x.toString());
            returnSB.append(", ");
        }
        int length = returnSB.length();
        returnSB.delete(length - 2, length);
        returnSB.append("]");
        return returnSB.toString();
    }

}
