
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
        int outside = stage.createRoom("Outside of the house. Not a big house.\nThere is a broken glass on the front door");
        int livingRoom = stage.createRoom("The living room.\nYou can see a chandelier up above, and a large table in front of you.\nthere is one plate ont the table");
        int kitchen = stage.createRoom("The kitchen.\nYou can see a stove.\nYou can sense the smell of burning on the stove");
        int bathroom = stage.createRoom("The bathroom.\nYou can see that nothing was touched here.\nlooks like nothing happened here");
        int stairs = stage.createRoom("The stairs.\nthe stairs are made of wood.\nYou can see a loose step");
        int bedroom = stage.createRoom("The bedroom.\nYou can see a trail of blood leading to the bedroom's bathroom.\nThere is a purse open up on the bed.\nThe drawers are open and some objects are missing.");
        int bathroom2 = stage.createRoom("The bathroom.\nThis bathroom is covered in blood and you can see a corpse inside the tub.\nshe has a deep cut on her leg");
        
        stage.attachRoom(outside, "north", livingRoom);
        
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
