import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Map;
import edu.ConnColl.SoftwareEngineering.MyFileReader;
import org.apache.commons.csv.CSVParser;  
import org.apache.commons.csv.CSVRecord; 

public class TestMovieRatings_2 {

	private ArrayList<MovieInformation> listOfMovies;

	private ArrayList<RaterPerson> listOfRaterPersons;

	//constructor that takes two arguments of type String
	public TestMovieRatings_2(String movieInformationFile, String movieRatingInfoFile ){

		TestMovieRatings tester = new TestMovieRatings();
		this.listOfMovies = tester.loadMovieData(movieInformationFile);
		this.listOfRaterPersons = tester.loadRaterPersonData(movieRatingInfoFile);

		//System.out.println(this.listOfMovies);
		System.out.println(this.listOfRaterPersons);

	}

	//default constructor that is chained to the above constructor
	public TestMovieRatings_2(){
		this("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
	}

	public int findNumberOfMovies(){
		return listOfMovies.size();
	}

	public int findNumberOfRaterPersons(){
		return listOfRaterPersons.size();
	}

	//Gets the amount of times a move has been rated
    public int amountRatingsByMovie(String id, ArrayList<RaterPerson> raterData){
        int ratings = 0;
        for (RaterPerson rater : raterData){
            if (rater.hasRatingValue(id) == true) {ratings++;}
        }
        return ratings;
    }

	public double findIDBasedAverage(String movieID, int minimumNumOfRaterPersons){
		if (amountRatingsByMovie(movieID, listOfRaterPersons) < minimumNumOfRaterPersons){
			//System.out.println("Not enough rater persons to calculate the arithmetic mean");
			return -1.0;
		}
		else{
			double total = 0;
			double tally = 0;
			for (RaterPerson rater : this.listOfRaterPersons){
				if (rater.hasRatingValue(movieID)){
					total += rater.getRatingValues(movieID);
					tally += 1;
					//System.out.println(total);
				}
			}
			return total / tally;
		}

	}

	public HashMap findRatingsAverage(int minimumNumberOfRaterPersons){
		HashMap<String, Double> ratingsAverage = new HashMap<>();
		for (MovieInformation movie : this.listOfMovies){
			ratingsAverage.put(movie.getMovieID(), findIDBasedAverage(movie.getMovieID(), minimumNumberOfRaterPersons));
		}
		//System.out.println(ratingsAverage);
		return ratingsAverage;
	}

	public String getMovieTitle(String movieID){
		for (MovieInformation movie : this.listOfMovies){
			//System.out.println(movie.getMovieID());
			if (movie.getMovieID().equals(movieID)){
				return movie.getMovieTitle();
			}
		}
		
		return null;
	}

	public String getMovieID(String movieTitle){
		for (MovieInformation movie : this.listOfMovies){
			//System.out.println(movie.getMovieTitle());
			if (movie.getMovieTitle().equals(movieTitle)){
				return movie.getMovieID();
			}
		}
		return null;
	}

public static void main(String[] args){
	TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
	System.out.println("Number of movies is: " + movieRatings2.findNumberOfMovies());
	System.out.println("Number of rater persons is: " + movieRatings2.findNumberOfRaterPersons());
	System.out.println(movieRatings2.findIDBasedAverage("68646", 5));
	System.out.println(movieRatings2.findRatingsAverage(0));
	System.out.println(movieRatings2.getMovieTitle("1798709"));
	System.out.println(movieRatings2.getMovieID("Her"));


}




}