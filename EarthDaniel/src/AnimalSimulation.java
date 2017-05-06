/**
 * AnimalSimulation.java
 * Purpose: defines a game involving animals and plants on an earth
 * TODO: Rename this class - AnimalSimulation is not a good name
 *
 * @author Daniel Obeng
 * @version 1.0 3/31/2017
 */

import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.Scanner;


public class AnimalSimulation
{
    //constants for animal implementation purposes
    //these constants are made available here to make changing how an animal behaves quick and easy
    private static final int minCarnivoreIter = 2;  // iterations after which carnivore moves
    private static final int maxCarnivoreIter = 4;  // iterations after which carnivore moves
    private static final int minHerbivoreIter = 1;  // iterations after which carnivore moves
    private static final int maxHerbivoreIter = 3;  // iterations after which carnivore moves
    private static final int minPlantIter  = 3;  // min iterations after which new plant grows
    private static final int maxPlantIter  = 5;  // max iterations after which new plant grows

    private static int initPlants;       // initial number of plants on earth
    private static int initHerbivore;    // initial number of herbivores on earth
    private static int initCarnivore ;   // initial number of carnivores on earth

    private static int width;
    private static int height;
    private static int maxIterations;

    private static SecureRandom randomNumbers = new SecureRandom(); //random numbers generator

    private static final int sleepTime = 1000;   // wait time of main loop to limit speed of game

    private static Earth earth;                  // reference to earth instance

    public static void main(String[] args)
    {
        init();
        startSimulation();
    }

    /**
     * Method to create earth and initialize with herbivores, carnivores and plants
     * Precondition: initPlants + initHerbivore + initCarnivore <= earth.width * earth.height
     */
    private static void init()
    {
        Scanner scanner = new Scanner(System.in);

        //Get Earth width
        do
        {
            System.out.print("Enter a Width >= 5: ");
            width = scanner.nextInt();
        } while (width < 5);

        //Get Earth size
        do
        {
            System.out.print("Enter a Height >= 5: ");
            height = scanner.nextInt();
        } while (height < 5);

        //Get number of iterations
        System.out.print("Enter the number of iterations: ");
        maxIterations = scanner.nextInt();

        //initial number of organisms based on the width of the board
        initPlants = width + width/2;
        initHerbivore = width;
        initCarnivore = width/2;

        Earth.setEarthSize(width, height);
        earth = Earth.getInstance();

        //MARK: initialize Plants
        for(int i = 0; i < initPlants; )
        {
            int randX = randomNumbers.nextInt(Earth.height);
            int randY = randomNumbers.nextInt(Earth.width);

            if(earth.getEntityAt(randX, randY) == null)
            {
                earth.set(randX, randY, new Plant(randX, randY));
                i++;
            }
        }

        //MARK: initialize Herbivores
        for(int i = 0; i < initHerbivore; )
        {
            int randX = randomNumbers.nextInt(Earth.height);
            int randY = randomNumbers.nextInt(Earth.width);

            if(earth.getEntityAt(randX, randY) == null)
            {
                earth.set(randX, randY, new Herbivore(randX, randY));
                i++;
            }
        }

        //MARK: Initialize Carnivores
        for(int i = 0; i < initCarnivore; )
        {
            int randX = randomNumbers.nextInt(Earth.height);
            int randY = randomNumbers.nextInt(Earth.width);

            if(earth.getEntityAt(randX, randY) == null)
            {
                earth.set(randX, randY, new Carnivore(randX, randY));
                i++;
            }
        }

    }

    /**
     * Method to activate each Carnivore
     */
    private static void activateCarnivores()
    {
        for (int i = 0; i < Earth.height; i++)
        {
            for (int j = 0; j < Earth.width; j++)
            {
                Entity entity = earth.getEntityAt(i, j);
                if (entity instanceof Carnivore && !((Carnivore) entity).isActive) ((Carnivore) entity).activate();
            }
        }
    }

    /**
     * Method to activate all herbivores
     */
    private static void activateHerbivores()
    {
        for (int i = 0; i < Earth.height; i++)
        {
            for (int j = 0; j < Earth.width; j++)
            {
                Entity entity = earth.getEntityAt(i, j);
                if (entity instanceof Herbivore && !((Herbivore) entity).isActive) ((Herbivore) entity).activate();
            }
        }
    }

    /**
     * Method to randomly grow and kill plants as needed
     */
    private static void activatePlants()
    {
        for (int i = 0; i < Earth.height; i++)
        {
            for (int j = 0; j < Earth.width; j++)
            {
                Entity entity = earth.getEntityAt(i, j);
                if (entity instanceof Plant) ((Plant) entity).activate();
            }
        }

        ArrayList<int[]> freeLocations = earth.getFreeLocations();
        if ( freeLocations.isEmpty() ) return; //no plants can grow at this iteration
        int index = randomNumbers.nextInt( freeLocations.size() ); //pick a random index in the freeLocations array
        int x = freeLocations.get(index)[0];
        int y = freeLocations.get(index)[1];

        earth.set(x, y, new Plant(x, y));
    }

    /**
     * Method to start the AnimalSimulation
     */
    private static void startSimulation()
    {

        int iteration = 1;

        //iteration for organisms to increase randomness
        int plantIter = minPlantIter + randomNumbers.nextInt(maxPlantIter - minPlantIter + 1);
        int herbivoreIter = minHerbivoreIter + randomNumbers.nextInt(maxHerbivoreIter - minHerbivoreIter + 1);
        int carnivoreIter = minCarnivoreIter + randomNumbers.nextInt(maxCarnivoreIter - minCarnivoreIter + 1);

        //Game Ends where extinction of Organisms is reached
        //Alternate: Game ends when Animals are extinct
        while ( !earth.isEmpty() && iteration <= maxIterations)
        {
            System.out.println(earth + "\n");

            if (iteration % carnivoreIter == 0)
            {
                activateCarnivores();
                carnivoreIter = minCarnivoreIter + randomNumbers.nextInt(maxCarnivoreIter - minCarnivoreIter + 1);
            }
            if (iteration % herbivoreIter == 0)
            {
                activateHerbivores();
                herbivoreIter = minHerbivoreIter + randomNumbers.nextInt(maxHerbivoreIter - minHerbivoreIter + 1);
            }
            if (iteration % plantIter == 0)
            {
                activatePlants();
                plantIter = minPlantIter + randomNumbers.nextInt(maxPlantIter - minPlantIter + 1);
            }

            deactivateAnimals();
            iteration++;

            //pause to prevent game from moving too fast
            try
            {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //MARK: Helper functions

    /**
     * Reset the isActive Variables of the Animals to prepare for next Iteration
     */
    private static void deactivateAnimals()
    {
        for (int i = 0; i < Earth.height; i++)
        {
            for (int j = 0; j < Earth.width; j++)
            {
                Entity entity = earth.getEntityAt(i, j);
                if (entity instanceof Animal) ((Animal) entity).deactivate();
            }
        }
    }
}
