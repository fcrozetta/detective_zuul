
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
        /*
         * Case:
         * The murder gets inside the home breaking the glass on the front door, 
         * He hears the bathtub sound upstairs,
         * goes to the kitchen and grab a knife.
         * He goes upstairs, goes to her bedroom, she tries to run but he cuts her leg.
         * she locks herself on the bathroom inside her bedroom, and bleeds out and fall inside the tub
         * The robber takes her jewlery and money on her purse, then  goes downstairs and hides the knife under the stairs,
         * then he goes away using the front door
         * 
         */
        
        Stage stage = new Stage("there was a murder in this house. you need to figure out what happened");
        //creating rooms
        int outside = stage.createRoom("Outside","It's the outside of the house. It's a big house.");
        stage.setRoomSideDescription(outside, "north", "The entrance of the house.");
        stage.createItem(outside, "north", "test", "This is a test Item");
        stage.setRoomSideDescription(outside, "east", "There is a bush separating this house from the neighbour.");
        stage.setRoomSideDescription(outside,"south", "There is a tree in front of the house across the street");
        stage.setRoomSideDescription(outside, "west", "I can see bushes separating this house from the neighbour");
        stage.setRoomSideDescription(outside, "up", "It's a sunny day.");
        stage.setRoomSideDescription(outside, "down", "The pavement...");
        
        int livingRoom = stage.createRoom("Living Room","The first room of the house.");
        stage.setRoomSideDescription(livingRoom, "north", "There is a table in front of me");
        stage.createStaticItem(livingRoom, "north", "table", "it's a Huge wooden table.\nThere is a plate on the table... Better not touch it");
        
        stage.setRoomSideDescription(livingRoom, "east", "There is a clock over there!");
        stage.createStaticItem(livingRoom, "east", "clock", "its a beautifull ancient pendulum clock");
        
        stage.setRoomSideDescription(livingRoom, "south", "I can see a coat hanging");
        stage.createStaticItem(livingRoom, "south", "coat", "It's a women's coat.");
        
        stage.setRoomSideDescription(livingRoom, "west", "There is a picture on the wall");
        stage.createStaticItem(livingRoom, "west", "picture", "I can see a blonde girl and a brunette.College friends, maybe?");
        
        stage.setRoomSideDescription(livingRoom, "up", "I can see a chandelier");
        stage.createStaticItem(livingRoom, "up", "chandelier", "It's a nice chandelier... it looks expensive");
        
        stage.setRoomSideDescription(livingRoom, "down", "There is glass on the floor");
        stage.createStaticItem(livingRoom, "down", "glass","Broken glass... probably from the broken window");

        int kitchen = stage.createRoom("Kitchen","You can see a stove.\nYou can sense the smell of burning on the stove");
        stage.createStaticItem(kitchen, "west", "sink", "Empty, Clean.... nothing like my flat");
        stage.createStaticItem(kitchen, "north", "refrigerator", "mmmmm not hungry enough to open a refrigerator of the crime scene");
        stage.createStaticItem(kitchen, "north", "microwave", "Looks like a brand new microwave");
        int stove = stage.createFurniture(kitchen, "south", "stove", "The stove is cold, but i can sense the smell of burning... ", false, false);
        int bedroomKey = stage.createFurnitureItem(stove, "gold_key", "Looks like someone tried to melt this key... i wonder why");
        //HERE
        int bathroom = stage.createRoom("Bathroom","You can see that nothing was touched here.\nlooks like nothing happened here");
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
        
        return stage;
    }
    
}
