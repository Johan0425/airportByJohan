package util;

import java.io.Serializable;

/**
 *
 * @author joanp
 * @param <T>
 */
public class Node<T> implements Serializable{
    
    T element;
    Node<T> next;
    
    public Node(T element) {
        this.element = element;
        this.next = null;
    }
    
}
