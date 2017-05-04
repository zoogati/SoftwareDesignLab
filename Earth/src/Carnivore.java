/**
 * Created by socra_000 on 3/27/2017.
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

    public int getHealth() { return health;}


    public String born(){
        if (this.health == 5) {
            Random Numbers = new Random();
            int n = Numbers.nextInt(3);
            health -= n;
            return "@";
        }
        else return ".";
    }

    public String eat(){
        health += 1;
        return "@";
    }


}
