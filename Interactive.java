
/**
 * Abstract class Interactive - Methods for objects with interaction
 * 
 * @author Fernando H. Crozetta
 * @version 0.0.1
 */
abstract class Interactive extends Thing
{
    public Interactive(int id, String name, String description){
        super(id,name,description);
    }
    
    /**
     * This will perform something when player interact with this
     * 
     * @return true if action is performed
     */
    abstract boolean action();
}
