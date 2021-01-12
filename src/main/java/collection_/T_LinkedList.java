package collection_;

import java.util.LinkedList;

public class T_LinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
    }

    static class LinkedList_<E>
    {

        // 集合中的元素数量
        transient int size = 0;

        /**首节点的指针.*/
        transient Node<E> first;

        /**尾结点的指针.*/
        transient Node<E> last;

        int modCount;

        /**内部类.*/
        private static class Node<E> {
            E item;
            Node<E> next;
            Node<E> prev;

            Node(Node<E> prev, E element, Node<E> next) {
                this.item = element;
                this.next = next;
                this.prev = prev;
            }
        }

        Node<E> node(int index) {
            // assert isElementIndex(index);

            if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.prev;
                return x;
            }
        }

        /**尾部添加元素.*/
        public boolean add(E e) {
            linkLast(e);
            return true;
        }

        /**尾部添加元素.*/
        void linkLast(E e) {
            final Node<E> l = last;
            final Node<E> newNode = new Node<>(l, e, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
            modCount++;
        }

        /**头部添加元素.*/
        private void linkFirst(E e) {
            final Node<E> f = first;
            final Node<E> newNode = new Node<>(null, e, f);
            first = newNode;
            if (f == null)
                last = newNode;
            else
                f.prev = newNode;
            size++;
            modCount++;
        }

        /**移除元素.*/
        public E remove(int index) {
            checkElementIndex(index);
            return unlink(node(index));
        }

        /**固定位置解链.*/
        E unlink(Node<E> x) {
            // assert x != null;
            final E element = x.item;
            final Node<E> next = x.next;
            final Node<E> prev = x.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }

            x.item = null;
            size--;
            modCount++;
            return element;
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+size;
        }

        private void checkElementIndex(int index) {
            if (!isElementIndex(index))
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void checkPositionIndex(int index) {
            if (!isPositionIndex(index))
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private boolean isElementIndex(int index) {
            return index >= 0 && index < size;
        }

        private boolean isPositionIndex(int index) {
            return index >= 0 && index <= size;
        }


    }
}
