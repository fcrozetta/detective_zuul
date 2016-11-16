import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class creates a wall, where the objects, doors, and furnitures will be placed.
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public class Side extends Thing
{
    private ArrayList<Thing> allObjects;

    /**
     * Constructor for objects of class Side
     */
    public Side(int id,String name, String description)
    {
        super(id, name, description);
        this.allObjects = new ArrayList<Thing>();

    }    
    
    /**
     * Return an object of given id
     * 
     * @param id    id of the object
     * @return      Thing object or null
     */
    public Thing getThing(int id){
        for (Thing t:allObjects){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Add new Thing to the array
     * 
     * @param thing an object
     */
    protected void addThing(Thing thing){        
        this.allObjects.add(thing);
    }
    
    /**
     * Remove an object by id
     * 
     * @param id id of the object to remove
     */
    protected void remove(int id){
        for(Thing t:allObjects){
            if(t.getId() == id){
                this.allObjects.remove(t);
            }
        }
    }
    
    /**
     * Return true if has a door attached
     * 
     * @return hasDoor
     */
    protected boolean hasDoor(){
        for(Thing t:allObjects){
            if(t instanceof Door){
                return true;
            }
        }
        return false;
    }
    
    /**
     * return every object in this Side
     * 
     * @return arrayList of Thing (returns Side.allObjects)
     * 
     */
    public ArrayList<Thing> getObjects(){
        return this.allObjects;
    }
}