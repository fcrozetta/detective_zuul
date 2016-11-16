import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * Stage Contains everything the level needs to work
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Stage
{
    private ArrayList<Thing> allObjects;
    private Player player;
    private int totalOfObjects;   
    

    
    /**
     * Constructor for objects of class Stage
     */
    public Stage(String description)
    {
        totalOfObjects=0;
        this.allObjects = new ArrayList<Thing>();
        this.player = new Player();
    }

    /**
     * Return the opposite direction
     * 
     * @param direction you want the opposite
     * 
     * @return The opposite direction
     */
    protected String getOppositeDirection(String direction){
        switch (direction){
            case "north":return "south";
            case "south":return "north";
            case "east": return "west";
            case "west": return "east";
            case "up"  : return "down";
            case "down": return "up";
        }
        return null;
    }
    
    /**
     * returns a newId, and increments the total of objects in the stage
     */
    private int getNewId(){
        totalOfObjects++;
        return totalOfObjects;
    }    

    /**
     * Return that Thing you want
     * 
     * @param id Id of the Thing object
     * @return Thing object
     */
    protected Thing getThing(int id){
        for (Thing t: allObjects){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }      
    
    public int createRoom(String description){
        int tmpId = this.getNewId();
        
        Room tmpRoom = new Room(tmpId, "Room_"+tmpId , description);
        
        Side north = new Side(this.getNewId(), "wall", "north Side");
        Side south = new Side(this.getNewId(), "wall", "south Side");
        Side east = new Side(this.getNewId(), "wall", "east Side");
        Side west = new Side(this.getNewId(), "wall", "west Side");
        Side up = new Side(this.getNewId(), "ceiling", "ceiling");
        Side down = new Side(this.getNewId(), "floor", "floor");
        
        tmpRoom.setSide("north", north);
        tmpRoom.setSide("south", south);
        tmpRoom.setSide("east", east);
        tmpRoom.setSide("west", west);
        tmpRoom.setSide("up", up);
        tmpRoom.setSide("down", down);
        
        this.allObjects.add(north);
        this.allObjects.add(south);
        this.allObjects.add(east);
        this.allObjects.add(west);
        this.allObjects.add(tmpRoom);
        
        return tmpId;
    }
    
    /**
     * Attach a room to another Room, creating a door to link those two.
     * The currentRoom will create on given direction, and newRoom will create on opposite direction, so the doors are "connected"
     * 
     * @param origin id from origin room
     * @param direction which side should be used to create a door
     * @param destination The id of the room you will reach.
     */
    public void attachRoom(int origin,String direction,int destination){
        Room currentRoom = (Room)this.getThing(origin);
        Room newRoom = (Room)this.getThing(destination);
        if( !currentRoom.getSide(direction).hasDoor() && !newRoom.getSide(getOppositeDirection(direction)).hasDoor() ){            
            allObjects.add(newRoom);
            Door newDoor = new Door(this.getNewId(),"Door","common Door");
            newDoor.linkRooms(currentRoom,newRoom);
            currentRoom.getSide(direction).addThing(newDoor);
            newRoom.getSide(getOppositeDirection(direction)).addThing(newDoor);
        }
    }
    
    /**
     * create a new Item, based on id and direction of the room.
     * 
     * @param id id of the room
     * @param direction direction of the room
     * @param description description of the object
     * 
     * @return id id of the item created
     */
    public int createThing(int room, String direction, String name, String description,String type){
        Room tmpRoom = (Room)this.getThing(room);
        Side tmpSide = tmpRoom.getSide(direction);
        Thing tmpThing = null;
        int tmpId=this.getNewId();
        switch (type.toLowerCase()){            
            case "item":
                tmpThing = new Thing(tmpId, name, description);
                break;
            case "furniture":
                tmpThing = new Furniture(tmpId, name, description);
                break;
        }
        this.allObjects.add(tmpThing);
        tmpSide.addThing(tmpThing);
        return tmpId;
    }
            
    /**
     * Return a list of things based on pl
     * <name_of_object,type_of_object>
     */
    public ArrayList<String> getView(){
        //TODO: Fix this method, create javadoc
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );
        ArrayList<String> tmpReturn = new ArrayList<>();
        for( Thing t: tmpSide.getObjects() ){
            tmpReturn.add(t.getName());
        }
        return tmpReturn;
    }
    
    /**
     * 
     */
}    