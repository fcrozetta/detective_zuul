import java.util.ArrayList;
import java.util.Iterator;
/**
 * This sets common fields between players and NPCs
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Character{
    // instance variables - replace the example below with your own
    private ArrayList<Item> bag;

    /**
     * Constructor for objects of class Character
     */
    public Character()
    {
        // initialise instance variables
        this.bag = new ArrayList<Item>();
    }
    
    /**
     * remove Item from bag
     * 
     * @param id if the Item
     */
    public void removeItem(int id){
        Iterator<Item> iter = this.bag.iterator();        
        while (iter.hasNext()) {
            Item i = iter.next();
        
            if (i.getId() == id){
                iter.remove();
            }
        }
    }
    
    /**
     * return the Item
     * 
     * @param id of the item
     * 
     * @return Item
     */
    public Item getItem(int id){
        for(Item i:this.bag){
            if(i.getId() == id){
                return i;
            }
        }
        return null;
    }
    
    /**
     * Add an item to the player's bag
     * 
     * @param Item
     * 
     */
    public void pickItem(Item item){
        this.bag.add(item);
    }
    
    /**
     * Return Items
     */
    public ArrayList<Item> getObjects(){
        return this.bag;
    }
}
