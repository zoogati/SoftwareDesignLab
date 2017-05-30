import java.io.Serializable;

/**
 * Entity.java
 * Purpose: defines abstract Entity class which represents any object on the earth
 *
 * @author Daniel Obeng & Socratis Katehis
 * @version 1.0 4/28/2017
 */
public abstract class Entity implements Serializable
{
    int locationX;
    int locationY;

    /**
     * Protected Constructor of Entity for subclasses
     * @param pair the (x, y) pair of coordinates
     */
    Entity(Pair<Integer,Integer> pair)
    {
        locationX = pair.getXCoord();
        locationY = pair.getYCoord();
    }

    /**
     * Returns x-location of entity
     * @return the x-location of entity
     */
    public int getX() { return locationX; }

    /**
     * Returns y-location of entity
     * @return the y-location of entity
     */
    public int getY() { return locationY; }
}
