/**
 * This class captures and encapsulate information about
 * movies based on the CSV data file that has movie information.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MovieInformation {
    
    // private data (instance) fields
    private String movieID;
    private String movieTitle;
    private int movieYear;
    private String movieGenre;
    private String movieDirector;
    private String movieCountry;
    private String posterURL;
    private int movieLength;   // in minutes
    
    /**
     * 4-argument constructor for objects of class MovieInformation
     */
    public MovieInformation(String someID, String someTitle, String someYear, String someGenre) {        
        movieID = someID;
        movieTitle = someTitle;
        movieYear = Integer.parseInt(someYear);
        movieGenre = someGenre;
    }

    /**
     * 8-argument constructor for objects of class MovieInformation
     */
    public MovieInformation (String someID, String someTitle, String someYear, String someGenre,
                     String someDirector, String someCountry, String someURL, int someLength) {        
        movieID = someID;
        movieTitle = someTitle;
        movieYear = Integer.parseInt(someYear);
        movieGenre = someGenre;
        movieDirector = someDirector;
        movieCountry = someCountry;
        posterURL = someURL;
        movieLength = someLength;
    }

    // getter method for movieID
    public String getMovieID () {
        return movieID;
    }

    // getter method for movieTitle
    public String getMovieTitle () {
        return movieTitle;
    }

    // getter method for movieYear
    public int getMovieYear () {
        return movieYear;
    }

    // getter method for movieGenre
    public String getMovieGenre () {
        return movieGenre;
    }
    
    // getter method for movieCountry
    public String getMovieCountry(){
        return movieCountry;
    }

    // getter method for movieDirector
    public String getMovieDirector(){
        return movieDirector;
    }
    
    // getter method for posterURL
    public String getPosterURL(){
        return posterURL;
    }
    
    // getter method for movieLength
    public int getMovieLength(){
        return movieLength;
    }

    /**
     * Override the toString() method
     */
    @Override
    public String toString() {
        String message = "((( Movie ID: " + movieID + "; Movie Title: " + movieTitle; 
        message += "; Movie Year: " + movieYear + "; Movie Genre(s): " + movieGenre + " )))";        
        return message;
    }
}
