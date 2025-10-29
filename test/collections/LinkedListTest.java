/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shawn lee
 */
public class LinkedListTest {

    private LinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void testAddTail() {
        list.addTail(10);
        assertEquals(1, list.size);
        assertEquals(Integer.valueOf(10), list.head.data);
        assertEquals(Integer.valueOf(10), list.tail.data);

        list.addTail(20);
        assertEquals(2, list.size);
        assertEquals(Integer.valueOf(10), list.head.data);
        assertEquals(Integer.valueOf(20), list.tail.data);
    }

    @Test
    public void testRemoveByIndex() {
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);

        // Remove middle element
        assertEquals(Integer.valueOf(2), list.removeByIndex(1));
        assertEquals(2, list.size);

        // Remove head
        assertEquals(Integer.valueOf(1), list.removeByIndex(0));
        assertEquals(1, list.size);
        assertEquals(Integer.valueOf(3), list.head.data);

        // Remove tail
        assertEquals(Integer.valueOf(3), list.removeByIndex(0));
        assertEquals(0, list.size);
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveByIndexInvalid() {
        list.addTail(1);
        list.removeByIndex(1); 
    }
}
