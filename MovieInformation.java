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
    

    public MovieInformation(String someID, String someTitle, String someYear, String someGenre) {        
        movieID = someID;
        movieTitle = someTitle;
        movieYear = Integer.parseInt(someYear);
        movieGenre = someGenre;
    }

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

    public String getMovieID () {
        return movieID;
    }

    public String getMovieTitle () {
        return movieTitle;
    }

    public int getMovieYear () {
        return movieYear;
    }

    public String getMovieGenre () {
        return movieGenre;
    }
    
    public String getMovieCountry(){
        return movieCountry;
    }

    public String getMovieDirector(){
        return movieDirector;
    }
    
    public String getPosterURL(){
        return posterURL;
    }
    
    public int getMovieLength(){
        return movieLength;
    }

    // Override the toString() method
    @Override
    public String toString() {
        String message = "((( Movie ID: " + movieID + "; Movie Title: " + movieTitle; 
        message += "; Movie Year: " + movieYear + "; Movie Genre(s): " + movieGenre + " )))";        
        return message;
    }
}
