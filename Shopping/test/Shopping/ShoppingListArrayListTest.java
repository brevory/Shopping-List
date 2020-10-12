package Shopping;

import DataStructures.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ShoppingListArrayListTest {

    //private ShoppingListArrayList instance;

    /**
     * Initialize instance and entries
     */
    @Before
    public void setupTestCases() {
        //instance = new ShoppingListArrayList();
    }

    /**
     * test of add method, of class ShoppingArray.
     */
    @Test
    public void testAdd() {
        //Create grocery objects and a shopping list instance
        Grocery item1 = new Grocery("Harry Potter", "book", 3, 
                15.5f, 2);
        Grocery item2 = new Grocery(item1);
        item2.setQuantity(3);
 
        ShoppingListArrayList instance = new ShoppingListArrayList();
        instance.add(item1); 
        try{
            assertTrue(instance.size() == 1);
            assertTrue(instance.find(0) == item1);
        }
        catch (EmptyCollectionException E)
        {
            E.printStackTrace();
        }
        
        // Test the "combine" feature of the add method.
        try{
        instance.add(item2);
        assertTrue("assert3",instance.size()==1);
        assertTrue("assert4",instance.find(0).getQuantity() == 5);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        instance.add(null);
        assertTrue(1 == instance.size());
        Grocery item3 = new Grocery("Laugh in the Rains", "book", 3, 
                35.5f, 1);
        instance.add(item3);
        System.out.println("Test..." + instance.size());
        assertTrue(2 == instance.size());
        try{
            assertTrue(item3.compareTo(instance.find(1)) == 0);
            assertTrue(1 == item3.getQuantity());
            assertTrue(1 == instance.find(1).getQuantity());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Test of remove method, of class ShoppingArrayList.
     */
    @Test
    public void testRemove() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3, 
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3, 
                10.5f, 3);
        ShoppingListArrayList instance = new ShoppingListArrayList();
        instance.add(item1);
        instance.add(item2);
        assertTrue(2 == instance.size());
        assertTrue(instance.remove(item1));
        assertTrue(instance.size()==1);
        assertFalse(instance.contains(item1));
        Grocery item3 = new Grocery("Bread", "food", 2, 11.5f, 4);
        assertFalse(instance.remove(item3));
        assertTrue(instance.size()==1);
        
        // Construct a case that the shopping list becomes empty
        boolean isRemoved;
        isRemoved = instance.remove(item2);
        assertTrue(isRemoved == true);
        assertTrue(0 == instance.size());
        isRemoved = instance.remove(item2);
        assertTrue(isRemoved == false);
        assertTrue(0 == instance.size());
    }

    /**
     * Test of find method, of class ShoppingArrayList.
     */
    @Test
    public void testFind() {
        Grocery item1 = new Grocery("Harry Potter", "book", 3, 
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3, 
                10.5f, 3);
        ShoppingListArrayList instance = new ShoppingListArrayList();
        
        // Tests the case of finding a grocery object when the shopping list is empty
        try{
            instance.find(3);
            assertTrue(false);
        }
        catch(EmptyCollectionException E)
        {
            assertTrue(true);
        }
        instance.add(item1);
        instance.add(item2);
        assertTrue(2 == instance.size());
        
        // Test the case of finding a grocery object which index is larger than the shopping list size
        try{
            instance.find(5);
            assertTrue(false);
        }
        catch(IndexOutOfBoundsException E)
        {
            assertTrue(true);
        }
        catch(EmptyCollectionException E)
        {
            assertTrue(false);
        }

        // Test the case of finding a grocery object which index is negative
        try{
            Grocery item = instance.find(-5);
            assertTrue(0 == 1);
        } catch (IndexOutOfBoundsException ex){
            assertTrue(1 == 1);
        } catch (Exception ex){
            assertTrue(0 == 1);
        }
        
        // Test the case of finding a grocery object which index is negative
        try{
            Grocery item = instance.find(0);
            assertTrue(item1.compareTo(item) == 0);
        } catch (IndexOutOfBoundsException ex){
            assertTrue(0 == 1);
        } catch (EmptyCollectionException ex){
            assertTrue(0 == 1);
        } catch (Exception ex){
            assertTrue(0 == 1);
        }
    }

    /**
     * Test of indexOf method, of class ShoppingArrayList.
     */
    @Test
    public void testIndexOf() {
        Grocery item1 = new Grocery("Harry Potter", "book", 3, 
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3, 
                10.5f, 3);
        ShoppingListArrayList instance = new ShoppingListArrayList();
        
        // Check the indexOf method when the shopping list is empty
        try{
            int index = instance.indexOf(item1);
        } catch (ElementNotFoundException ex){
            assertTrue(1 == 1);
        } catch (Exception ex){
            assertTrue(0 == 1);
        }
        
        // Add grocery items into the shopping list
        instance.add(item1);
        instance.add(item2);
        
        // Check the indexOf method when the grocery item appears in the list
        try{
            int index = instance.indexOf(item2);
            assertTrue(1 == 1);
            assertTrue(index == 1);
            index = instance.indexOf(item1);
            assertTrue(1 == 1);
            assertTrue(index == 0);
        } catch (ElementNotFoundException ex){
            assertTrue(0 == 1);
        } catch (Exception ex){
            assertTrue(0 == 1);
        }
        
        // Check the indexOf method when the grocery item does not appear in the list
        try{
            Grocery item3 = new Grocery("Aladin", "book", 3, 
                15.5f, 2);
            int index = instance.indexOf(item3);
        } catch (ElementNotFoundException ex){
            assertTrue(1 == 1);
        } catch (Exception ex){
            assertTrue(0 == 1);
        }
        
        // Check the indexOf method when the grocery item is null
        try{
            Grocery obj = null;
            int index = instance.indexOf(obj);
        } catch (ElementNotFoundException ex){
            assertTrue(1 == 1);
        } catch (Exception ex){
            ex.printStackTrace();
            assertTrue(0 == 1);
        }
    }

    /**
     * Test of contains method, of class ShoppingArrayList.
     */
    @Test
    public void testContains() {
        //Create grocery objects
        Grocery item1 = new Grocery("Harry Potter", "book", 3, 
                15.5f, 2);
        Grocery item2 = new Grocery("Hunger Game", "book", 3, 
                10.5f, 3);
        
        // Construct a shopping list
        ShoppingListArrayList instance = new ShoppingListArrayList();
        
        // Check the contains method when the shopping list is empty
        boolean isTrue = instance.contains(item1);
        assertFalse(isTrue);
        
        // Add grocery items into the shopping list
        instance.add(item1);
        instance.add(item2);
        
        // Check the contains method when the grocery item appears in the list
        isTrue = instance.contains(item2);
        assertTrue(isTrue);
        
        // Check the contains method when the grocery item does not appear in the list
        Grocery item3 = new Grocery("Aladin", "book", 3, 
                15.5f, 2);
        isTrue = instance.contains(item3);
        assertFalse(isTrue);
        
        // Check the contains method when the grocery item is null
        Grocery obj = null;
        isTrue = instance.contains(obj);
        assertFalse(isTrue);
    }

    /**
     * Test of size method, of class ShoppingArrayList.
     */
    @Test
    public void testSize() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        ShoppingListArrayList instance = new ShoppingListArrayList();

        assertEquals(0, instance.size());

        instance.add(entry1);

        // Test increment
        assertEquals(1, instance.size());

        assertTrue(instance.remove(entry1));

        // Test decrement
        assertEquals(0, instance.size());
    }

    /**
     * Test of isEmpty method, of class ShoppingArrayList.
     */
    @Test
    public void testIsEmpty() {
        Grocery entry1 = new Grocery("Mayo", "Dressing / Mayo", 1, 2.99f, 1);
        ShoppingListArrayList instance = new ShoppingListArrayList();

        // Test empty
        assertTrue(instance.isEmpty());

        instance.add(entry1);

        // Test not empty
        assertFalse(instance.isEmpty());
    }
}