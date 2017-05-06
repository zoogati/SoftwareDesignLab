/**
 * Entity.java
 * Purpose: defines abstract Entity class which represents any object on the earth
 *
 * @author Daniel Obeng
 * @version 1.0 3/31/2017
 */
public abstract class Entity
{
    protected static Earth earth = Earth.getInstance();

    protected int locationX;
    protected int locationY;

    //protected constructor for use by subclasses
    protected Entity(int x, int y)
    {
        locationX = x;
        locationY = y;
    }

    //Getters for Entities Location X and Y
    public int getX() { return locationX; }
    public int getY() { return locationY; }
}
