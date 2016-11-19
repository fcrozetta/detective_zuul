
/**
 * The Hinge class contains fields and methods about objects that can be open and closed, and lock/unlocked
 * 
 * @author Fernando H. Crozetta 
 * @version 0.0.1
 */
public abstract class Hinge extends Interactive
{
    private boolean isLocked;
    private boolean isOpen;
    private int keyId;

    /**
     * Constructor for objects of class Hinge
     */
    public Hinge(int id, String name, String description)
    {
        super(id,name,description);
        this.isLocked = false;
        this.isOpen = true;
        this.keyId = 0;
    }
    
    /**
     * Set isLocked
     * 
     * @param boolean isLocked
     */
    protected void setIsLocked(boolean isLocked){
        this.isLocked = isLocked;
    }
    
    /**
     * Set isOpen
     * 
     * @param boolean isOpen
     */
    protected void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
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
     * Return the state of the lock
     * 
     * @return true if it's locked
     */
    public boolean getIsLocked(){
        return this.isLocked;
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
    
    /**
     * returns true if its open
     */
    public boolean getIsOpen(){
        return this.isOpen;
    }
    
    /**
     * Hinge description includes open/close and locked/unlocked
     * 
     * @return description
     */
    protected String getDescription(){
        String tmp;
        if(this.isLocked){
            tmp = "Locked";
        }else{
            tmp=this.isOpen?"open":"closed";
        }
        return super.getDescription() + "\nIt's "+tmp ;
    }
    
    /**
     * Action to perform when something interacts with it
     */
    protected boolean action(){
        return this.open();
    }
}
