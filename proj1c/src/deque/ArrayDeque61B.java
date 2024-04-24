package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private final int INIT_SIZE = 8;

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        items = (T[]) new Object[INIT_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = nextFirst + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDeque61BIterator() {
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

    private int getRealIndex(int index) {
        return Math.floorMod(index, items.length);
    }

    @SuppressWarnings("unchecked")
    private void resizing() {
        if (size == 0) {
            return;
        }
        int newLength = (int) (size / 0.25);
        T[] newItems = (T[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = getRealIndex(nextFirst - 1);
        size += 1;
        if (size == items.length) {
            resizing();
        }
    }

    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = getRealIndex(nextLast + 1);
        size += 1;
        if (size == items.length) {
            resizing();
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = getRealIndex(nextFirst + 1);
        int loopSize = size;
        while (loopSize > 0 && items[index] != null) {
            returnList.add(items[index]);
            index = getRealIndex(index + 1);
            loopSize -= 1;
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
        nextFirst = getRealIndex(nextFirst + 1);
        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if (items.length * 0.25 >= size && size > INIT_SIZE) {
            resizing();
        }
        return removeItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = getRealIndex(nextLast - 1);
        T removeItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (items.length * 0.25 > size && size > INIT_SIZE) {
            resizing();
        }
        return removeItem;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int targetIndex = getRealIndex(nextFirst + 1 + index);
        return items[targetIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque61B<?> other) {
            if (other.size != this.size) {
                return false;
            }
            for (int i = 0; i < this.size; i++) {
                if (this.get(i) != other.get(i)) {
                    return false;
                }
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
