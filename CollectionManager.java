import java.io.*;
import java.util.*;

// The Collection Manager class manages a binary search tree and allows users to
// add hikes, check if a collection contains a certain hike, print hikes, save hikes, 
// and filter hikes based on elevation. 

public class CollectionManager {

    private static class HikeNode{
        public final Hikes hike;
        public HikeNode left;
        public HikeNode right;

        public HikeNode(Hikes hike){
            this(hike, null, null);
        }

        public HikeNode(Hikes hike, HikeNode left, HikeNode right){
            this.hike = hike;
            this.left = left;
            this.right = right;
        }
    } 

    private HikeNode overallRoot;

    //Behavior: Initializes an empty collection.
    public CollectionManager(){
        overallRoot = null;
    }

    //Behavior: Creates a CollectionManager using users input in order.
    //Parameter: input to access user input.
    //Exceptions: throws IllegalArgumentException if input is null.
    public CollectionManager(Scanner input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        overallRoot = null;
        while(input.hasNextLine()){
            String trailString = input.nextLine();

            if(input.hasNextLine()){
                String elevationString = input.nextLine();
                int elevationInt = Integer.parseInt(elevationString);
                Hikes hike = new Hikes(trailString, elevationInt);
                add(hike);
            }
        }
    }


    //Behavior: Adds the item to the collection.
    //Parameter: hike to access the object being added.
    //Exceptions: throws IllegalArgumentException if hike is null.
    //Returns: returns node.
    public void add(Hikes hike){
        if(hike == null){
            throw new IllegalArgumentException();
        }
        overallRoot = add(hike, overallRoot);
    }

    private HikeNode add(Hikes hike, HikeNode node){
        if(node == null){
            return new HikeNode(hike);
        }
        if(hike.compareTo(node.hike) < 0){
            node.left = add(hike, node.left);
        }else if(hike.compareTo(node.hike) > 0){
            node.right = add(hike, node.right);
        }
        return node;
    }
    

    //Behavior: Checks if collection contains a certain hike.
    //Parameter: hike to access the object being added.
    //Exceptions: throws IllegalArgumentException if hike is null.
    //Returns: recursivley returns true if hike is in collection, false otherwise.
    public boolean contains(Hikes hike){
        if(hike == null){
            throw new IllegalArgumentException();
        }
        return contains(hike, overallRoot);
    }

    private boolean contains(Hikes hike, HikeNode node){
        if(node == null){
            return false;
        }
        if(hike.compareTo(node.hike) == 0){
            return true;
        }else if(hike.compareTo(node.hike) < 0){
            return contains(hike, node.left);
        }else{
            return contains(hike, node.right);
        }
    }


    //Behavior: Creates a string of the tree with elevation and hike name.
    //Returns: Recursivley returns string, and if it is null, it returns an empty string.
    @Override
    public String toString(){
        return toString(overallRoot);
    }

    private String toString(HikeNode node){
        if(node == null){
            return "";
        }
        return toString(node.left) + node.hike.toString() + toString(node.right);
    }


    //Behavior: Saves collection to a file.
    //Returns: recursivley returns 
    public String save(){
        return save(overallRoot);
    }

    //Behavior: saves hikes to the printstream in order.
    //Parameters: output to access printstream to enter hike to.
    //Parameters: node to access node in tree.
    //Exceptions: throws IllegalArgumentException if output is null.
    //Returns: recursivley returns saved hikes.
    public void save(PrintStream output){
        if(output == null){
            throw new IllegalArgumentException();
        }
        String total = save(overallRoot);
        output.print(total);
    }

    private String save(HikeNode node){
        if(node == null){
            return "";
        }
        String left = save(node.left);
        String right = save(node.right);
        return node.hike.getTrailName() + "\n" + node.hike.getElevation() 
        + "\n" + left + right;
    }


    //Behavior: filters hikes based on users preference of max elevation. 
    //Parameters: elevation to access max elevation.
    //Parameters: node to access node in tree.
    //Returns: recursivley returns all filtered hikes.
    public List<Hikes> filter (int elevation){
        return filter(elevation, overallRoot);
    }

    private List<Hikes> filter(int elevation, HikeNode node){
        if(node == null){
            return new ArrayList<>();
        }
        List<Hikes> all = new ArrayList<>();

        all.addAll(filter(elevation, node.left));
    
        if(node.hike.getElevation() < elevation){
            all.add(node.hike);
        }
        all.addAll(filter(elevation, node.right));
        return all;
    }
}