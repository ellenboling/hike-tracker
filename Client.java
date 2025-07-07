import java.util.*;
import java.io.*;

// Allows clients to use collection manager to create a binary search tree.
public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the CSE 123 Collection Manager! " +
                           "To begin, enter your desired mode of operation:");
        System.out.println();
        System.out.println("1) Start with an empty collection manager");
        System.out.println("2) Load collection from file");
        System.out.print("Enter your choice here: ");

        int choice = Integer.parseInt(console.nextLine());
        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice! Try again");
            choice = Integer.parseInt(console.nextLine());
        }

        CollectionManager collectionManager = null;
        if (choice == 1) {
            collectionManager = new CollectionManager();
        } else if (choice == 2) {
            System.out.print("Enter file to read: ");
            String inFileName = console.nextLine();
            File inFile = new File(inFileName);
            while (!inFile.exists()) {
                System.out.println("  File does not exist. Please try again.");
                System.out.print("Enter file to read: ");
                inFileName = console.nextLine();
                inFile = new File(inFileName);
            }
    
            collectionManager = new CollectionManager(new Scanner(inFile));
            System.out.println("Collection manager created!");
            System.out.println();
        }

        menu(console);
        String option = console.nextLine();
        while (!option.equalsIgnoreCase("quit")) {
            System.out.println();

            if (option.equalsIgnoreCase("add")) {
                System.out.print("What is the trail's name? ");
                String trailName = console.nextLine();
                System.out.print("What is elevation gain of that trail in feet? ");
                int elevation = Integer.parseInt(console.nextLine());
                Hikes hike = new Hikes(trailName, elevation);
                collectionManager.add(hike);
                System.out.println();
            } else if (option.equalsIgnoreCase("contains")) {
                System.out.print("What is the name of the trail your looking for?");
                String trailName = console.nextLine();
                System.out.print("What is the elevation gain of that trail in feet?");
                int elevation = Integer.parseInt(console.nextLine());
                Hikes hike = new Hikes(trailName, elevation);
                boolean wasFound = collectionManager.contains(hike);
                System.out.println("True if found, false otherwise: " + wasFound);
                System.out.println();
            } else if (option.equalsIgnoreCase("print")) {
                System.out.println(collectionManager.toString());
                System.out.println();
            } else if (option.equalsIgnoreCase("creative")) {
                System.out.print("What is the maximum elevation you'd hike?");
                int limit = Integer.parseInt(console.nextLine());
                List<Hikes> filteredList = collectionManager.filter(limit);
                if(filteredList.isEmpty()){
                    System.out.println("There are 0 hikes lower than your preferred elevation.");
                }else{
                    System.out.println("List of hikes within your preferred elevation:");
                    for(Hikes eachHike : filteredList){
                        System.out.println(eachHike);
                    }
                }
                System.out.println();
            } else if (option.equalsIgnoreCase("save")) {
                System.out.print("Enter file to save to: ");
                String outFileName = console.nextLine();
                PrintStream outFile = new PrintStream(new File(outFileName));
                collectionManager.save(outFile);
                System.out.println("Collection Manager exported!");
                System.out.println();
            } else if (!option.equalsIgnoreCase("quit")) {
                System.out.println("  Invalid choice. Please try again.");
                System.out.println();
            }

            menu(console);
            option = console.nextLine();
        }
    }

    private static void menu(Scanner console) {
        System.out.println("What would you like to do? Choose an option in brackets.");
        System.out.println("  [add] item");
        System.out.println("  [contains] item");
        System.out.println("  [print] my collection");
        System.out.println("  [save] my collection");
        System.out.println("  [creative] extension");
        System.out.println("  [quit] program");
    }
}

