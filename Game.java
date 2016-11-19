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
        System.out.println("\t[ " + currentStage.getPlayerCurrentRoomName()+" ]\t");
        System.out.println(currentStage.getPlayerCurrentRoomDescription());
        System.out.println("You are looking "+currentStage.getPlayerDirection());
        System.out.println(currentStage.getPlayerViewSideDescription());        
        System.out.println("Objects:");
        for(String s : currentStage.getPlayerViewObjects()){
            System.out.print(s);
            System.out.print("\t");
        }
        System.out.println("");
        
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
        System.out.println("Police departament calls you when the crime commited is unaswered.");
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
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("examine")){
            examine(command);
        }
        else if(commandWord.equals("pick")){
            pick(command);
        }
        else if(commandWord.equals("bag")){
            bag();
        }
        else if (commandWord.equals("interact")){
            interact(command);
        }
        else if (commandWord.equals("loot")){
            loot(command);
        }
        else if (commandWord.equals("use")){
            use(command);
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
     * Try to go trhough the door. If there is open, enter
     * the new room, stay in the same room.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go which door?");
            return;
        }

        String door = command.getSecondWord();
        currentStage.playerGoRoom(door);
        this.printLocationInfo();
    }

    /**
     * Change where player is looking
     */
    private void look(Command command) 
    {        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            printLocationInfo();
            return;
        }
        String direction = command.getSecondWord();        
        currentStage.setPlayerDirection(direction);
        this.printLocationInfo();
    }
    
    /**
     * Examine objects, returning the description of the Object
     * 
     * @param name of the Object
     * 
     * @return the description of this thing
     */
    private void examine(Command command){
        if(!command.hasSecondWord()){
            System.out.println("examine what?");
            return;
        }
        String thing = command.getSecondWord();
        if ( currentStage.getPlayerViewObjects().contains(thing)){
            System.out.println("\t[ " + thing + " ]");
            System.out.println(currentStage.getPlayerViewThingDescription(thing));
        }        
    }
    
    /**
     * Pick up an Item
     * 
     * @param name of the Thing
     */
    private void pick(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Pick up what?");
            return;
        }
        
        String thing = command.getSecondWord();
        if ( currentStage.getPlayerViewObjects().contains(thing)){
            currentStage.playerPickItem(thing);
        }
        this.printLocationInfo();
    }
    
    /**
     * Print Player's bag
     * 
     */
    private void bag(){
        System.out.println("\t [ Bag ]\t");
        for (String s: currentStage.showPlayerBag()){
            System.out.print(s);
            System.out.print("\t");
        }
        System.out.println("");
    }
    
    /**
     * tries to interact with object
     * 
     */
    private void interact(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Interact with what?");
            return;
        }
        
        String thing = command.getSecondWord();
        
        if (!currentStage.playerInteract(thing)){
            System.out.println("not succeed to interact with "+ thing);
        }
    }
    
    /**
     * Loot a furniture, picking up everything inside
     * 
     */
    private void loot(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Loot what?");
            return;
        }
        String furniture = command.getSecondWord();
        currentStage.playerGetFurnitureItems(furniture);
    }
    
    /**
     * Use bag item into other objectin visible(not in bag)
     */
    private void use(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Use what?");
            return;
        }
        if(!command.hasThirdWord()){
            System.out.println("Use "+command.getSecondWord()+" where?");
            return;
        }
        String item = command.getSecondWord();
        String object = command.getThirdWord();
        if(currentStage.playerUseItem(item,object)){
            System.out.println("It worked!");
        }else{
            System.out.println("Well... that's not how i should use it");
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
