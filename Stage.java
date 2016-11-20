import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

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
    private ArrayList<String> validDirections;
    private HashMap<String,Item> combinations;

    /**
     * Constructor for objects of class Stage
     */
    public Stage(String description)
    {
        this.totalOfObjects=0;
        this.allObjects = new ArrayList<Thing>();
        this.player = new Player();
        this.combinations = new HashMap<String,Item>();
        this.validDirections=new ArrayList<String>();
        this.validDirections.add("north");
        this.validDirections.add("south");
        this.validDirections.add("east");
        this.validDirections.add("west");
        this.validDirections.add("up");
        this.validDirections.add("down");
        this.validDirections.add("left");
        this.validDirections.add("right");
        this.validDirections.add("back");
    }

    /**
     * Add a new combination
     */
    public void setCombination(int id1,int id2, int returnItemId){
        String str = id1+"-"+id2;
        this.combinations.put(str, (Item)this.getThing(returnItemId));        
    }
    
    /**
     * Result of combination of Items
     * 
     * @param Item item1
     * @param Item item2
     * 
     * @return Item result
     */
    public Item getCombination(Item item1, Item item2){
        Item tmpItem = null;
        tmpItem = this.combinations.get(item1.getId()+"-"+item2.getId());
        if(tmpItem == null){
            tmpItem = this.combinations.get(item2.getId()+"-"+item1.getId());
        }
        return tmpItem;
    }    
    
    /**
     * Set player initial room
     */
    public void setPLayerInitialRoom(int id){
        this.player.setCurrentRoom(id);
    }
    
    /**
     * Return the opposite direction
     * 
     * @param direction you want the opposite
     * 
     * @return The opposite direction
     */
    protected static String getOppositeDirection(String direction){
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
        for (Thing t: this.allObjects){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Creates a new Room, with all sides
     * 
     * @param String name of the Room
     * @param String Description of the Room
     * 
     * @retun id of the Room created
     * 
     */
    public int createRoom(String name, String description){
        int tmpId = this.getNewId();
        
        Room tmpRoom = new Room(tmpId, name , description);
        
        Side north = new Side(this.getNewId(), "side_north", "north Side");
        Side south = new Side(this.getNewId(), "side_south", "south Side");
        Side east = new Side(this.getNewId(), "side_east", "east Side");
        Side west = new Side(this.getNewId(), "side_west", "west Side");
        Side up = new Side(this.getNewId(), "side_up", "ceiling");
        Side down = new Side(this.getNewId(), "side_down", "floor");
        
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
     * Set de description of the Side of the room
     * 
     * @param id of the room
     * @param direction
     * @param description
     */
    public void setRoomSideDescription(int id,String direction,String description){
        Room tmpRoom = (Room)this.getThing(id);
        tmpRoom.setSideDescription(direction, description);
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
        int tmpId = this.getNewId();
        if( !currentRoom.getSide(direction).hasDoor() && !newRoom.getSide(getOppositeDirection(direction)).hasDoor() ){            
            allObjects.add(newRoom);
            Door newDoor = new Door(tmpId,"door","I'ts a common Door");
            this.allObjects.add(newDoor);
            newDoor.linkRooms(currentRoom,newRoom);
            currentRoom.getSide(direction).addThing(newDoor);
            newRoom.getSide(getOppositeDirection(direction)).addThing(newDoor);
        }
    }
    
    /**
     * Attach a room to another Room, creating a door to link those two.
     * The currentRoom will create on given direction, and newRoom will create on opposite direction, so the doors are "connected"
     * 
     * @param origin id from origin room
     * @param direction which side should be used to create a door
     * @param destination The id of the room you will reach.
     * @param name of the door
     * @param description of the Door
     */
    public void attachRoom(int origin,String direction,int destination,String name, String description){
        Room currentRoom = (Room)this.getThing(origin);
        Room newRoom = (Room)this.getThing(destination);
        int tmpId = this.getNewId();
        if( !currentRoom.getSide(direction).hasDoor() && !newRoom.getSide(getOppositeDirection(direction)).hasDoor() ){            
            allObjects.add(newRoom);
            Door newDoor = new Door(tmpId,name,description);
            this.allObjects.add(newDoor);
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
    public int createItem(int room, String direction, String name, String description){
        Room tmpRoom = (Room)this.getThing(room);
        Side tmpSide = tmpRoom.getSide(direction);
        Thing tmpThing = null;
        int tmpId=this.getNewId();
        tmpThing = new Item(tmpId,name,description);
        this.allObjects.add(tmpThing);
        tmpSide.addThing(tmpThing);
        return tmpId;
    }
    
    /**
     * create a no pickable Item, based on id and direction of the room.
     * 
     * @param id id of the room
     * @param direction direction of the room
     * @param description description of the object
     * 
     * @return id id of the item created
     */
    public int createStaticItem(int room, String direction, String name, String description){
        Room tmpRoom = (Room)this.getThing(room);
        Side tmpSide = tmpRoom.getSide(direction);
        Thing tmpThing = null;
        int tmpId=this.getNewId();
        tmpThing = new Thing(tmpId,name,description);
        this.allObjects.add(tmpThing);
        tmpSide.addThing(tmpThing);
        return tmpId;
    }
    
    /**
     * create a furniture, based on id and direction of the room
     * 
     * @param id of the room
     * @param direction
     * @param String name
     * @param String description
     * @param boolean isOpen
     * @param boolean isLocked
     * 
     * @return the id of the furniture
     */
    public int createFurniture(int room, String direction, String name, String description,boolean isOpen,boolean isLocked,int keyId){
        Room tmpRoom = (Room)this.getThing(room);
        Side tmpSide = tmpRoom.getSide(direction);
        Furniture tmpThing = null;
        int tmpId=this.getNewId();
        tmpThing = new Furniture(tmpId,name,description);        
        tmpThing.setIsLocked(isLocked);
        tmpThing.setIsOpen(isOpen);
        tmpThing.setKeyId(keyId);
        this.allObjects.add(tmpThing);
        tmpSide.addThing(tmpThing);
        return tmpId;
    }
    
    /**
     * create Item inside Furniture
     * 
     * @param int furniture
     * @param String name
     * @string description
     */
    public int createFurnitureItem(int id, String name, String description){
        Thing t = this.getThing(id);
        int tmpId=this.getNewId();
        if(t instanceof Furniture){
            Furniture f = (Furniture)t;            
            Item tmpThing = new Item(tmpId,name,description);
            this.allObjects.add(tmpThing);
            f.addItem(tmpThing);
        }
        return tmpId;
    }       
                
    /**
     * Return a list of Things in the side
     * 
     * @return The list of Things in player's current view
     */
    public ArrayList<String> getPlayerViewObjects(){
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
     * Return the name of the Side which player is facing
     * 
     * @return String with the Side description
     */
    public String getPlayerViewSideName(){
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );
        return tmpSide.getName();
    }
    
    /**
     * Return the description of the Side which player is facing
     * 
     * @return String with the Side description
     */
    public String getPlayerViewSideDescription(){
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );
        return tmpSide.getDescription();
    }
    
    /**
     * Return Player Direction
     * 
     * @return the direction of the player
     */
    public String getPlayerDirection(){
        return this.player.getDirection();
    }
    
    /**
     * Change player direction
     * 
     * @param direction direction which player should face, left, or right
     * 
     */
    public void setPlayerDirection(String direction){
       this.player.setDirection(this.validDirections.contains(direction)?direction:this.player.getDirection());
    }
    
    /**
     * try to pass a door
     * 
     */
    public void playerGoRoom(String name){
        Thing thing = this.getPlayerViewThing(name);
        if(thing instanceof Door){
            Door tmpDoor = (Door) thing;
            if(!tmpDoor.getIsOpen()){
                return;
            }
            Room nextRoom = (Room)tmpDoor.getOtherRoom((Room)this.getThing(this.player.getCurrentRoom()));                
            this.setPlayerRoom(nextRoom.getId());
        }
    }
    
    
    /**
     * Set player Room (same Direction)
     * 
     * @param id of the door
     * 
     */
    private void setPlayerRoom(int id){
           this.player.setCurrentRoom(id);
    }
        
    
    /**
     * Return The thing in player's view
     * 
     * @param name is the name of the Thing you want
     * 
     * @return the Thing
     */
    protected Thing getPlayerViewThing(String name){
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );
        if (this.getPlayerViewObjects().contains(name)){
             for (Thing t: tmpSide.getObjects()){
                if( t.getName().equals(name)){
                    return t;
                }
            }
        }
        return null;
    }
    
    /**
     * Remove The thing in player's view
     * 
     * @param name is the name of the Thing you want
     */
    protected void removePlayerViewThing(String name){
        Room tmpRoom = (Room) this.getThing(this.player.getCurrentRoom() );
        Side tmpSide = tmpRoom.getSide( player.getDirection() );        
        Iterator<Thing> iter = tmpSide.getObjects().iterator();
        while (iter.hasNext()) {
            Thing t = iter.next();       
            if (t.getName().equals(name)){
                iter.remove();
            }
        }

        /*for (Thing t: tmpSide.getObjects()){
            if( t.getName().equals(name)){                    
                tmpSide.removeItem(t.getId());
            }
        }*/
    }
    
    /**
     * return description of thing in player's view
     * 
     * @param name of the Thing
     * 
     * @return String description
     */
    public String getPlayerViewThingDescription(String name){
        Thing tmpThing = this.getPlayerViewThing(name);        
        if(tmpThing == null){
            for(Item i: this.player.getObjects()){
                if (i.getName().equals(name)){
                    return i.getDescription();
                }
            }
            return "";
        }
        return tmpThing.getDescription();
    }
    
    /**
     * return the description of the Room
     * 
     * @return String with currentRoomDescription
     */
    public String getPlayerCurrentRoomDescription(){
        Room tmpRoom = (Room)this.getThing(this.player.getCurrentRoom());
        return tmpRoom.getDescription();
    }
    
    /**
     * Return the name of the Room
     * 
     * @return String with the name of the Room
     */
    public String getPlayerCurrentRoomName(){
        Room tmpRoom = (Room)this.getThing(this.player.getCurrentRoom());
        return tmpRoom.getName();
    }
    
    /**
     * Player pick ups Item
     * 
     * @param name of the Item
     */
    public void playerPickItem(String name){        
        Item item = null;
        for(Thing t: this.allObjects){
            if(t.getName().equals(name)){
                item=(Item)t;
            }
        }
        if(item instanceof Item){
            this.player.pickItem((Item)item);
            this.removePlayerViewThing(name);
        }
    }
    
    /**
     * Shows the player's bag
     * 
     * @return String with all objects name
     */
        public ArrayList<String> showPlayerBag(){
        ArrayList<String> tmp = new ArrayList<String>();
        for (Item i: this.player.getObjects()){
            tmp.add(i.getName());
        }
        return tmp;
    }
    
    /**
     * Interact with interactable object
     * 
     * @param name of the thing
     */
    public boolean playerInteract(String name){
        Thing item = this.getPlayerViewThing(name);
        if(item instanceof Interactive){
            Interactive tmp = (Interactive) item;
            return tmp.action();
        }
        return false;
    }
    
    /**
     * Retrieves every object of the furniture into the player's bag
     * 
     * @param int id of the furniture
     */
    public void playerGetFurnitureItems(String name){
        Thing t = this.getPlayerViewThing(name);        
        if(t instanceof Furniture){
            Furniture f = (Furniture)t;
            for(Item i: f.getDeleteObjects()){                
                this.player.pickItem(i);
            }
        }
    }
    
    /**
     * Player uses an Item in some Hinge object
     */
    public boolean playerUseItem(String bag_item, String view_thing){
        Item item = null;
        Thing thing = null;
        for(Item i : this.player.getObjects()){
            if(i.getName().equals(bag_item)){
                item=i;
            }
        }
        thing = this.getPlayerViewThing(view_thing);
        if(item !=null && thing!=null){
            if(thing instanceof Hinge){
                Hinge h = (Hinge)thing;
                if(h.unlock(item.getId())){
                    this.player.removeItem(item.getId());
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Player combine items
     * 
     * @param string name of the 1st item
     * @param string name of the 2nd item
     * 
     * @return true if succeed
     */
    public boolean playerCombineItem(String bag_item1, String bag_item2){
        Item item1 = null;
        Item item2 = null;
        for(Item i : this.player.getObjects()){
            if(i.getName().equals(bag_item1)){
                item1=i;
            }
        }
        
        for(Item i : this.player.getObjects()){
            if(i.getName().equals(bag_item2)){
                item2=i;
            }
        }
        
        if ( (item1 != null) && (item2 != null)){
            Item tmpItem = this.getCombination(item1, item2);
            if(tmpItem !=null){
                this.player.removeItem(item1.getId());
                this.player.removeItem(item2.getId());
                this.allObjects.add(new Item(tmpItem.getId(),tmpItem.getName(),tmpItem.getDescription()));
                this.player.pickItem((Item)this.getThing(tmpItem.getId()));
                return true;
            }
        }
        return false;
    }

}    