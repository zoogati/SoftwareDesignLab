/**
 * Created by socra_000 on 3/27/2017.
 */
import java.util.Random;
public class Herbivore extends Animal{

    private String herb;

    private int health = 3;
    private int maxHealth = 10;
    private int age = 0;
    private int maxAge;     //TODO: Max age depends on number of days (20 if "unlimited") setMaxAge

    //TODO: Add method that checks if object is eligible to give birth (health > 4 && age > 4) checkForBirth

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
        health -= n;    //Sets the health of the new object.
        return "&";
    }

    public String eat() {
        if (health < maxHealth) {
            health += 1;
            return "&";
        }
        else return "&";
    }
}
