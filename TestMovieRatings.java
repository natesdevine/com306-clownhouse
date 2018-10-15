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
            if (RaterIDExists(id, raterData) == true) {
                RaterPerson person = getRaterObject(id, raterData);
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


    public void findandDisplayMovies(){
        String filePath = "data/Rated-Movies-for-Testing.csv";
        ArrayList<MovieInformation> movieData = loadMovieData(filePath);

        //length
        int length = 135;
        GreaterThanLength(length, movieData);

        //directors
        mostProductiveDirectors(movieData);

        //drama
        String genre = "Drama";
        MoviesInGenre(genre, movieData);
    }

    public void mostProductiveDirectors(ArrayList<MovieInformation> movieData){
        HashMap<String, Integer> directors = new HashMap<>();
        Map.Entry<String,Integer> maxEntry = null;

        //generates hashmap counting the amount of movies each director directed
        for (MovieInformation movie : movieData){
            String director = movie.getMovieDirector();

            //if director is in hashmap, then increment count
            if (directors.containsKey(director)) {directors.put(director, directors.get(director) + 1);}
            
            //if director isn't in hashmap, then add director
            else {directors.put(director, 1);}
        }

        //finds the most prolific director from hashmap
        for(Map.Entry<String,Integer> entry : directors.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        //print result
        System.out.println("From the dataset, the most prolific director is " + maxEntry.getKey() + " with " + maxEntry.getValue() + " movie produced.");
    }

    public void GreaterThanLength(Integer length, ArrayList<MovieInformation> movieData){
        for (MovieInformation movie : movieData){
            if (movie.getMovieLength() > length) {System.out.println(movie.toString());}
        }
    }

    public void MoviesInGenre(String genre, ArrayList<MovieInformation> movieData){
        Integer count = 0;

        for (MovieInformation movie : movieData){
            if (movie.getMovieGenre().contains(genre)) {count++;}
        }
        System.out.println("There are " + count + " movies in the " + genre + " genre.");
    }

    public void amountRatingsbyPerson(String id, ArrayList<RaterPerson> raterData){
        System.out.println("Rater " + id + " has rated " + getRaterObject(id, raterData).findNumberOfRatings() + " movies.");
    }

    public ArrayList<String> mostRatings(ArrayList<RaterPerson> raterData){
        int max = 0; 
        ArrayList<String> topRaters = new ArrayList<String>();

        for (RaterPerson rater : raterData){
            int count = rater.findNumberOfRatings();
            
            if (count == max){
                topRaters.add(rater.getRaterPersonID());
            }

            if (count > max){
                topRaters.clear();
                topRaters.add(rater.getRaterPersonID());
                max = count;
            }
        }
        if (topRaters.size() == 1){
            System.out.println("The most active rater is " + topRaters + " with " + max + " ratings.");
            return topRaters; 
        }
        else{
            System.out.println("The most active raters are: " + topRaters + " with " + max + " ratings.");   
            return topRaters; 
        }
    }

    public void amountRatingsbyMovie(String id, ArrayList<RaterPerson> raterData){
        int ratings = 0;
        for (RaterPerson rater : raterData){
            if (rater.hasRatingValue(id) == true) {ratings++;}
        }
        System.out.println("Movie " + id + " has " + ratings + " ratings.");
    }

    public void findandDisplayRaters(){
        String filePath = "data/Movie-Ratings-for-Testing.csv";
        ArrayList<RaterPerson> raterData = loadRaterPersonData(filePath);

        //Part 1
        String rater_id = "2";
        amountRatingsbyPerson(rater_id, raterData);

        //Part 2
        ArrayList<String> topRaters = mostRatings(raterData);
        

        //Part 3
        String movie_id = "68646";
        amountRatingsbyMovie(movie_id, raterData);


        //Part 4
        RaterPerson top = getRaterObject(topRaters.get(0), raterData);
        System.out.println("Rater " + top.getRaterPersonID() + " has rated the following movies: " + top.getMoviesRated());

    }

    public boolean RaterIDExists(String id, ArrayList<RaterPerson> raterData){
        for (RaterPerson person : raterData){
            if (person.getRaterPersonID().equals(id)) {return true;}   
        }
        return false;
    }

    public RaterPerson getRaterObject(String id, ArrayList<RaterPerson> raterData){
        for (RaterPerson person : raterData){
            if (person.getRaterPersonID().equals(id)) {return person;}
        } 
        return null;       
    }

    public static void main(String[] args){
        TestMovieRatings movieRatings = new TestMovieRatings();

        movieRatings.testLoadMovieData();
        System.out.println();
        movieRatings.testLoadRaterPerson();
        System.out.println();
        movieRatings.findandDisplayMovies();
        System.out.println();
        movieRatings.findandDisplayRaters();
    }

}


