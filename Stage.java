import java.util.ArrayList;

/**
 * Stage Contains everything the level needs to work
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Stage extends Thing
{
    private ArrayList<Room> rooms;
    private ArrayList<Door> doors;
    private Player zuul;
    private int totalOfObjects;   
    
    /**
     * Constructor for objects of class Stage
     */
    public Stage(int id,String description)
    {
        super(id,description);
        totalOfObjects=0;
        ArrayList<Room> rooms = new ArrayList<Room>();
        ArrayList<Door> doors = new ArrayList<Door>();
        this.setPlayer();
    }

    /**
     * returns a newId, and increments the total of objects in the stage
     */
    private int getNewId(){
        totalOfObjects++;
        return totalOfObjects;
    }
    
    /**
     * Initialise Player
     */
    private void setPlayer(){
        Player zuul = new Player(this.getNewId(),"Detective Zuul");
    }
    
    /**
     * returns the player
     */
    public Player getPlayer(){
        return this.zuul;
    }
    
    public Room createRoom(String description){
        Room tmpRoom = new Room(this.getNewId(),description);
        return tmpRoom;
    }
    
    /**
     * Attach a room to another Room, creating a door to link those two.
     * The currentRoom will create on given direction, and newRoom will create on opposite direction, so the doors are "connected"
     * 
     * @param currentRoom room origin
     * @param direction which side should be used to create a door
     * @param newRoom the destination room.
     */
    public void attachRoom(Room currentRoom,String direction,Room newRoom){
        if( !currentRoom.getSide(direction).hasDoor() && !newRoom.getSide(getOppositeDirection(direction)).hasDoor() ){            
            rooms.add(newRoom);
            Door newDoor = new Door(this.getNewId(),"common Door");
            newDoor.linkRooms(currentRoom,newRoom);
            currentRoom.getSide(direction).addThing(newDoor);
            newRoom.getSide(getOppositeDirection(direction)).addThing(newDoor);
        }
    }
}