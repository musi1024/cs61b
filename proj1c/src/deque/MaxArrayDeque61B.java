package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private final Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            int compare = comparator.compare(get(i), maxItem);
            if (compare > 0) {
                maxItem = get(i);
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            int compare = c.compare(get(i), maxItem);
            if (compare > 0) {
                maxItem = get(i);
            }
        }
        return maxItem;
    }
}
