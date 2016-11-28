
/**
 * Load/Save the game
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
public class LoadSave
{
    // instance variables - replace the example below with your own
    private Stage stage;
    
    /**
     * Constructor for objects of class LoadSave
     */
    public LoadSave()
    {
        // initialise instance variables
        this.stage=null;
    }

    /**
     * Return "Detective Zuul" Stage
     */
    static Stage loadZuul(){
        Stage stage = new Stage("there was a murder in this house. you need to figure out what happened");
        int shadow = stage.createRoom("This room is not reacheable by the player", "This room stores everything not present in the game now");
        int hammer = stage.createItem(shadow, "north", "hammer", "It's a home made hammer");
        int crowbar = stage.createItem(shadow, "north", "crowbar", "This twisted metal bar can be used as a crowbar");
        int full_mag = stage.createItem(shadow,"north", "full_magazine", "It's a small firearm's magazine. It's full of 9mm ammo");
        int p290_loaded = stage.createItem(shadow, "north", "p290_loaded", "It's a p290 gun from sig sauer. It's loaded");
        
        
        int outside = stage.createRoom("Outside","It's the outside of the house. It's a big house.");

        stage.setRoomSideDescription(outside, "north", "The entrance of the house.");        
        stage.createStaticItem(outside, "north", "window", "I can see a table from here");
        
        stage.setRoomSideDescription(outside, "east", "There is a car parked there, and the entrance of the garage.");
        stage.createStaticItem(outside, "east", "car", "It's a beautiful car... i'm not sure if i should open the door");
        
        stage.setRoomSideDescription(outside,"south", "There is a tree in front of the house across the street");
        stage.createStaticItem(outside, "south", "home", "It's the neighbour's home..");
        
        stage.setRoomSideDescription(outside, "west", "I can see bushes separating this house from the neighbour");
        int trashCan = stage.createFurniture(outside, "west", "trash_can", "It's the reccycle bin...", false, false, 0);
        int metal_scrap = stage.createFurnitureItem(outside, "metal_scrap", "It's a metal bar.. this can be usefull");
        
        stage.setRoomSideDescription(outside, "up", "It's a sunny day.");
        stage.createStaticItem(outside, "up", "sun", "Yup... it is the sun");        
        stage.setRoomSideDescription(outside, "down", "The pavement...");
        
        
        //LivingRoom
        int livingRoom = stage.createRoom("Living Room","It's a big room..");
        stage.setRoomSideDescription(livingRoom, "north", "There is a table in front of me, and a corridor");
        stage.createStaticItem(livingRoom, "north", "table", "it's a Huge wooden table.\nThere is a plate on the table... Better not touch it");
        
        stage.setRoomSideDescription(livingRoom, "east", "There is a clock over there!");
        stage.createStaticItem(livingRoom, "east", "clock", "its a beautifull ancient pendulum clock");
        
        stage.setRoomSideDescription(livingRoom, "south", "I can see a coat hanging");
        stage.createStaticItem(livingRoom, "south", "coat", "It's a women's coat.");
        stage.createStaticItem(livingRoom, "south", "window", "I can see the road, and a house on the other side of the street");

        
        stage.setRoomSideDescription(livingRoom, "west", "There is a picture on the wall");
        int picture = stage.createFurniture(livingRoom, "west", "picture", "I can see a blonde girl and a brunette.College friends, maybe?", false, false, 0);
        //TODO:Finish this
        int camera = stage.createFurnitureItem(living, "camera", "");
        stage.setRoomSideDescription(livingRoom, "up", "I can see a chandelier");
        stage.createStaticItem(livingRoom, "up", "chandelier", "It's a nice chandelier... it looks expensive"); 
        stage.setRoomSideDescription(livingRoom, "down", "There is glass on the floor");
        stage.createStaticItem(livingRoom, "down", "glass","Broken glass... probably from the broken window");
        stage.createStaticItem(livingRoom, "down", "blood", "blood splats... i wonder whose blood is it");

        // Kitchen
        int kitchen = stage.createRoom("Kitchen","You can see a stove.\nYou can sense the smell of burning on the stove");
        stage.createStaticItem(kitchen, "west", "sink", "Empty, Clean.... nothing like my flat");
        stage.createStaticItem(kitchen, "north", "refrigerator", "mmmmm not hungry enough to open a refrigerator of the crime scene");
        stage.createStaticItem(kitchen, "north", "microwave", "Looks like a brand new microwave");
        int stove = stage.createFurniture(kitchen, "south", "stove", "The stove is cold, but i can sense the smell of burning... ", false, true,stove_key);
        int bedroomKey = stage.createFurnitureItem(stove, "gold_key", "Looks like someone tried to melt this key... i wonder why");
        //HERE
        //Bathroom
        int bathroom = stage.createRoom("Bathroom","You can see that nothing was touched here.\nlooks like nothing happened here");
        stage.setRoomSideDescription(bathroom, "north", "There is a sink and a mirror over here");
        stage.createStaticItem(bathroom, "north", "sink", "it's just a sink... really");
        int mirror = stage.createFurniture(bathroom, "north", "mirror", "Look... It's me", false, false, 0);
        int toothbrush = stage.createFurnitureItem(mirror, "toothbrush", "nice toothbrush... it has nothing to do with this case");
        
        stage.setRoomSideDescription(bathroom, "east", "there is a shower over here");
        stage.createStaticItem(bathroom, "east", "shower", "a shower... i don't think it was ever used");
        
        stage.setRoomSideDescription(batroom, "south", "there is a toilet");
        stage.createStaticItem(bathroom, "south", "toilet", "it's clean...");
        
        stage.setRoomSideDescription(bathroom, "west", "there is just the door i came from");
        
        stage.setRoomSideDescription(bathroom, "up", "just the light up here");
        stage.setRoomSideDescription(bathr
        oom, "down", "looking at my own feet");
        
        
        
        int stairs = stage.createRoom("Stairs","The stairs are made of wood.\nYou can see a loose step");
        int bedroom = stage.createRoom("Bedroom","You can see a trail of blood leading to the bedroom's bathroom.\nThere is a purse open up on the bed.\nThe drawers are open and some objects are missing.");
        int bathroom2 = stage.createRoom("Bedroom's Bathroom","This bathroom is covered in blood and you can see a corpse inside the tub.\nshe has a deep cut on her leg");
        

        
        
        stage.attachRoom(outside, "north", livingRoom,"door","The door has a broken window");
        
        stage.attachRoom(livingRoom,"north",stairs);
        stage.attachRoom(livingRoom,"east",bathroom);
        stage.attachRoom(livingRoom,"west",kitchen);
        
        stage.attachRoom(stairs, "north", bedroom);
        stage.attachRoom(stairs, "south", livingRoom);
        
        stage.attachRoom(bathroom, "south", livingRoom);
        
        stage.attachRoom(kitchen,"east", livingRoom);        
        
        stage.attachRoom(bedroom, "east", bathroom2);
        stage.attachRoom(bedroom, "south", stairs);
        stage.setPLayerInitialRoom(outside);
        return stage;
    }
    
}
