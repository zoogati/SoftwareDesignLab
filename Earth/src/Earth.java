/**
 * Created by socra_000 on 3/26/2017.
 */
import java.util.Random;

public class Earth extends Clock {

    private int Ax,Ay,Bx,By,Cx,Cy; //Indexes respective the Herb, Carn, and Plant
    private Random rand = new Random();
    private String[][] earth = new String[Clock.size][Clock.size];

    public void earthInitialize(Plant plant, Carnivore carn,Herbivore herb , int population) {
        int index = 0;
        while (index < population) {

            //Plant Initialization
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            earth[Cx][Cy] = plant.getString();
            plant.visited(Cx, Cy);
            index++;

            // Herbivore Initialization
            Ax = rand.nextInt(earth.length);
            Ay = rand.nextInt(earth.length);
            if(!plant.checkIfVisited(Ax, Ay)) {
                earth[Ax][Ay] = herb.getString();
                herb.visited(Ax, Ay);
            }

            //Carnivore Initialization
            Bx = rand.nextInt(earth.length);
            By = rand.nextInt(earth.length);
            if(!plant.checkIfVisited(Bx, By) && !herb.checkIfVisited(Bx, By)) {
                earth[Bx][By] = carn.getString();
                carn.visited(Bx, By);
            }

        }

        //Free spaces after initializations.
        for (int i = 0; i < earth.length; i++) {
            for (int j = 0; j < earth.length; j++) {
                if (earth[i][j] == null) {
                    earth[i][j] = ".";
                }
            }
        }
    }

    public void birthEarth(Plant plant, Carnivore carn,Herbivore herb, int dayNumber) {

        if(dayNumber % 3 == 0){
            //TODO: Should iterate at least length/3 of every row.
            Cx = rand.nextInt(earth.length);
            Cy = rand.nextInt(earth.length);
            if (carn.checkIfVisited(Cx, Cy) && herb.checkIfVisited(Cx, Cy)) {
                earth[Cx][Cy] = plant.born();
                plant.visited(Cx, Cy);
            }
        }
        /*TODO: Want to iterate through every visited object (Animal array)
         *TODO: Step 1: Iterate through entire array.
         *TODO: Step 2: if object.checkIfVisited true go to next check.
         */
        //TODO: getHealth to be replace with checkForBirth method.
        if (herb.getHealth() >= 5) {
            //TODO: Should be a random location AROUND the object. [x+-1][y+-1]
            Ax = rand.nextInt(earth.length);
            Ay = rand.nextInt(earth.length);
            if (plant.checkIfVisited(Ax, Ay) && carn.checkIfVisited(Ax, Ay)) {
                earth[Ax][Ay] = herb.born();
                herb.visited(Ax, Ay);
            }
        }
        //TODO: Same as above.
        if (carn.getHealth() >= 5) {
            Bx = rand.nextInt(earth.length);
            By = rand.nextInt(earth.length);
            if (plant.checkIfVisited(Bx, By) && herb.checkIfVisited(Bx, By)) {
                earth[Bx][By] = carn.born();
                carn.visited(Bx, By);
            }
        }
    }

    //TODO: Might have to completely replace these move methods. Kinda drunk when I made them. Attempt Refactor.
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
                            herb.visited(row,column+1);
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
                            herb.visited(row,column+1);
                            earth[row][++column] = herb.getString();
                        }
                    }
                    else if (column == earth.length-1 && earth[row][column].equals("&") && row < earth.length-1) {
                        if (earth[row + 1][0].equals("*")) {
                            earth[row + 1][0] = herb.eat();
                        }
                        else {
                            earth[row][column] = ".";
                            herb.visited(row+1,0);
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
                            carn.visited(row,column+1);
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
                            carn.visited(row, column + 1);
                            earth[row][++column] = carn.getString();
                        }
                    }
                    else if (column ==earth.length-1 && earth[row][column].equals("@") && row < earth.length-1) {
                        if (earth[row + 1][0].equals("&")) {
                            earth[row + 1][0] = carn.eat();
                        }
                        else {
                            earth[row][column] = ".";
                            carn.visited(row+1,0);
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
