/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.Serializable;

/**
 *
 * @author joanp
 * @param <T>
 */
public class Stack<T> implements Serializable {

    Node<T> first;
    int size;

    public Stack() {
        this.first = null;
        this.size = 0;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> aux = first;
            int counter = 0;
            while (counter < size - 1 && aux.next != null) {
                aux = aux.next;
                counter++;
            }
            T element = aux.element;
            return element;
        }
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            this.first = newNode;
        } else {
            Node<T> aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;
        }
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> aux = first;
            int counter = 0;
            while (counter < size - 1 && aux.next != null) {
                aux = aux.next;
                counter++;
            }
            T element = aux.element;
            size--;
            return element;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
}
