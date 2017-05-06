/**
 * Created by socra_000 on 3/27/2017.
 */
public class Plant {

    private String plant;
    private boolean [][] array; //No superclass so defined here.

    Plant(boolean [][] array){
        this.array=array;
        this.plant = "*";
    }
    public String getString(){
        return plant;
    }
    public String born(){
        return "*";
    }
    public void visited(int a, int b){
        this.array[a][b]= true;
    }
    public boolean checkIfVisited(int a, int b) {
        return array[a][b];
    }
}
