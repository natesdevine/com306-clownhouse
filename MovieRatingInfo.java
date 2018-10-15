// This class implements the Comparable interface 
// so that two objects of MovieRatingInfo type can be compared
public class MovieRatingInfo implements Comparable<MovieRatingInfo> {
    private String ratedMovie;
    private double ratingValue;


    public MovieRatingInfo(String someMovie, double someValue) {
        ratedMovie = someMovie;
        ratingValue = someValue;
    }

    public String getRatedMovie() {
        return ratedMovie;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    //Override the toString() method
    @Override
    public String toString () {
        return "((( Rated Movie: " + ratedMovie + "; Rating Value" + ratingValue + " )))";
    }
    
    //Override the compareTo() method
    @Override
    public int compareTo(MovieRatingInfo otherRating) {
        if (ratingValue < otherRating.ratingValue) return -1;
        if (ratingValue > otherRating.ratingValue) return 1;        
        return 0;
    }
}
