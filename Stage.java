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
    private Room currentRoom;

    /**
     * Constructor for objects of class Stage
     */
    public Stage(int id,String description)
    {
        super(id,description);
        ArrayList<Room> rooms = new ArrayList<Room>();
    }
    
    /**
     * Add a room to the list
     * 
     * @param room room to be added
     */
    public void addRoom(Room room){
        rooms.add(room);
    }
    
    /**
     * remove the given room
     * 
     * @param room room to be removed
     */
    public void removeRoom(Room room){
        rooms.remove(room);
    }
    
}
