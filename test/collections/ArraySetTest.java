/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 *
 * @author Shawn lee
 */
public class ArraySetTest {

    private ArraySet<String> set;

    @Before
    public void setUp() {
        set = new ArraySet<>();
    }

    @Test
    public void testAdd() {
        assertTrue(set.add("A"));
        assertFalse(set.add("A")); 
        assertEquals(1, set.size());
    }

    @Test
    public void testRemove() {
        set.add("A");
        set.add("B");

        assertTrue(set.remove("A"));
        assertEquals(1, set.size());

        assertFalse(set.remove("C")); 
        assertEquals(1, set.size());
    }

    @Test
    public void testClear() {
        set.add("A");
        set.add("B");
        set.clear();
        assertEquals(0, set.size());
        assertFalse(set.iterator().hasNext());
    }

    @Test
    public void testIterator() {
        set.add("A");
        set.add("B");

        Iterator<String> it = set.iterator();
        assertTrue(it.hasNext());
        String first = it.next();
        assertTrue(first.equals("A") || first.equals("B"));
        assertTrue(it.hasNext());
        String second = it.next();
        assertTrue(second.equals("A") || second.equals("B"));
        assertFalse(it.hasNext());
    }

    @Test
    public void testExpandCapacity() {
        for (int i = 0; i < 20; i++) {
            set.add("E" + i);
        }
        assertEquals(20, set.size());
    }
}
