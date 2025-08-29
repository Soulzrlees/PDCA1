/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collections;


import entity.Potion;

/**
 *
 * @author Shawn lee
 */
public class LinkedList <T>{
    public int size = 0;
    public Node<T> head;
    public Node<T> tail;
    
    
    //addTail method is used when a potion is added into the inventory
    public void addTail(T data) {
        Node<T> newTail = new Node<>(data);          
        
        newTail.next = null;
        newTail.prev = tail;
        
        if(tail != null){ //If the Inventory is not empty, then the new last potion is the added potion
            tail.next = newTail;
        }
        tail = newTail;
        
        if(head == null){ //If the Inventory is empty, the new Potion is the first potion in the list
            head = newTail;
        }
        size++;
    }
    
    //removeByIndex method is used to remove a potion when the user chooses a specific number
    public T removeByIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        
        Node<T> current = head;
        
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        
        T Potion = current.data;
        
        if(current.prev != null){ //If the potion before this potion is not null than the next potion from the previous potion is the new net potion from the previous potion;
            current.prev.next = current.next;
        }
        else{
            head = current.next;
        }
        
        if(current.next != null){ //If the next potion is not null than the current potion is now is linked to the previous potion.
            current.next.prev = current.prev;
        }
        else{
            tail = current.prev;
        }
        size--;
        return Potion;
    }



    //Display Inventory potions
    public void displayInventory(){
        Node<T> current = head;
        int index = 0;
        System.out.println("______Inventory______");
        while(current != null){
            System.out.println("["+ index + "] " + current.data);
            current = current.next;
            index++;
        }
        System.out.println("_____________________");
    }
}
