import java.util.ArrayList;
/**
 * Furniture is a "container" for items. This should be used to place Items or other Furnitures
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Furniture extends Hinge
{
    private ArrayList<Thing> insideItems;
    
    /**
     * Constructor for objects of class Furniture
     */
    public Furniture(int id,String description)
    {
        super(id,description);
        ArrayList<Thing> insideItems = new ArrayList<Thing>();
    }
    
    /**
     * Add object
     * 
     * @param thing an object
     */
    public void add(Thing thing){
        this.insideItems.add(thing);
    }
    
    /**
     * removes object
     */
    public void remove(Thing thing){
        for(Thing t:insideItems){
            if(t.equals(thing)){
                insideItems.remove(t);
            }
        }
    }
}
