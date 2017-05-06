import java.security.SecureRandom;

/**
 * Organism.java
 * Purpose: defines abstract Organism class which represents any living object on the earth
 *
 * @author Daniel Obeng
 * @version 1.0 3/31/2017
 */
public abstract class Organism extends Entity
{
    //constants for animal implementation purposes
    //these constants are made available here to make changing how an animal behaves quick and easy

    private static SecureRandom randomNumbers = new SecureRandom();
    protected static final int lifeExpectancy = 50 + randomNumbers.nextInt(100 - 50 + 1);

    protected int age = 0;

    /**
     * Method to check if organism has reached its life Expectancy
     * @return true if age of organism >= lifeExpectancy
     */
    public boolean shouldDie() { return age >= lifeExpectancy; }

    /**
     * Method to kill an organism, removes organisms reference in earth grid
     * and points it to a free space
     */
    public void die() { earth.set( getX(), getY(), null ); }

    /**
     * Method to activate a specific Organism. Organism will age, feed, grow etc if it can.
     */
    public abstract void activate();

    /**
     * Method to increase the age of an organism
     */
    public void increaseAge() { age++; }

    //Protected constructor for use by subclasses
    protected Organism(int x, int y) { super(x, y); }
}
