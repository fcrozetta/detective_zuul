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
    
    class Player
    {
        // instance variables - replace the example below with your own
        private int currentRoom;  
        private String direction;
    
        /**
         * Constructor for objects of class Player
         */
        public Player()
        {
            // initialise instance variables
            currentRoom=0;
            direction=null;
        }
    
        /**
         * If the player type left or right, we needd to know which side it is
         */
        private String LeftRight(String leftRight){
            String left,right;
            switch(this.getDirection()){
                case "north":
                    left="west";right="east";
                    break;
                case "south":
                    left="east";right="west";
                    break;
                case "east":
                    left="south";right="north";
                    break;
                case "west":
                    left="north";right="south";
                    break;
                default:
                    left=this.getDirection();right=this.getDirection();
                    break;
                }
            if(leftRight.toLowerCase().equals("left")){
                return left;
            }else if(leftRight.toLowerCase().equals("right")){
                return right;
            }else{
                return this.getDirection();
            }
        }
        
        /**
         * Set player direction, inside the room
         * 
         * @param direction direction where player is faced
         */
        public void setDirection(String direction){
            if(direction.toLowerCase().equals("left") || direction.toLowerCase().equals("right") ){
                direction = this.LeftRight(direction);
            }
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
        public void setCurrentRoom(int room){
            this.currentRoom = room;
        }
        
        /**
         * get player currentRoom
         */
        public int getCurrentRoom(){
            return this.currentRoom;
        }
    }
    
    /**
     * Constructor for objects of class Stage
     */
    public Stage(String description)
    {
        totalOfObjects=0;
        ArrayList<Thing> allObjects = new ArrayList<Thing>();
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
        Room tmpRoom = new Room(tmpId,description);
        
        Side north = new Side(this.getNewId(),"north Side");
        Side south = new Side(this.getNewId(),"south Side");
        Side east = new Side(this.getNewId(),"east Side");
        Side west = new Side(this.getNewId(),"west Side");
        Side up = new Side(this.getNewId(),"ceiling");
        Side down = new Side(this.getNewId(),"floor");
        
        tmpRoom.setSide("north", north);
        tmpRoom.setSide("south", south);
        tmpRoom.setSide("east", east);
        tmpRoom.setSide("west", west);
        tmpRoom.setSide("up", up);
        tmpRoom.setSide("down", down);
        
        
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
            Door newDoor = new Door(this.getNewId(),"common Door");
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
    public int createThing(int room, String direction, String description,String type){
        Room tmpRoom = (Room)this.getThing(room);
        Side tmpSide = tmpRoom.getSide(direction);
        Thing tmpThing = null;
        int tmpId=this.getNewId();
        switch (type.toLowerCase()){            
            case "item":
                tmpThing = new Thing(tmpId,description);
                break;
            case "furniture":
                tmpThing = new Furniture(tmpId,description);
                break;
        }
        this.allObjects.add(tmpThing);
        tmpSide.addThing(tmpThing);
        return tmpId;
    }
            
    /**
     * This method should return an hashMap of the objects player can see
     * <name_of_object,type_of_object>
     */
    public ArrayList<String> getView(){
        //TODO: Fix this method, create javadoc
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );
        ArrayList<String> tmpReturn = new ArrayList<>();
        for( Thing t: tmpSide.getObjects() ){
            tmpReturn.add(t.getDescription());
        }
        return tmpReturn;
    }
    
}    