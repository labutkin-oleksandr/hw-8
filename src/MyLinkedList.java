public class MyLinkedList <E> {
    private Node<E> first;
    private Node<E> last;
    private int N;

    public MyLinkedList() {
        first = null;
        last = null;
        N = 0;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(E value) {
        Node<E> newEntry = new Node<>(last, value, null);
        if(N == 0) {
            first = newEntry;
        }
        if(last != null) {
            last.next = newEntry;
        }
        N++;
        last = newEntry;
    }

    public void remove(int index) {
        if(N == 0) {
            return;
        }

        Node<E> e = this.getNode(index);

        if(index != 0) {
            e.prev.next = e.next;
            if(index != size() - 1) {
                e.next.prev = e.prev;
            }
            e.next = e.prev = null;
        } else {
            first = e.next;
            e.next = null;
        }
        e.element = null;
        N--;
    }

    public void clear() {
        for (int i = 0; i < size(); i++) {
            remove(i);
        }
        first = null;
        last = null;
        N = 0;
    }

    public int size() {
        return N;
    }

    public E get(int index) {
        Node<E> e = getNode(index);
        return e.element;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node<E> e;

        if (index < (size() >> 1)) {
            e = first;
            for (int i = 1; i <= index; i++) {
                e = e.next;
            }
        } else {
            e = last;
            for (int i = size() - 1; i > index; i--) {
                e = e.prev;
            }
        }

        return e;
    }

    @Override
    public String toString() {
        if (N == 0 && this.first == null && this.last == null) {
            return "[]";
        }
        StringBuilder MyQueueToString = new StringBuilder();
        MyQueueToString.append("[");
        Node<E> l = this.first;
        int count = 0;
        while (count < N) {
            count++;
            MyQueueToString.append(count != N ? l.element + "; " : l.element);
            if(count != N) {
                l = l.next;
            }
        }
        MyQueueToString.append("]");
        return new String(MyQueueToString);
    }
}
