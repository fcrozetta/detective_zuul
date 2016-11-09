import java.util.ArrayList;
/**
 * This sets common fields between players and NPCs
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Character extends Thing
{
    // instance variables - replace the example below with your own
    private ArrayList<Thing> inventory;

    /**
     * Constructor for objects of class Character
     */
    public Character(int id, String description)
    {
        // initialise instance variables
        super(id,description);
        ArrayList<Thing> inventory = new ArrayList<Thing>();
    }
}
