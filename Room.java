import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * composed by 6 sides(north,south,east,west,up,down 
 * For each direction, the room stores a reference
 * to one side, or null, if the player cannot look that direction
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room extends Thing
{
    private HashMap<String,Side> sides;    
    
    /**
     * Create a room described "description". 
     * Initially, it has no sides. "description" is something like "It's a Dark room".
     * The room's description should be an easy way to know what the room is like, looking from outside,and to help to design the graphic version
     * @param description The room's description.
     */
    public Room(int id, String name, String description) 
    {
        
        super(id, name, description);
        sides = new HashMap<String,Side>();                
    }              
    
    
    /**
     * Return the Side wall of the given direction
     * 
     * @param direction String with the direction
     */
    public Side getSide(String direction){        
        return sides.get(direction);
    }
    
    /**
     * Set every side of the room
     * 
     * @param direction the direction inside the room
     * @param side  the object to be placed on that side
     */
    public void setSide(String direction, Side side){
        this.sides.put(direction,side);        
    }
        
    

}
