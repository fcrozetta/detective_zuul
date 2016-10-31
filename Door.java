
/**
 * Write a description of class Door here.
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public class Door extends Hinge
{
    // this is the id of the room you go when pass trhough the door    
    private Room otherRoom;

    /**
     * Constructor for objects of class Door
     */
    public Door(int id, String description)
    {
        super(id,description);
        this.otherRoom = null;
    }
    
    /**
     * Set the other Room
     * 
     * @param room The room you'll when you pass this object
     */
    public void setOtherRoom(Room room){
        this.otherRoom = room;
    }
    
    /**
     * returns the next room, or null
     * 
     * @return otherRoom
     */
    public Room getOtherRoom(){
        return this.otherRoom;
    }
    
}
