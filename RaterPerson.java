import java.util.ArrayList;

public class RaterPerson {
    private String raterPersonID;
    private ArrayList<MovieRatingInfo> raterPersonRatings;
    

    public RaterPerson(String personID) {
        raterPersonID = personID;        
        raterPersonRatings = new ArrayList<MovieRatingInfo>();
    }
    
    public String getRaterPersonID() {
        return raterPersonID;
    }
    
    public int findNumberOfRatings() {
        return raterPersonRatings.size();
    }
    
    public void addRating(String movie, double ratingValue) {
        raterPersonRatings.add(new MovieRatingInfo(movie,ratingValue));
    }

    // checks to see if a movie rating value exists for this person 
    // this method iterates over all of this person's rating values
    // to see if a rating exists for the movie passed to the method
    public boolean hasRatingValue(String movie) {
        for(int i = 0; i < raterPersonRatings.size(); i++){
            if (raterPersonRatings.get(i).getRatedMovie().equals(movie)){
                return true;
            }
        }        
        return false;
    }
    
    // checks to see if a rating has been done by this person
    // for the movie passed to the method and if so, returns the rating value
    // this method iterates over all of this person's rating values
    public double getRatingValues(String movie) {
        for(int i = 0; i < raterPersonRatings.size(); i++){
            if (raterPersonRatings.get(i).getRatedMovie().equals(movie)){
                return raterPersonRatings.get(i).getRatingValue();
            }
        }        
        return -1;
    }    
    
    // finds and returns all of this person's rating values in an array list
    // this method iterates over all of this person's rating values
    public ArrayList<String> getMoviesRated() {
        ArrayList<String> movieList = new ArrayList<String>();
        for(int i = 0; i < raterPersonRatings.size(); i++){
            movieList.add(raterPersonRatings.get(i).getRatedMovie());
        }        
        return movieList;
    }
}
