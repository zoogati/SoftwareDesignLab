/**
 * Created by socra_000 on 3/20/2017.
 */
import java.util.Random;


public class Carnivore extends Animal{

    private String carn;
    private int health = 3;

    Carnivore(boolean[][] array){
        super(array);
        this.carn ="@";
    }

    public String getString(){
        return carn;
    }

    public String born(){
        Random Numbers= new Random();
        int n = Numbers.nextInt(8);
        health -=n;
        return "@";

    }

    public String increaseHealth(){
        Random Numbers= new Random();
        int n = Numbers.nextInt(8);
        health += n;
        return "@";
    }

}
