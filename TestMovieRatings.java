import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Map;
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


    public void testLoadMovieData() {
        String filePath = "data/Rated-Movies-for-Testing.csv";
        ArrayList<MovieInformation> movieData = loadMovieData(filePath);
        System.out.println("Number of Movies: " + movieData.size());
        System.out.println("Movie Information: " + movieData);               
    }

    public ArrayList<RaterPerson> loadRaterPersonData(String fileName) {
        ArrayList<RaterPerson> raterData = new ArrayList<RaterPerson>();
        MyFileReader fr = new MyFileReader(fileName);
        CSVParser parser = fr.accessCSVParser();

        for (CSVRecord record : parser) {
            String id = record.get("RaterPersonID");
            String movieID = record.get("MovieID");
            Double rating = Double.parseDouble(record.get("MovieRating"));

            //check if the person exists already, if they do, then just add their rating
            if (searchRaterID(id, raterData) == true) {
                RaterPerson person = getRater(id, raterData);
                person.addRating(movieID, rating);
            }

            //if rater doesn't exist
            else{
                RaterPerson raterPerson = new RaterPerson(id);
                raterPerson.addRating(movieID, rating);     
                raterData.add(raterPerson);
            }
        }

        return raterData;        
    }

    public void testLoadRaterPerson(){
        String filePath = "data/Movie-Ratings-for-Testing.csv";
        ArrayList<RaterPerson> raterData = loadRaterPersonData(filePath);

        for (RaterPerson person : raterData){
            System.out.println("Rater " + person.getRaterPersonID() + " rated the following movies: " + person.getMoviesRated());
        }
    }

    public boolean searchRaterID(String id, ArrayList<RaterPerson> raterData){
        for (RaterPerson person : raterData){
            if (person.getRaterPersonID().equals(id)) {return true;}   
        }
        return false;
    }

    public RaterPerson getRater(String id, ArrayList<RaterPerson> raterData){
        for (RaterPerson person : raterData){
            if (person.getRaterPersonID().equals(id)) {return person;}
        } 
        return null;       
    }

    public void findandDisplayMovies(){
        String filePath = "data/Rated-Movies-for-Testing.csv";
        ArrayList<MovieInformation> movieData = loadMovieData(filePath);
        int count = 0;

        //length
        System.out.println("LENGTH BITCHES");
        for (MovieInformation movie : movieData){
            if (movie.getMovieLength() > 135) {System.out.println(movie.toString());}
        }

        //directors
        System.out.println("DIRECTORS BITCHES");
        HashMap<String, Integer> directors = new HashMap<>();
        for (MovieInformation movie : movieData){
            String director = movie.getMovieDirector();

            if (directors.containsKey(director)) {directors.put(director, directors.get(director) + 1);}
            else{directors.put(director, 1);}
        }

        Map.Entry<String,Integer> maxEntry = null;
        for(Map.Entry<String,Integer> entry : directors.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        System.out.println(maxEntry);

        //drama
        System.out.println("DRAMA BITCHES");
        for (MovieInformation movie : movieData){
            if (movie.getMovieGenre().contains("Drama")) {count++;}
        }
        System.out.println("There are " + count + " movies that are dramas");
    }


    public void findandDisplayRaters(){
        String filePath = "data/Movie-Ratings-for-Testing.csv";
        ArrayList<RaterPerson> raterData = loadRaterPersonData(filePath);

        //Part 1
        String rater_id = "1";
        RaterPerson person = getRater(rater_id, raterData);

        System.out.println("part 1");
        System.out.println(person.findNumberOfRatings());

        //Part 2
        System.out.println("\npart 2");
        int max = 0; 
        int freq = 0;
        ArrayList<String> topRaters = new ArrayList<String>();

        for (RaterPerson rater : raterData){
            int count = rater.findNumberOfRatings();
            if (count == max){
                freq++;
                topRaters.add(rater.getRaterPersonID());
            }

            if (count > max){
                topRaters.clear();
                topRaters.add(rater.getRaterPersonID());
                max = count;
            }
        }
        System.out.println("The most active rater is/are rater(s): " + topRaters + " with " + max + " ratings.");

        //Part 3
        System.out.println("\nPart 3");
        String movie_id = "68646";
        int ratings = 0;
        for (RaterPerson rater : raterData){
            if (rater.hasRatingValue(movie_id) == true) {ratings++;}
        }
        System.out.println(movie_id + " has " + ratings + " ratings");


        //Part 4
        System.out.println("\nPart 4");
        RaterPerson top = getRater(topRaters.get(0), raterData);
        System.out.println("Rater " + top.getRaterPersonID() + " has rated the following movies: ");
        System.out.println(top.getMoviesRated());

    }



    public static void main(String[] args){
        TestMovieRatings movieRatings = new TestMovieRatings();

        // movieRatings.testLoadMovieData();
        // movieRatings.testLoadRaterPerson();
        movieRatings.findandDisplayMovies();
        // movieRatings.findandDisplayRaters();
    }

}


