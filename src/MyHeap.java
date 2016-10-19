import java.util.Comparator;
import java.util.List;

/**
 * Created by Ilgiz on 19.07.2016.
 */

public class MyHeap<E> {

    private Object arr[];
    private int last;
    private int capacity;
    private Comparator comparator;

    public MyHeap() {
        arr = new Object[10];
        last = 0;
        capacity = 10;

    }

    public MyHeap(Comparator comparator) {
        this();
        this.comparator = comparator;
    }

    public MyHeap(int cap) {
        arr = (E[]) new Object[cap + 1];
        last = 0;
        capacity = cap;
    }

    public int size() {
        return last;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E max() {
        if (isEmpty())
            return null;
        else
            return (E) arr[0];
    }

    private int compare(E x, E y) {
        return comparator.compare(x, y);
    }

    public boolean add(E e) {
        if (size() == capacity)
            return false;
        else {
            last++;
            arr[last] = e;
            upHeapBubble();
            return true;
        }
    }


    public E pool() {
        if (isEmpty())
            return null;
        else {
            E max = max();
            last--;
            arr[0] = arr[last];
            downHeapBubble(0);
            return max;
        }
    }

    /**
     * downHeapBubble() method is used after the pool() method to reorder the elements
     * in order to preserve the MyHeap properties
     */

    private void downHeapBubble(int index) {
        while (true) {
            int child = index * 2;
            if (child >= size())
                break;
            if (child + 1 < size()) {
                child = findMax(child, child + 1);
            }
            if (child == index) {
                break;
            }
            if (compare((E) arr[index], (E) arr[child]) > 0)
                break;
            swap(index, child);
            index = child;
        }
    }

    /**
     * upHeapBubble() method is used after the add(E e) method to reorder the elements
     * in order to preserve the MyHeap properties
     */
    private void upHeapBubble() {
        int index = size();
        while (index > 1) {
            int parent = index / 2;
            if (compare((E) arr[index], (E) arr[parent]) < 0)
                break;
            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Swaps two elements i and j
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        E temp = (E) arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * the method is used in the downHeapBubble() method
     *
     * @param leftChild
     * @param rightChild
     * @return max of left and right child, if they are equal return the left
     */
    private int findMax(int leftChild, int rightChild) {
        return (compare((E) arr[leftChild], (E) arr[rightChild]) > 0) ? leftChild : rightChild;
    }

    public List<E> buildHeap(List<E> list) {

        arr = list.toArray();
        int size = list.size();
        last = size;
        for (int i = size / 2; i >= 0; i--) {
            downHeapBubble(i);
        }
        list.clear();
        for (int i = 0; i < size; i++) {
            list.add(pool());
        }
        return list;
    }

}




