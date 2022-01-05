import java.util.Arrays;

public class MyArrayList <E> {
    private E[] elementData;

    public MyArrayList(E[] elementData) {
        this.elementData = (E[]) new Object[elementData.length];
        this.elementData = Arrays.copyOf(elementData, elementData.length - 1);
    }

    public MyArrayList() {
        this.elementData = (E[]) new Object[10];
    }

    public void add(E value) {
        int arraySize = size();
        if(elementData.length <= arraySize) {
            int capacity = Math.round((arraySize * 3) / 2 + 1);
            elementData = Arrays.copyOf(elementData, capacity);
        }
        elementData[arraySize] = value;
    }

    public void remove(int index) {
        int numMoved = size() - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[size() - 1] = null;
    }

    public void clear() {
        elementData = (E[]) new Object[0];
    }

    public int size() {
        int size = 0;
        for(E element: elementData) {
            if(element == null) return size;
            size = size + 1;
        }
        return size;
    }

    public E get(int index) {
        return elementData[index];
    }
}

