import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
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
        currentStage=null;
    }

    /**
     * Load the game
     * 
     */
    public void loadGame(){
        this.currentStage = LoadSave.loadZuul();
    }
    
    /**
     * Print everything on the player's view
     */
    private void printLocationInfo(){
        for(String s : currentStage.getView()){
            System.out.println(s);
        }
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        if(this.currentStage.equals(null)){
            System.out.println("OOPS, its wrong.. do not do this again");
        }else{
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
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Detective Zuul");
        System.out.println("You are Zuul. A detective in somewhereland city.");
        System.out.println("Police departament calls you when the crime commited looks like unaswered.");
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
        System.out.println("You are at the crime scene.");
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
        //Room nextRoom = null;       
        //        nextRoom = currentRoom.getExit(direction);

        //if (nextRoom == null) {
        //    System.out.println("There is no door!");
        //}
        //else {
        //    //            currentRoom = nextRoom;
        //    printLocationInfo();
        //}
    }

    /**
     * Change player's direction
     */
    private void look(Command command) 
    {
        //TODO: Fix this methos
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Look where?");
            return;
        }

        String direction = command.getSecondWord();                   
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
