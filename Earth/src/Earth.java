/**
 * Created by socra_000 on 3/19/2017.
 */
import java.util.Random;

public class Earth extends Clock {

    private static int Ax,Ay,Bx,By,Cx,Cy; //Indexes respective the Herb, Carn, and Plant
    private static Random rand = new Random();
    private static int size = 30;
    private static String[][] earth = new String[size][size];

    static void earthInitialize(Herbivore herb, Carnivore carn, Plant plant, int population) {
        int index = 0;
        while (index < population) {

            // Herbivore Initialization
            Ax = rand.nextInt(earth.length);
            Ay = rand.nextInt(earth.length);
            earth[Ax][Ay] = herb.getString();
            herb.checkIfVisited(Ax, Ay);

            //Carnivore Initialization
            Bx = rand.nextInt(earth.length);
            By = rand.nextInt(earth.length);
            earth[Bx][By] = carn.getString();
            carn.checkIfVisited(Bx, By);

            //Plant Initialization
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            earth[Cx][Cy] = plant.getString();
            plant.checkIfVisited(Cx, Cy);
            index++;
        }

        //Print free spaces after initializations.
        for (int i = 0; i < earth.length; i++) {
            for (int j = 0; j < earth.length; j++) {
                if (earth[i][j] == null) {
                    earth[i][j] = ".";
                }
            }
        }
    }

    static void birthEarth(Herbivore herb, Carnivore carn, Plant plant, int dayNumber) {
        if(herb.health%5 == 0){
            Ax = rand.nextInt(earth.length);
            Ay = rand.nextInt(earth.length);
            earth[Ax][Ay] = herb.born();
            herb.checkIfVisited(Ax,Ay);
        }
        if (dayNumber%4 == 0) {
            Bx = rand.nextInt(earth.length);
            By = rand.nextInt(earth.length);
            earth[Bx][By] = carn.born();
            carn.checkIfVisited(Bx,By);
        }
        if(dayNumber%3 == 0){
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            earth[Cx][Cy] = plant.born();
            plant.checkIfVisited(Cx,Cy);
        }
    }

    static void herbMove(Herbivore herb) {
        for (int row = 0; row < earth.length; row++) {
            for (int column = 0; column < earth.length; column++) {
                if (earth[row][column].equals("&")) {
                    if (column == 0) {
                        if (earth[row][column + 1].equals("*")) {
                            herb.increaseHealth();
                            earth[row][column + 1] = herb.eat();
                        }
                        else if(row<earth.length-1 && earth[row+1][column].equals("*") ){
                            herb.increaseHealth();
                            earth[row+1][column] = herb.eat();
                        }
                        else {
                            earth[row][column] = ".";
                            herb.checkIfVisited(row,column+1);
                            earth[row][++column] = herb.getString();
                        }
                    }
                    else if (column < earth.length-1) {
                        if (earth[row][column + 1].equals("*")) {
                            herb.increaseHealth();
                            earth[row][column + 1] = herb.eat();
                        }
                        else if (earth[row][column - 1].equals("*")) {
                            earth[row][column - 1] = herb.eat();
                            herb.increaseHealth();
                        }
                        else if(row==0 && earth[row+1][column].equals("*")){
                            herb.increaseHealth();
                            earth[row+1][column] = herb.eat();
                        }
                        else if (row>0 && row<4 && earth[row+1][column].equals("*")){
                            herb.increaseHealth();
                            earth[row+1][column] = herb.eat();
                        }
                        else if(row>0 && row<4 && earth[row-1][column].equals("*")){
                            herb.increaseHealth();
                            earth[row-1][column] = herb.eat();
                        }
                        else {
                            earth[row][column] = ".";
                            herb.checkIfVisited(row,column+1);
                            earth[row][++column] = herb.getString();
                        }
                    }
                    else if (column == earth.length-1 && earth[row][column].equals("&") && row < earth.length-1) {
                        if (earth[row + 1][0].equals("*")) {
                            earth[row + 1][0] = herb.eat();
                            herb.increaseHealth();
                        }
                        else {
                            earth[row][column] = ".";
                            herb.checkIfVisited(row+1,0);
                            earth[row + 1][0] = herb.getString();
                        }
                    }
                    else if(column == earth.length-1 && earth[row][column].equals("&") && row==earth.length-1){
                        if(earth[row][column - 1].equals("*")) {
                            earth[row][column - 1] = herb.eat();
                            herb.increaseHealth();
                        }
                    }
                }
            }
        }
    }

    static void carnMove(Herbivore herb, Carnivore carn) {
        for (int row = 0; row < earth.length; row++) {
            for (int column = 0; column < earth.length; column++) {
                if (earth[row][column].equals("@")) {
                    if (column == 0) {
                        if (earth[row][column + 1].equals("&")) {
                            earth[row][column + 1] = carn.increaseHealth();
                        }
                        else if(row<earth.length-1 && earth[row+1][column].equals("*") ){
                            earth[row+1][column] = carn.increaseHealth();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.checkIfVisited(row,column+1);
                            earth[row][++column] = carn.getString();
                        }
                    }
                    else if (column < earth.length-1) {
                        if (earth[row][column + 1].equals("&")) {
                            earth[row][column + 1] = carn.increaseHealth();
                        }
                        else if (earth[row][column - 1].equals("&")) {
                            earth[row][column - 1] = carn.increaseHealth();
                        }
                        else if(row==0 && earth[row+1][column].equals("&")){
                            earth[row+1][column] = carn.increaseHealth();
                        }
                        else if (row>0 && row<4&&earth[row+1][column].equals("&")){
                            earth[row+1][column] = carn.increaseHealth();
                        }
                        else if(row>0 && row<4&&earth[row-1][column].equals("*")){
                            earth[row-1][column] = carn.increaseHealth();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.checkIfVisited(row, column + 1);
                            earth[row][++column] = carn.getString();
                        }
                    }
                    else if (column ==earth.length-1 && earth[row][column].equals("@") && row < earth.length-1) {
                        if (earth[row + 1][0].equals("&")) {
                            earth[row + 1][0] = carn.increaseHealth();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.checkIfVisited(row+1,0);
                            earth[row + 1][0] = herb.getString();
                        }
                    }
                }
            }
        }
    }

    public static String[][] getEarth() {
        return earth;
    }
}
