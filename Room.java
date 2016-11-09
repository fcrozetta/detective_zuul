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
    private Side north, south, east, west, up, down;
    
    /**
     * Create a room described "description". 
     * Initially, it has no sides. "description" is something like "It's a Dark room".
     * The room's description should be an easy way to know what the room is like, looking from outside,and to help to design the graphic version
     * @param description The room's description.
     */
    public Room(int id, String description) 
    {
        
        super(id,description);
        sides = new HashMap<String,Side>();
        
        Side north = new Side(1,"north");
        Side south = new Side(2,"south");
        Side east = new Side(3,"east");
        Side west = new Side(4,"west");
        Side up = new Side(5,"ceiling");
        Side down = new Side(6,"floor");        
        
        this.sides.put("north",north);
        this.sides.put("south",south); 
        this.sides.put("east",east);
        this.sides.put("west",west);
        this.sides.put("up",up);
        this.sides.put("down",down);
    }              
    
    
    /**
     * Return the Side wall of the given direction
     * 
     * @param direction String with the direction
     */
    public Side getSide(String direction){        
        return sides.get(direction);
    }
    
    

}
