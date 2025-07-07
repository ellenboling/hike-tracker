import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;


public class Testing {

    @Test
    public void checkAdd(){
        CollectionManager collection = new CollectionManager();
        Hikes firstHike = new Hikes("Cle Elum", 2200);
        Hikes secondHike = new Hikes("Gold Bar", 800);
        collection.add(firstHike);
        collection.add(secondHike);
        assertTrue(collection.contains(firstHike));
        assertTrue(collection.contains(secondHike));
    }

    @Test
    public void checkContains(){
        CollectionManager collection = new CollectionManager();
        Hikes firstHike = new Hikes("Green Lake", 1300);
        collection.add(firstHike);
        assertTrue(collection.contains(new Hikes("Green Lake", 1300)));
        assertFalse(collection.contains(new Hikes("Granite Falls", 98)));
    }

    @Test
    public void checkToString(){        
        CollectionManager collection = new CollectionManager();
        collection.add(new Hikes ("Cle Elum", 2200));
        collection.add(new Hikes("Gold Bar", 800));
        collection.add(new Hikes("Granite Falls", 98));
        String result = collection.toString();
        assertTrue(result.contains("Cle Elum"));
        assertTrue(result.contains("Gold Bar"));
        assertTrue(result.contains("Granite Falls"));
    }

    @Test
    public void checkSave() throws FileNotFoundException{
        CollectionManager collection = new CollectionManager();
        collection.add(new Hikes("Snoqualmie Falls", 3553));
        collection.add(new Hikes("Ronald", 2375));
        collection.add(new Hikes("Leavenworth", 1800));

        PrintStream output = new PrintStream("checkingSave.txt");
        output.print(collection.save());
        Scanner input = new Scanner(new File("checkingSave.txt"));
        CollectionManager complete = new CollectionManager(input);
        
        assertTrue(complete.contains(new Hikes("Snoqualmie Falls", 3553)));
        assertTrue(complete.contains(new Hikes("Ronald", 2375)));
        assertTrue(complete.contains(new Hikes("Leavenworth", 1800)));
    }

    @Test
    public void checkFilter(){
        CollectionManager collection = new CollectionManager();
        collection.add(new Hikes("Discovery Park", 140));
        collection.add(new Hikes("Rattlesnake Ledge", 1300));
        collection.add(new Hikes("Green Lake", 1300));
        List<Hikes> filterHikes = collection.filter(800);
        assertEquals(1, filterHikes.size());
        assertEquals("Discovery Park", filterHikes.get(0).getTrailName());
    }
}