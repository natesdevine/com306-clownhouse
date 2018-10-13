
/**
 * This class captures and encapsulate information about
 * movie ratings based on the CSV data file that has movie ratings information.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

// This class implements the Comparable interface 
// so that two objects of MovieRatingInfo type can be compared
public class MovieRatingInfo implements Comparable<MovieRatingInfo> {
    // private data (instance) fields
    private String ratedMovie;
    private double ratingValue;

    /**
     * 2-argument constructor for objects of class MovieRatingInfo
     */
    public MovieRatingInfo(String someMovie, double someValue) {
        ratedMovie = someMovie;
        ratingValue = someValue;
    }

    // getter method for ratedMovie
    public String getRatedMovie() {
        return ratedMovie;
    }

    // getter method for ratingValue
    public double getRatingValue() {
        return ratingValue;
    }

    /**
     * Override the toString() method
     */
    @Override
    public String toString () {
        return "((( Rated Movie: " + ratedMovie + "; Rating Value" + ratingValue + " )))";
    }
    
    /**
     * Override the compareTo() method
     */
    // Note: otherRating is an object of this class, so we can use the private fields 
    // we could also use the getter methods, but it's not necessary and methods calls
    // add extra overhead
    @Override
    public int compareTo(MovieRatingInfo otherRating) {
        if (ratingValue < otherRating.ratingValue) return -1;
        if (ratingValue > otherRating.ratingValue) return 1;        
        return 0;
    }
}
