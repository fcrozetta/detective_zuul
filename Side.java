import java.util.ArrayList;
import java.util.HashMap;
/**
 * This class creates a wall, where the objects, and furniture will be placed.
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
    public Side(int id, String description)
    {
        super(id,description);
        ArrayList<Thing> allObjects = new ArrayList<Thing>();

    }    
    
    /**
     * Return an object of given id
     * 
     * @param id    id of the object
     * @return      Thing object or null
     */
    public Thing get(int id){
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
    public void add(Thing thing){
        this.allObjects.add(thing);
    }
    
    /**
     * Remove an object by id
     * 
     * @param id id of the object to remove
     */
    public void remove(int id){
        for(Thing t:allObjects){
            if(t.getId() == id){
                this.allObjects.remove(t);
            }
        }
    }
}
