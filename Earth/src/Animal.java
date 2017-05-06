/**
 * Created by socra_000 on 3/27/2017.
 */
public abstract class Animal{

    private boolean [][] array;

    Animal(boolean [][] array){
        this.array = array;
    }

    public void visited(int a, int b) {
        array[a][b] = true;
    }

    public boolean checkIfVisited(int a, int b) {
        return array[a][b];
    }

}
