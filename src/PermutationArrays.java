import java.util.Scanner;

public class PermutationArrays {
    private static Scanner input = new Scanner(System.in);
    private static boolean isRunning = true;
    private static boolean isValid = true;
    private static int choice = Integer.MIN_VALUE;


    public static void main(String[] args) {
        while(isRunning) {

            //Avoids printing menu if choice was invalid
            if(isValid)
                printMenu();

            getIntegerInput();

            //Switches between possible cases
            switch(choice) {
                case 1:
                    checkPermutation();
                    break;
                case 2:
                    computeComposition();
                    break;
                case 3:
                    computeInverse();
                    break;
                case 4:
                    computePower();
                    break;
                default:
                    System.out.println("Invalid input.");
                    isValid = false;
                    continue;
            }

            promptContinuation();

        }
    }

    private static void printMenu() {

    }

    private static void checkPermutation() {

    }

    private static void computeComposition() {

    }

    private static void computeInverse() {

    }

    private static void computePower() {

    }

    private static void getIntegerInput() {

    }

    private static void promptContinuation() {

    }
}