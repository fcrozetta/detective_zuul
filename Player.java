
/**
 * Create a new player.
 * In this project, it will create Zuul
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Player extends Character
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private String direction;

    /**
     * Constructor for objects of class Player
     */
    public Player(int id, String description)
    {
        // initialise instance variables
        super(id,description);
        currentRoom = null;
        direction="north";
    }

    /**
     * Set player direction, inside the room
     * 
     * @param direction direction where player is faced
     */
    public void setDirection(String direction){
        this.direction = direction;
    }
    
    /**
     * returns the direction
     */
    public String getDirection(){
        return this.direction;
    }
    
    /**
     * Set player current room
     * 
     * @param room room where zuul is
     */
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    
    /**
     * get player currentRoom
     */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }
    
    /**
     * Return the Player actual view
     * 
     */
    public Side getcurrentView(){
        return this.currentRoom.getSide(this.direction);
    }
}
