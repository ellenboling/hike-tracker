import java.util.Scanner;

//The hikes class represents hikes by names of the hikes and their elevation.

public class Hikes implements Comparable<Hikes> {
    private final String trailName;
    private final int elevation; 

    //Constructor for users hike and elevation.
    public Hikes(String trailName, int elevation) {
        if (trailName == null || elevation < 0) {
            throw new IllegalArgumentException();
        }
        this.trailName = trailName;
        this.elevation = elevation;
    }

    public Hikes(Hikes otherHike) {
        this(otherHike.trailName, otherHike.elevation);
    }

    public static Hikes parse(Scanner input) {
        if(input == null){
            throw new IllegalArgumentException();
        }
        System.out.print("What is the trail's name? ");
        String trailName = input.nextLine();

        System.out.print("What is elevation gain of that trail in feet? ");
        int elevation = Integer.parseInt(input.nextLine());

        return new Hikes(trailName, elevation);
    }

    public String getTrailName() {
        return this.trailName;
    }


    public int getElevation() {
        return this.elevation;
    }

    @Override
    public String toString() {
        return "Trail " + this.trailName + " has an elevation of " + this.elevation + " feet.";
    }

    public int compareTo(Hikes otherHike){
        if(this.elevation != otherHike.elevation){
            return Integer.compare(this.elevation, otherHike.elevation);
        }else{
            return this.trailName.compareTo(otherHike.trailName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Hikes) {
            Hikes otherHike = (Hikes) o;
            return this.elevation == otherHike.elevation
                    && this.trailName.equals(otherHike.trailName);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        int h = 0;
        h = 27 * h + this.trailName.hashCode();
        h = 27 * h + elevation;
        return h;
    }
}