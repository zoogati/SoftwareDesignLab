/**
 * Created by socra_000 on 3/27/2017.
 */
import java.util.Random;


public class Carnivore extends Animal{

    private String carn;

    private int health = 3;
    private int maxHealth = 10;
    private int age = 0;
    private int maxage; //TODO: Max age depends on number of days (20 if "unlimited") setMaxAge

    //TODO: Add method that checks if object is eligible to give birth (health > 4 && age > 4) checkForBirth

    Carnivore(boolean[][] array){
        super(array);
        this.carn ="@";
    }

    public String getString(){
        return carn;
    }

    public int getHealth() { return health;}


    public String born(){
        Random Numbers= new Random();
        int n = Numbers.nextInt(3);
        health -=n;
        return "@";
    }

    public String eat(){
        if (health < maxHealth) {
            health += 1;
            return "@";
        }
        else return "&";
    }
}
