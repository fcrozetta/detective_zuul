
/**
 * Thing class contains everything all game objects should have.
 * It's called Thing, because "Object" wa already taken.
 * In theory, this should create items to be put inside the detective's inventory.
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public class Thing
{
    private int id;              // Id to knkow my objects
    private String name;
    private String description;     // Description of this thing

    /**
     * Constructor for objects of class Thing
     */
    public Thing(int id,String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the Id
     * 
     * @return     id of the object
     */
    protected int getId()
    {
        return this.id;
    }
    
    /**
     * Sets the description
     * 
     * @param description The description
     */
    protected void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Returns the Description
     * 
     * @return     description of the thing
     */
    protected String getDescription(){
        return this.description;
    }       
    
    /**
     * Return the name of the Thing
     * 
     * @return name name f the Object
     */
    protected String getName(){
        return this.name;
    }
    
    
}
