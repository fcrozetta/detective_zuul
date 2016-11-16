
/**
 * Creates a player
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class Player extends Character
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
        super();
        currentRoom=1;
        direction="north";
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