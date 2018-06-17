import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Original {
    public static void main(String[] args) {
        //Initialize variables used in every case
        Scanner input = new Scanner(System.in);
        String filename;
        int choice, state = 1;
        boolean isPerm;
        boolean check;

        //Menu loop
        while(state == 1) {
            choice = 0;
            isPerm = false;
            check = false;

            //Menu
            System.out.println("=====================================================================");
            System.out.println("                                Welcome!                             ");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1. Check if an array is a permutation.");
            System.out.println("2. Find the composition of two arrays.");
            System.out.println("3. Find the inverse of an array.");
            System.out.println("4. Compute the permutation of an array to a power.");
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Menu Choice: ");

            //Checks if input is a number and if it is valid
            while (!check) {
                try {
                    choice = Integer.parseInt(input.nextLine());
                    check = true;
                } catch (NumberFormatException e) {
                    System.out.print("Not a number. Please try again: ");
                }
                if (choice < 1 || choice > 4) {
                    System.out.println("Not an option. Please enter a number between 1-4: ");
                    check = false;
                }
            }

            //Switch statement for menu choice
            switch (choice) {

                //Determines if an input array is a permutation
                case 1: {
                    System.out.println("=====================================================================");
                    System.out.println("                     Check if it is a Permutation                    ");
                    System.out.println("=====================================================================");

                    //Fetches file & array
                    System.out.print("Enter the filepath: ");
                    filename = input.nextLine();
                    int[] array = readFromFile(filename);


                    //Checks if array is a permutation
                    isPerm = isPermutation(array);
                    System.out.println("=====================================================================");
                }
                break;

                //Determines the composition of two permutation arrays
                case 2: {
                    int[] firstArray = null;
                    int[] secondArray = null;
                    System.out.println("=====================================================================");
                    System.out.println("          Calculate Composition of Two Permutation Arrays            ");
                    System.out.println("=====================================================================");

                    //Fetches first file & array and checks if it is a permutation
                    System.out.print("Enter the first filepath: ");
                    while (!isPerm) {
                        filename = input.nextLine();
                        firstArray = readFromFile(filename);
                        isPerm = isPermutation(firstArray);

                        if (!isPerm) {
                            System.out.println("Composition requires that the arrays are permutations.");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.print("Try a new filepath: ");
                        }
                    }
                    System.out.println("---------------------------------------------------------------------");
                    isPerm = false;

                    //Fetches second file & array and checks if it is a permutation
                    System.out.print("Enter the second filepath: ");
                    while (!isPerm) {
                        filename = input.nextLine();
                        secondArray = readFromFile(filename);
                        isPerm = isPermutation(secondArray);

                        if (!isPerm) {
                            System.out.println("Composition requires that the arrays are permutations.");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.print("Try a new filepath: ");
                        }
                    }
                    System.out.println("---------------------------------------------------------------------");

                    //Computes the composition of the arrays
                    int[] composition = findComposition(firstArray, secondArray);
                    System.out.print("The composition of the arrays is: ");
                    for (int i = 0; i < composition.length; i++) {
                        System.out.print(" " + composition[i] + " ");
                    }
                    System.out.println();
                    System.out.println("=====================================================================");
                }
                break;

                //Determines the inverse of a permutation array
                case 3: {
                    int[] array = null;

                    System.out.println("=====================================================================");
                    System.out.println("                            Find an Inverse                          ");
                    System.out.println("---------------------------------------------------------------------");

                    //Fetches file & array; determines if it is a permutation
                    System.out.print("Enter filepath: ");
                    while (!isPerm) {
                        filename = input.nextLine();
                        array = readFromFile(filename);
                        isPerm = isPermutation(array);

                        if (!isPerm) {
                            System.out.println("Inversion requires that the array be a permutation.");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.print("Try a new filepath: ");
                        }
                    }
                    System.out.println("---------------------------------------------------------------------");

                    //Determines the inverse of the permutation array
                    int[] inverse = findInverse(array);
                    System.out.print("The inverse of the array is: ");
                    for (int i = 0; i < inverse.length; i++) {
                        System.out.print(" " + inverse[i] + " ");
                    }
                    System.out.println();
                    System.out.println("=====================================================================");
                }
                break;

                //Determines the composition of an array, to itself, k times
                case 4: {
                    int[] array = null;

                    System.out.println("=====================================================================");
                    System.out.println("                     Compose an Array to Itself                      ");
                    System.out.println("---------------------------------------------------------------------");

                    //Fetches file & array; determines if it is a permutation
                    System.out.print("Enter filepath: ");
                    while (!isPerm) {
                        filename = input.nextLine();
                        array = readFromFile(filename);
                        isPerm = isPermutation(array);

                        if (!isPerm) {
                            System.out.println("Inversion requires that the array be a permutation.");
                            System.out.println("---------------------------------------------------------------------");
                            System.out.print("Try a new filepath: ");
                        }
                    }
                    System.out.println("---------------------------------------------------------------------");

                    //Determines the composition
                    int[] selfComposition = findPermutation(array);
                    for (int i = 0; i < selfComposition.length; i++) {
                        System.out.print(" " + selfComposition[i] + " ");
                    }
                    System.out.println();
                    System.out.println("=====================================================================");
                }
                break;
            }

            //Allows a user to make another calculation or exit the program
            state = 0;
            System.out.println("Would you like to continue using the program? (1 for yes, 2 for no): ");
            while((state < 1) || (state > 2)) {
                try {
                    state = Integer.parseInt(input.nextLine());
                    if (state < 1 || state > 2) {
                        System.out.print("Not a valid option. Please try again: ");
                    }
                }
                catch(NumberFormatException e) {
                    System.out.print("Not a number. Try again: ");
                }
            }
        }
    }

    public static boolean isPermutation(int[] array) {
        boolean result = true;
        System.out.println("Checking if the array is a permutation...");

        //Checks for duplicate elements, which in turn invalidates permutation status
        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[i] == array[j]) {
                    System.out.println("Repeated element: " + array[i]);
                    result = false;
                }
            }
        }
        if(result) {
            System.out.println("The array is a permutation.");
        }
        else {
            System.out.println("The array is not a permutation.");
        }
        return result;
    }

    public static int[] readFromFile(String filename) {
        //Establishes memory allocations and initializes variables
        Scanner input;
        File data;
        String fromFile = "";
        boolean success = false, retry = false;
        int length = 0, i = 0, counter = 0;
        int[] array = null;

        //Only exits if the file was found and its composition was in a valid format
        while(!success) {
            //Executes if initial attempt to fetch file fails
            if(retry) {
                System.out.println("---------------------------------------------------------------------");
                System.out.print("Enter new filepath: ");
                Scanner scnr = new Scanner(System.in);
                filename = scnr.nextLine();
            }

            //Attempts to read the file
            System.out.println("Reading file... ");
            try {
                //Assigns values to already established memory allocation
                data = new File(filename);
                input = new Scanner(data);
                fromFile = input.nextLine();

                //Error exception for array length & stores length in variable
                try {
                    length = Integer.parseInt(fromFile);
                    System.out.println("Array length from file: " + length);
                } catch (NumberFormatException e) {
                    System.out.println("Array length invalid. Please try another file.");
                    retry = true;
                }

                //Error exception for array & Stores the next line integers into the array
                try {
                    array = new int[length];
                    counter = 0;
                    i = 0;
                    while (input.hasNextInt()) {
                        counter++;
                        array[i] = input.nextInt();
                        i++;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Length invalid. Please try another file.");
                    retry = true;
                }
                catch (ArrayIndexOutOfBoundsException e) {
                }

                //Catches invalid array length and continues if no errors were found
                input.close();
                System.out.println("Actual array length: " + counter);
                if((counter < length || counter > length)) {
                    System.out.println("Array length invalid. Please try another file.");
                    retry = true;
                }
                else {
                    System.out.print("Array:");
                    for(i = 0; i < array.length; i++) {
                        System.out.print(" " + array[i] + " ");
                    }
                    retry = false;
                    System.out.println();
                    System.out.println("File read successfully.");
                    System.out.println("---------------------------------------------------------------------");
                    success = true;
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found. Please try another file.");
                retry = true;
            }
        }
        return array;
    }

    public static int[] findComposition(int[] firstArray, int[] secondArray) {
        //Establishes memory allocations and initializes variables
        int[] composition = null;
        boolean success = false, check = false;
        int counter = 0;

        //Error checks length of arrays
         while(!success) {
             //Catches the error for arrays of different sizes
             try {
                 counter = 0;
                 composition = new int[firstArray.length];
                 for(int i = 0; i < firstArray.length; i++) {
                     counter++;
                     composition[i] = firstArray[secondArray[i]];
                 }
                 success = true;
             }
             catch(ArrayIndexOutOfBoundsException e) {
                 //Initializes variables only needed in the case that there is an error
                 Scanner input = new Scanner(System.in);
                 String filename = "";
                 int choice = 0;
                 System.out.println("The arrays are of a different size:");
                 System.out.println("Length of array 1: " + firstArray.length);
                 System.out.println("Length of array 2: " + secondArray.length);
                 System.out.print("Which array would you like to change? (1 or 2): ");

                 //Makes sure that the user can only pick one of two options
                 while(!(choice == 1 || choice == 2)) {
                     check = false;
                     while(!check) {
                         try {
                             choice = Integer.parseInt(input.nextLine());
                             if (choice == 1) {
                                 firstArray = readFromFile(filename);
                                 check = true;
                             } else if (choice == 2) {
                                 secondArray = readFromFile(filename);
                                 check = true;
                             } else {
                                 System.out.print("Please input 1 or 2: ");
                             }
                         }
                         catch(NumberFormatException s) {
                             System.out.println("Not a number.");
                         }
                     }
                 }
             }
         }
        return composition;
    }

    public static int[] findInverse(int[] array) {
        System.out.println("Calculating inverse...");
        int[] inverse = new int[array.length];
        //Catches NumberFormatException
        try {
            for(int i = 0; i < array.length; i++) {
                inverse[i] = array[Integer.parseInt(String.valueOf(array[i]))];
            }
        }
        catch (NumberFormatException e){
            Scanner input = new Scanner(System.in);
            String filename = "";
            boolean isPerm = false;

            System.out.println("Error! Value from array is not an integer.");
            System.out.print("Enter new filepath: ");
            while(!isPerm) {
                filename = input.nextLine();
                array = readFromFile(filename);
                isPerm = isPermutation(array);

                if(!isPerm){
                    System.out.println("Inversion requires that the array be a permutation.");
                    System.out.println("---------------------------------------------------------------------");
                    System.out.print("Try a new filepath: ");
                }
            }
            System.out.println("---------------------------------------------------------------------");


        }
        return inverse;
    }

    public static int[] findPermutation(int[] array) {
        boolean check = false;
        int choice = 0;
        Scanner input = new Scanner(System.in);

        //Checks that a user can only compose to a numeric degree, excluding 0
        System.out.print("To what degree would you like to compose this array to? ");
        while(!check) {
            try {
                choice = Integer.parseInt(input.nextLine());
                check = true;
            }
            catch(NumberFormatException e) {
                System.out.print("Not a number. Please try again: ");
            }

            if(choice == 0) {
                System.out.print("You can't compose to the 0th degree. Try again: ");
                check = false;
            }
        }

        //Computes the composition
        System.out.println("Computing composition...");

        //If the array to be composed is to a negative degree, the inverse of the array is composed
        if(choice < 0) {
            array = findInverse(array);
        }

        //Computes the composition
        int[] permutation = new int[array.length];
        for(int i = 1; i < Math.abs(choice); i++) {
            for(int j = 0; j < array.length; j++) {
                permutation[j] = array[array[j]];
            }
        }
        System.out.print("The composition of the array to itself " + choice + " times is:");
        return permutation;
    }
}