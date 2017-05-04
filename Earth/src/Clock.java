import java.util.Random;
import java.util.Scanner;

/**
 * Created by socra_000 on 3/28/2017.
 */
public class Clock {

    private static Random rand = new Random();
    private static Scanner input = new Scanner(System.in);

    static int cycles, size, population;

    public static void main(String[] args) {

        //User now decides Earth size and day count.
        System.out.println("The Earth");
        System.out.println("What Earth size would you like? ");
        size = input.nextInt();
        population = 4*size;
        System.out.println("How many days would you like? ");
        cycles = input.nextInt();


        int day = 1; // Easier than using 0 to day

        boolean [][] visitedHerb = new boolean[size][size];
        boolean [][] visitedCarn = new boolean[size][size];
        boolean [][] visitedPlant = new boolean[size][size];

        Earth earth = new Earth();

        Herbivore first = new Herbivore(visitedHerb);
        Carnivore second = new Carnivore(visitedCarn);
        Plant third = new Plant(visitedPlant);


        while(day <= cycles){
            if(day == 1) {
                earth.earthInitialize(first,second,third,population);
            }
            if(day!=1) {

                //Added randomness to the movement
                if (day % 2 == rand.nextInt(4)) {
                    earth.herbMove(first);
                }
                if (day % 2 == rand.nextInt(2)) {
                    earth.carnMove(first, second);
                }

                earth.birthEarth(first,second,third,day);

                System.out.println();

                String[][] earthString = earth.getEarth();
                for (String[] anEarth : earthString) {
                    for (int j = 0; j < size; j++) {
                        System.out.print(anEarth[j] + " ");
                    }
                    System.out.println();
                }
            }
            day++;
        }
    }
}
