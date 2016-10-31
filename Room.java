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
    public Room(int id, String description) 
    {
        super(id,description);
        sides = new HashMap<String,Side>();   
        this.setEmptyRoom();
    }
    
    /**
     * Defines this room as empty: no sides at all.
     * The player will not be able do reach this room
     */   
    public void setEmptyRoom(){
       this.sides.put("north",null); 
       this.sides.put("south",null); 
       this.sides.put("east",null); 
       this.sides.put("west",null); 
       this.sides.put("up",null); 
       this.sides.put("down",null); 
    }
    
    /**
     * Add a room, to a given side
     */
    public void add(String direction,Side side){
        this.sides.put(direction,side);
    }
    
    /**
     * Set the direction's side to null
     */
    public void remove(String direction){
        this.sides.put(direction,null);
    }
    
    
    /**
     * Return the Side wall of the given direction
     * 
     * @param direction String with the direction
     */
    public Side look(String direction){
        return sides.get(direction);
    }
    
    

}
