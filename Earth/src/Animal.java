/**
 * Created by socra_000 on 3/27/2017.
 */
public abstract class Animal{

    public boolean [][] array;
    Animal(boolean [][] array){
        this.array = array;
    }
    public void checkIfVisited(int a, int b) {
        array[a][b] = true;
    }

}
