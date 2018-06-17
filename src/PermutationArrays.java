import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
                    System.out.println("\n== Check if an array is a permutation ==");
                    if(checkPermutation(arrayFromFile()))
                        System.out.println("== The array is a permutation ==");
                    else System.out.println("== The array is not a permutation ==");
                    break;
                case 2:
                    System.out.println("\n== Compute the composition of two arrays ==");
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

            isValid = true;
            promptContinuation();
        }
    }

    private static void printMenu() {
        System.out.println("=====================================================================");
        System.out.println("                                Welcome!\n");
        System.out.println("1. Check if an array is a permutation");
        System.out.println("2. Find the composition of two arrays");
        System.out.println("3. Find the inverse of an array");
        System.out.println("4. Compute the permutation of an array to a power");
        System.out.println("=====================================================================");
    }

    private static boolean checkPermutation(int[] array) {
        Set<Integer> set = new HashSet<>();

        //If the array contains duplicate integers, it is not a permutation
        for(int element: array) {
            if(set.contains(element))
                return false;

            set.add(element);
        }

        return true;
    }

    private static void computeComposition() {

    }

    private static void computeInverse() {

    }

    private static void computePower() {

    }

    private static void getIntegerInput() {
        try {
            choice = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e) {
            choice = Integer.MAX_VALUE;
        }
    }

    private static void promptContinuation() {
        System.out.println("Would you like to check another array or perform another computation? (Y/N)");
        char continuation = input.nextLine().charAt(0);
        if(continuation != 'y' && continuation != 'Y')
            isRunning = false;
        else System.out.println();
    }

    private static int[] arrayFromFile() {
        int[] array;

        while(true) {
            System.out.print("Enter filename (root is project folder): ");
            try {
                File file = new File(input.nextLine());
                Scanner data = new Scanner(file);
                int counter = 0;

                //Reads first line as array length
                array = new int[Integer.parseInt(data.nextLine())];
                data.nextLine();

                //Reads integers from second line into array
                while(data.hasNextInt()) {
                    array[counter] = data.nextInt();
                    counter++;
                }

                //Throws exception if amount of integers does not equal array length
                if(counter + 1 != array.length)
                    throw new NumberFormatException();

                //Throws exception if file contains more data
                if(data.hasNextLine())
                    throw new NumberFormatException();

                break;
            } catch(FileNotFoundException f) {
                System.out.println("File not found...");
            } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("File is corrupt... please try another file.");
            }
        }

        return array;
    }
}