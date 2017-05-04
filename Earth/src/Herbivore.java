/**
 * Created by socra_000 on 3/27/2017.
 */
import java.util.Random;
public class Herbivore extends Animal{

    private String herb;
    private int health = 3;

    Herbivore(boolean[][]array ){
        super(array);
        this.herb = "&";
    }

    public String getString (){
        return herb;
    }

    public int getHealth() { return health; }

    public String born() {
        Random Numbers = new Random();
        int n = Numbers.nextInt(3);
        health -= n;
        return "&";
    }

    public String eat() {
        health += 1;
        return "&";
    }
}
