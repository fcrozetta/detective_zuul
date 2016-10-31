
/**
 * The Hinge class contains fields and methods about objects that can be open and closed, and lock/unlocked
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public class Hinge extends Thing
{
    private boolean isLocked;
    private boolean isOpen;
    private int keyId;

    /**
     * Constructor for objects of class Hinge
     */
    public Hinge(int id, String description)
    {
        super(id,description);
        this.isLocked = false;
        this.isOpen = false;
        this.keyId = 0;
    }
    
    /**
     * Tries to unlock the object with the id of the key
     * 
     * @param key   id of the key to unlock the furniture
     * 
     * @return  bool    true if it unlocks the door.
     */
    public boolean unlock(int key){
        if(this.isLocked && (this.keyId > 0)){
            if(this.keyId == key){
                this.isLocked = !this.isLocked;
                return true;
            }
        }
        return false;
    }
    
    /**
     * open/close the object, if it's not locked
     * 
     * @return bool true if stateChanged
     */
    public boolean open(){
        if(this.isLocked){
            return false;
        }else{
            this.isOpen = !this.isOpen;
            return true;
        }
    }    
}
