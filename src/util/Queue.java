package util;

import java.io.Serializable;

/**
 *
 * @author joanp
 * @param <T>
 */
public class Queue<T> implements Serializable{

    Node<T> first;
    int size;

    public Queue() {
        this.first = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void enqueue(T element) {
        Node<T> newest = new Node<>(element);
        if (isEmpty()) {
            this.first = newest;
        } else {
            Node<T> aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newest;
        }
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("No data in the queue");
        }
        return this.first.element;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("No data in the queue");
        }
        T element = this.first.element;
        this.first = first.next;
        size--;
        return element;
    }
    
    public T get(int index) {

        if (index == 0) {
            return this.first.element;

        } else {
            Node<T> aux = first;
            int counter = 0;
            while (counter < index) {
                aux = aux.next;
                counter++;

            }
            return aux.element;
        }
    }
}
