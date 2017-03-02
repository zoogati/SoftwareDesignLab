import java.util.Scanner;

/**
 * Created by Socratis Katehis on 2/21/17.
 */
public class Welcome1 {
    public static void main(String[] args) {

        runProgram();

    }

    private static void printStars(int age, int temp) {
        Scanner input = new Scanner(System.in);

        if (age > temp) return;
        for(int i = 0; i < age; i++) {        //Use recursion to make stars
            System.out.print("*");
        }
        System.out.println();
        printStars(age+1, temp);
        System.out.println();

        System.out.println("Do you want to continue? (Y/N)");
        String answer = input.next();
        if (answer.equalsIgnoreCase("N")) {
            System.out.print("Goodbye!");
            System.exit(0);
        }
        else if(answer.equalsIgnoreCase("Y")) {
            runProgram();
        }
    }

    private static void runProgram() {
        Scanner input = new Scanner(System.in);

        int age;

        System.out.print("Please enter your age: ");
        age = input.nextInt();

        int temp = age; //hold value for recursive star function.

        System.out.println("The result is: \n");

        do {
            System.out.print(age + ", ");
            age = age / 2;
        } while (age > 1);

        while (age == 1) {
            System.out.print(age + "\n");
            age -= 1;
        }

        printStars(age, temp);
    }
}
