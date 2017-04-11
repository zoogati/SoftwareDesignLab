/**
 * Created by socra_000 on 3/20/2017.
 */
public class Plant {

    private String plant;
    public boolean [][] array; //No superclass so defined here.

    Plant(boolean [][] array){
        this.array=array;
        this.plant = "*";
    }
    public String getString(){
        return plant;
    }
    public String born(){
        return plant;
    }
    public void checkIfVisited(int a, int b){
        this.array[a][b]= true;
    }
}
