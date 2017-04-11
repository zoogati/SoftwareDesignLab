/**
 * Created by socra_000 on 3/20/2017.
 */
import java.util.Random;
public class Herbivore extends Animal{

    private String herb;
    public int health = 3;

    Herbivore(boolean[][]array ){
        super(array);
        this.herb = "&";
    }

    public String getString (){
        return herb;
    }

    public String born() {
        Random Numbers = new Random();
        int n = Numbers.nextInt(8);
        health -= n;
        return "&";
    }

    public void increaseHealth(){
        Random Numbers = new Random();
        int n = Numbers.nextInt(8);
        health +=n;
    }
    public String eat()
    {
        return "&";
    }
}
