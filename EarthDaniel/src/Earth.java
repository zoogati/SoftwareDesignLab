import java.util.ArrayList;
/**
 * Earth.java
 * Purpose: defines Earth on which Entities will sit (Designed as a singleton)
 *
 * @author Daniel Obeng
 * @version 1.0 3/31/2017
 */
public class Earth
{
    //constants for animal implementation purposes
    //these constants are made available here to make changing how the earth is quick and easy
    public static int width;
    public static int height;

    private static Earth instance;
    private Entity[][] grid;

    /**
     * Overwrite Earth's width and height - can only be done when earth
     * instance has never been called
     */
    public static void setEarthSize(int width, int height)
    {
        if ( instance != null ) return;

        Earth.width = width;
        Earth.height = height;
    }

    /**
     * Private constructor to enforce Singleton design
     */
    private Earth() { grid = new Entity[height][width]; }

    /**
     * Get entity at location x and y
     * @param x x-location of entity
     * @param y y-location of entity
     * @return Entity at x,y
     */
    public Entity getEntityAt(int x, int y)
    {
        return grid[x][y];
    }

    /**
     * Set location x,y on earth grid to entity
     * @param x x-location
     * @param y y-location
     * @param entity entity to place at location x,y
     */
    public void set(int x, int y, Entity entity)
    {
        grid[x][y] = entity;
    }

    /**
     * static method to get the single Earth instance
     * @return the single earth object in the program
     */
    public static Earth getInstance()
    {
        if (instance == null) instance = new Earth();
        return instance;
    }

    /**
     * Method to check if earth has no Organisms left
     * @return true if earth consists only of instances of FreeSpace
     */
    public boolean isEmpty()
    {
        for (Entity[] row : grid)
            for (Entity entity : row)
                if ( entity instanceof Organism ) return false;
      return true;
    }

    /**
     * Method to check if earth has no free spaces left
     * @return true if earth consists of no instances of FreeSpace
     */
    public boolean isFull()
    {
        for (Entity[] row : grid)
            for (Entity entity : row)
                if (entity == null) return false;
        return true;
    }

    /**
     * Method to check if earth has no Animals
     * @return true if earth consists of no Animals
     */
    public boolean animalsExtinct()
    {
        for (Entity[] row : grid)
            for (Entity entity : row)
                if (entity instanceof Animal) return false;
        return true;
    }

    /**
     * Returns a list of all free spaces available on earth
     * @return list of 2 element integer arrays
     */
    public ArrayList<int[]> getFreeLocations()
    {
        ArrayList<int[]> freeLocations = new ArrayList<>();

        for(int m = 0; m < height; m++)
            for(int n = 0; n < width; n++)
                if (getEntityAt(m, n) == null) freeLocations.add(new int[]{m, n});

        return freeLocations;
    }

    /**
     * Method to print the current state of the earth instance
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Entity[] row : grid)
        {
            for (Entity entity : row)
            {
                if (entity == null) sb.append("." + " ");
                else sb.append(entity.toString() + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
