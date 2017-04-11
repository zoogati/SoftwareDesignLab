/**
 * Created by socra_000 on 3/21/2017.
 */
public class Clock {
    public static void main(String[] args) {

        System.out.println("The Earth");

        int day = 1; // Easier than using 0 to day
        int cycles = 30;
        int size = 30;
        int population = 150;

        boolean [][] visitedHerb = new boolean[size][size];
        boolean [][] visitedCarn = new boolean[size][size];
        boolean [][] visitedPlant = new boolean[size][size];


        Herbivore first = new Herbivore(visitedHerb);
        Carnivore second = new Carnivore(visitedCarn);
        Plant third = new Plant(visitedPlant);


        while(day <= cycles){
            if(day == 1) {
                Earth.earthInitialize(first,second,third,population);
            }
            if(day!=1) {
                if (day % 2 == 0) {
                    Earth.herbMove(first);
                }
                Earth.carnMove(first,second);

                Earth.birthEarth(first,second,third,day);

                System.out.println();

                String[][] earth2 = Earth.getEarth();
                for (String[] anEarth : earth2) {
                    for (int j = 0; j < earth2.length; j++) {
                        System.out.print(anEarth[j] + " ");
                    }
                    System.out.println();
                }
            }
            day++;
        }
    }
}
