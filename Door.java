
/**
 * Write a description of class Door here.
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public class Door extends Hinge
{
    // this is the id of the room you go when pass trhough the door    
    private Room room1, room2;

    /**
     * Constructor for objects of class Door
     */
    public Door(int id, String name, String description)
    {
        super(id, name, description);
        this.room1 = null;
        this.room2 = null;
    }
    
    /**
     * LinkRooms
     * 
     * @param room1 The first room you'll link
     * @param room2 The second room you'll link
     */
    public void linkRooms(Room room1,Room room2){
        this.room1 = room1;
        this.room2 = room2;
    }
    
    /**
     * returns the next room
     * 
     * @return The room which tou are not passing
     */
    public Room getOtherRoom(Room currentRoom){
        if(room1 == currentRoom){
            return room2;
        }else{
            return room1;
        }                
    }
    
    /**
     * Door's description contain the opposite's room description if it's open
     * 
     * @return Description
     */
    public String getDescription(){
        //TODO: Change this to see the opposite room
        if(this.getIsOpen()){
            return super.getDescription()+"\nAnd you can see the other room";
        }else{
            return super.getDescription();
        }
    }
    
}
