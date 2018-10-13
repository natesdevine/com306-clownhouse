
/**
 * This class is a test class. This class tests the 
 * the other three classes by invoking their methods/operations. 
 * 
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;  
import edu.ConnColl.SoftwareEngineering.MyFileReader;
import org.apache.commons.csv.CSVParser;  
import org.apache.commons.csv.CSVRecord; 

public class TestMovieRatings  {    
    
    // loads movie data from the CSV Rated-Movies-for-Testing 
    // file into and array list and returns the array list
    public ArrayList<MovieInformation> loadMovieData(String fileName) {
        ArrayList<MovieInformation> movieData = new ArrayList<MovieInformation>();
        MyFileReader fr = new MyFileReader(fileName);
        CSVParser parser = fr.accessCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("IMDB");
            String title = record.get("MovieTitle");
            String year = record.get("MovieYear");
            String genres = record.get("MovieGenre");
            String director = record.get("MovieDirector");
            String country = record.get("MovieCountry");
            String poster = record.get("PosterURL");
            int minutes = Integer.parseInt(record.get("MovieLength"));
            MovieInformation movieInfo = new MovieInformation(id, title, year, genres, director,country, poster, minutes);
            movieData.add(movieInfo);        
        }
        return movieData;        
    }
    
    // tester method to test the loadMovieData() method
    public void testLoadMovieData() {
        String filePath = "DataFiles/Rated-Movies-for-Testing.csv";
        ArrayList<MovieInformation> movieData = loadMovieData(filePath);
        System.out.println("Number of Movies: " + movieData.size());
        System.out.println("Movie Information: " + movieData);               
    }  
}
