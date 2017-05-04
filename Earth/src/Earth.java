/**
 * Created by socra_000 on 3/26/2017.
 */
import java.util.Random;

public class Earth extends Clock {

    private int Ax,Ay,Bx,By,Cx,Cy; //Indexes respective the Herb, Carn, and Plant
    private Random rand = new Random();
    private String[][] earth = new String[Clock.size][Clock.size];

    public void earthInitialize(Herbivore herb, Carnivore carn, Plant plant, int population) {
        int index = 0;
        while (index < population) {

            //Plant Initialization
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            earth[Cx][Cy] = plant.getString();
            plant.checkIfVisited(Cx, Cy);

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

    public void birthEarth(Herbivore herb, Carnivore carn, Plant plant, int dayNumber) {

        if(dayNumber % 3 == 0){
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            earth[Cx][Cy] = plant.born();
            plant.checkIfVisited(Cx,Cy);
        }

        if (dayNumber % 4 == 0) {
            if (herb.getHealth() % 5 == 0) {
                Ax = rand.nextInt(earth.length);
                Ay = rand.nextInt(earth.length);
                herb.checkIfVisited(Ax, Ay);
                earth[Ax][Ay] = herb.born();
            }
            if (carn.getHealth() % 5 == 0) {
                Bx = rand.nextInt(earth.length);
                By = rand.nextInt(earth.length);
                carn.checkIfVisited(Bx, By);
                earth[Bx][By] = carn.born();
            }
        }
    }

    public void herbMove(Herbivore herb) {
        for (int row = 0; row < earth.length; row++) {
            for (int column = 0; column < earth.length; column++) {
                if (earth[row][column].equals("&")) {
                    if (column == 0) {
                        if (earth[row][column + 1].equals("*")) {
                            earth[row][column + 1] = herb.eat();
                        }
                        else if(row<earth.length-1 && earth[row+1][column].equals("*") ) {
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
                            earth[row][column + 1] = herb.eat();
                        }
                        else if (earth[row][column - 1].equals("*")) {
                            earth[row][column - 1] = herb.eat();
                        }
                        else if(row==0 && earth[row+1][column].equals("*")){
                            earth[row+1][column] = herb.eat();
                        }
                        else if (row>0 && row<4 && earth[row+1][column].equals("*")){
                            earth[row+1][column] = herb.eat();
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
                        }
                    }
                }
            }
        }
    }

    public void carnMove(Herbivore herb, Carnivore carn) {
        for (int row = 0; row < earth.length; row++) {
            for (int column = 0; column < earth.length; column++) {
                if (earth[row][column].equals("@")) {
                    if (column == 0) {
                        if (earth[row][column + 1].equals("&")) {
                            earth[row][column + 1] = carn.eat();
                        }
                        else if(row<earth.length-1 && earth[row+1][column].equals("*") ){
                            earth[row+1][column] = carn.getString();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.checkIfVisited(row,column+1);
                            earth[row][++column] = carn.getString();
                        }
                    }
                    else if (column < earth.length-1) {
                        if (earth[row][column + 1].equals("&")) {
                            earth[row][column + 1] = carn.eat();
                        }
                        else if (earth[row][column - 1].equals("&")) {
                            earth[row][column - 1] = carn.eat();
                        }
                        else if(row==0 && earth[row+1][column].equals("&")){
                            earth[row+1][column] = carn.eat();
                        }
                        else if (row>0 && row<4&&earth[row+1][column].equals("&")){
                            earth[row+1][column] = carn.eat();
                        }
                        else if(row>0 && row<4&&earth[row-1][column].equals("*")){
                            earth[row-1][column] = carn.getString();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.checkIfVisited(row, column + 1);
                            earth[row][++column] = carn.getString();
                        }
                    }
                    else if (column ==earth.length-1 && earth[row][column].equals("@") && row < earth.length-1) {
                        if (earth[row + 1][0].equals("&")) {
                            earth[row + 1][0] = carn.eat();
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

    public String[][] getEarth() {
        return earth;
    }
}
