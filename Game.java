import java.util.HashMap;
/**
 *  This class is the main class of the "Detective Zuul" application. 
 *  "Detective Zuul" is a game based on "World of Zuul", created by Michael Kölling and David J. Barnes.
 *  Users can explore and interact with the scenery
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 */

public class Game 
{
    private Parser parser;
    private Stage currentStage;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        currentStage = createStage();
    }

    private void printLocationInfo(){
        System.out.println("no description.. fix this");
    }
    
    /**
     * Create all the rooms,furniture,items, and link their exits together.
     */
    private Stage createStage()
    {   
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
        
        Stage stage = new Stage(1,"there was a murder in this house. you need to figure out what happened");
        //creating rooms
        Room outside = stage.createRoom("Outside of the house. Not a big house.\nThere is a broken glass on the front door");
        Room livingRoom = stage.createRoom("The living room.\nYou can see a chandelier up above, and a large table in front of you.\nthere is one plate ont the table");
        Room kitchen = stage.createRoom("The kitchen.\nYou can see a stove.\nYou can sense the smell of burning on the stove");
        Room bathroom = stage.createRoom("The bathroom.\nYou can see that nothing was touched here.\nlooks like nothing happened here");
        Room stairs = stage.createRoom("The stairs.\nthe stairs are made of wood.\nYou can see a loose step");
        Room bedroom = stage.createRoom("The bedroom.\nYou can see a trail of blood leading to the bedroom's bathroom.\nThere is a purse open up on the bed.\nThe drawers are open and some objects are missing.");
        Room bathroom2 = stage.createRoom("The bathroom.\nThis bathroom is covered in blood and you can see a corpse inside the tub.\nshe has a deep cut on her leg");
        
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
        
        Player zuul = stage.getPlayer();
        zuul.setCurrentRoom(outside);
        zuul.setDirection("north");
        
        return stage;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Game Over");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("This is not world of zuul");
        System.out.println("You are the detective here. please find out what happened");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();

    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        // Print help
        if (commandWord.equals("help")) {
            printHelp();
        }
        // Go somewhere
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        // Exit the game
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }        
        return wantToQuit;
    }

    //TODO: change everything from this point
    
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are a detective at a crime scene.");
        System.out.println("Find out how the murder was done (or not. this is not functional yet)");
        System.out.println();        
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;       
        //        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Change player's direction
     */
    private void look(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Look where?");
            return;
        }

        String direction = command.getSecondWord();

        currentStage.getPlayer().setDirection(direction);
        
        if (currentstage == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    
}
