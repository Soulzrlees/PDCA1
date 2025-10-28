/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collections;

/**
 *
 * @author Shawn lee
 */
public class Node <T>{
    public T data;
    public Node<T> next;
    public Node<T> prev;
    
    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
