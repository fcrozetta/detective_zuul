import java.util.ArrayList;
/**
 * Furniture is a "container" for items. This should be used to place Items or other Furnitures
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Furniture extends Hinge
{
    private ArrayList<Item> allObjects;
    
    /**
     * Constructor for objects of class Furniture
     */
    public Furniture(int id, String name, String description)
    {
        super(id, name, description);
        this.allObjects = new ArrayList<Item>();
    }
    
    /**
     * Add object
     * 
     * @param Item - item
     */
    public void addItem(Item item){
        this.allObjects.add(item);
    }
    
    /**
     * removes object
     */
    public void remove(Item item){
        for(Item i:allObjects){
            if(i.equals(item)){
                this.allObjects.remove(i);
            }
        }
    }
    
    /**
     * return items
     * 
     * @return ArrayList all items
     */
    public ArrayList<Item> getObjects(){
        return this.allObjects;
    }
    
    /**
     * return every object then remove from this list
     * 
     * @return ArrayList all items
     *     
     */
    public ArrayList<Item> getDeleteObjects(){
        ArrayList<Item> tmp = new ArrayList<Item>();
        for(Item i: this.allObjects){
            tmp.add(new Item(i.getId(), i.getName(), i.getDescription()));
        }
        this.allObjects.clear();
        return tmp;
    }
    
    /**
     * @return description
     */
    public String getDescription(){
        String tmp="";
        if(this.getIsOpen()){            
            if(!this.getObjects().isEmpty()){            
                tmp="\nI can see somthing in there...";
            }
        }
        return super.getDescription() +tmp;
    }
}
