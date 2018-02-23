package info233v2018.labuke8;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Write a description of class TSVReader here.
 *
 * @author Truls Pedersen
 * @version (a version number or a date)
 */
public class TSVStorage
{
    /**
     * Constructor for objects of class TSVReader
     */
    private TSVStorage() {
        // Intentionally empty private constructor
    }
    
    public static ArrayList<String[]> readTSVFile(String filename, int columns) {
        try{
            return Files.lines(Paths.get(filename))
                .map(line -> line.split("\t"))
                .filter(row -> row.length == columns)
                .collect(Collectors.toCollection(ArrayList::new));
        } catch(IOException e) {
            System.out.println("Unable to open " + filename);
            return null;
        }
    }
}
