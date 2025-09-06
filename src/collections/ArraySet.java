/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fatehbhular
 */
package collections;

import java.io.*;
import java.util.*;
import entity.Player;

public class ArraySet<E> extends AbstractSet<E>{
    protected E[] elements;
    protected int numElements;
    private static final int INITIAL_CAPACITY = 16;
    
    public ArraySet() {
        super();
        numElements = 0;
        elements = (E[])(new Object[INITIAL_CAPACITY]);
    }

    public ArraySet(Collection<? extends E> c) {
        this();
        for (E element : c) {
            add(element);
        }
    }
    // adds an object to the arrayset
    @Override
    public boolean add(E o) {
        if (contains(o)) return false;
        if (numElements >= elements.length) {
            expandCapacity();
        }
        elements[numElements] = o;
        numElements++;
        return true;
    }
    // removes an object from the arrayset
    @Override
    public boolean remove(Object o) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i < numElements; i++) {
            if (elements[i].equals(o)) {
                index = i;
                found = true;
                break; // -> This was a recommendation from AI
            }
        }
        if (found) {
            elements[index] = elements[numElements - 1];
            elements[numElements - 1] = null;
            numElements--;
        }
        return found;
    }
    // iterator methods
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < numElements;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[index++];
            }

            @Override
            public void remove() {
                //remove method is not in this method
            }
        };
    }
    // returns number of elements in the arrayset
    @Override
    public int size() {
        return numElements;
    }
    // removes every element in the arrayset
    @Override
    public void clear() {
        for (int i = 0; i < numElements; i++) {
            elements[i] = null;
        }
        numElements = 0;
    }
    // doubles the arrayset's capacity
    private void expandCapacity() {
        E[] largerArray = (E[])(new Object[elements.length * 2]);
        for (int i = 0; i < numElements; i++) {
            largerArray[i] = elements[i];
        }
        elements = largerArray;
    }
}
