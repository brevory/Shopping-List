package Shopping;

import DataStructures.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListArrayList implements ShoppingListADT {

    private ArrayList<Grocery> shoppingList;

    /**
     * Default constructor of ShoppingArray object.
     */
    public ShoppingListArrayList() {
        this.shoppingList = new ArrayList<>();
    }

    /**
     * Constructor of ShoppingArray object that parses from a file.
     *
     * @param filename the name of the file to parse
     * @throws FileNotFoundException if an error occurs when parsing the file
     */
    public ShoppingListArrayList(String filename) throws FileNotFoundException {
        this.shoppingList = new ArrayList<>();
        scanFile(filename);
    }

    /**
     * Method to add a new entry. Only new entries can be added. Combines 
     * quantities if entry already exists.
     *
     * @param entry the entry to be added
     */
    @Override
    public void add(Grocery entry) {
        if (entry == null) {
            return;
        }
        
        // Check if this item already exists
        if (this.contains(entry)) {
            
            //Merge the quantity of new entry into existing entry
            combineQuantity(entry);
            return;
        }
        shoppingList.add(entry);
        return;
    }

    /**
     * Method to remove an entry.
     *
     * @param ent to be removed
     * @return true when entry was removed
     */
    @Override
    public boolean remove(Grocery ent) {
        
        boolean found = shoppingList.remove(ent);
        return found;
    }

    /**
     * Method to find an entry.
     *
     * @param index to find
     * @return the entry if found
     * @throws EmptyCollectionException if called with empty collection
     */
    @Override
    public Grocery find(int index) throws IndexOutOfBoundsException,
            EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("ECE - find");
        }
        if (index<0 || index>shoppingList.size() -1){
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        return (Grocery)this.shoppingList.get(index);
    }

    /**
     * Method to locate the index of an entry.
     *
     * @param ent to find the index
     * @return the index of the entry
     * @throws ElementNotFoundException if no entry was found
     */
    @Override
    public int indexOf(Grocery ent) throws ElementNotFoundException {
        if (ent != null) {
            for (int i = 0; i < shoppingList.size(); i++) {
                if (shoppingList.get(i).compareTo(ent) == 0) {
                    return i;
                }
            }
        }
        throw new ElementNotFoundException("indexOf");
    }

    /**
     * Method to determine whether the object contains an entry.
     *
     * @param ent to find
     * @return true if and only if the entry is found
     */
    @Override
    public boolean contains(Grocery ent) {
        boolean hasItem = false;
        
        if (ent != null) {
            for(int i = 0; i<shoppingList.size(); i++)
            {
                try
                {
                if(ent.compareTo(this.find(i))==0)
                    hasItem=true;
                }
                catch(EmptyCollectionException E)
                        {
                        System.out.println("The list is empty");
                        }
            }
            
        }
        return hasItem;
    }

    /**
     * Gets the size of the collection.
     *
     * @return the size of the collection
     */
    @Override
    public int size() {
        return shoppingList.size();
    }

    /**
     * Gets whether the collection is empty.
     *
     * @return true if and only if the collection is empty
     */
    @Override
    public boolean isEmpty() {
        return shoppingList.isEmpty();
    }

    /**
     * Returns a string representing this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%-25s", "NAME"));
        s.append(String.format("%-18s", "CATEGORY"));
        s.append(String.format("%-10s", "AISLE"));
        s.append(String.format("%-10s", "QUANTITY"));
        s.append(String.format("%-10s", "PRICE"));
        s.append('\n');
        s.append("------------------------------------------------------------"
                + "-------------");
        s.append('\n');
        for (int i = 0; i < shoppingList.size(); i++) {
            s.append(String.format("%-25s", shoppingList.get(i).getName()));
            s.append(String.format("%-18s", shoppingList.get(i).getCategory()));
            s.append(String.format("%-10s", shoppingList.get(i).getAisle()));
            s.append(String.format("%-10s", shoppingList.get(i).getQuantity()));
            s.append(String.format("%-10s", shoppingList.get(i).getPrice()));
            s.append('\n');
            s.append("--------------------------------------------------------"
                    + "-----------------");
            s.append('\n');
        }

        return s.toString();
    }

    /**
     * Add the quantity of a duplicate entry into the existing
     *
     * @param entry duplicate
     */
    private void combineQuantity(Grocery entry) {
        try {
            int index = this.indexOf(entry);
            shoppingList.get(index).setQuantity(
                    shoppingList.get(index).getQuantity()
                    + entry.getQuantity());
        } 
        catch (ElementNotFoundException e) {
            System.out.println("combineQuantity - ECE");
        }

    }

    /**
     * Scans the specified file to add items to the collection.
     *
     * @param filename the name of the file to scan
     * @throws FileNotFoundException if the file is not found
     */
    private void scanFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(getClass().getResourceAsStream(filename))
                .useDelimiter("(,|\r\n)");

        while (scanner.hasNext()) {
            Grocery temp = new Grocery(scanner.next(), scanner.next(),
                    Integer.parseInt(scanner.next()),
                    Float.parseFloat(scanner.next()),
                    Integer.parseInt(scanner.next()));
            
            add(temp);
        }
    }

}
