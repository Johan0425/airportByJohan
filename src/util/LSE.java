/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.Serializable;

/**
 *
 * @author joanp
 *
 * @param <S> tipo de dato generico
 */
public class LSE<S> implements Serializable {

    Node<S> first;
    int size;

    public LSE() {

        this.first = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void addDato(S data) {
        Node<S> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
        } else {
            Node<S> aux = first;
            while (aux.next != null) {
                aux = aux.next;

            }
            aux.next = newNode;

        }
        size++;
    }

    public void add(S data, int index) {
        validateIndex(index);
        Node<S> newNode = new Node<>(data);
        if (first == null || index >= size) {
            if (first == null) {
                first = newNode;
            } else {
                Node<S> aux = first;
                while (aux.next != null) {
                    aux = aux.next;

                }
                aux.next = newNode;
            }

        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<S> aux = first;
            int counter = 0;
            while (counter < index - 1) {
                aux = aux.next;
                counter++;

            }
            newNode.next = aux.next;
            aux.next = newNode;
        }
        size++;

    }

    public S get(int index) {

        if (index == 0) {
            return this.first.element;

        } else {
            Node<S> aux = first;
            int counter = 0;
            while (counter < index) {
                aux = aux.next;
                counter++;

            }
            return aux.element;
        }
    }

    public void remove(int index) {
        validateIndex(index);
        if (first != null) {
            if (index == 0) {
                first = first.next;

            } else {
                Node<S> aux = first;
                int contador = 0;
                while (contador < index - 1 && aux.next != null) {
                    aux = aux.next;
                    contador++;

                }
                if (aux.next != null) {
                    aux.next = aux.next.next;

                }

            }
            size--;

        }
    }

    public void clear() {
        int index = size - 1;
        while (index >= 0) {
            remove(index);
            index--;
        }
    }

    public void validateIndex(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("para el indice: " + index);
        }
    }

    public void edit(S newData, int index) throws Exception {
        if (first == null) {
            throw new Exception("La lista está vacía");
        }

        int counter = 0;
        Node<S> current = first;
        boolean found = false;

        while (current != null) {
            if (counter == index) {
                current.element = newData;
                found = true;
                break;
            }

            current = current.next;
            counter++;
        }

        if (!found) {
            throw new Exception("El elemento no está en la lista");
        }
    }

}
