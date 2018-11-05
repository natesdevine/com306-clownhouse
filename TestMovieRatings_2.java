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
	}

	//default constructor that is chained to the above constructor
	public TestMovieRatings_2(){
		this("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
	}

	//returns the number of movies stored in the listOfMovies field
	public int findNumberOfMovies(){
		return listOfMovies.size();
	}

	//test method for findNumberOfMovies()
	public void testFindNumberOfMovies(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println("Number of movies is: " + movieRatings2.findNumberOfMovies());
	}

	//returns the number of rater persons stored in the listOfRaterPersons field
	public int findNumberOfRaterPersons(){
		return listOfRaterPersons.size();
	}

	//test method for findNumberOfRaterPersons()
	public void testFindNumberOfRaterPersons(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println("Number of rater persons is: " + movieRatings2.findNumberOfRaterPersons());
	}

	//Gets the amount of times a move has been rated
	//This is a ported method from TestMovieRatings
    public int amountRatingsByMovie(String id, ArrayList<RaterPerson> raterData){
        int ratings = 0;
        for (RaterPerson rater : raterData){
            if (rater.hasRatingValue(id) == true) {ratings++;}
        }
        return ratings;
    }

    /*Calculates the mean of movie ratings for a given movie ID that has been rated by the
    minimum number of rater persons and returns the result*/
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
				}
			}
			return total / tally;
		}

	}

	//test method for findIDBasedAverage()
	public void testFindIDBasedAverage(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println(movieRatings2.findIDBasedAverage("68646", 5));
	}


	/*Calculates the mean of those movies rated by the minimum number of rater persons 
	and returns a dictionary (hash map) containing the result*/
	public HashMap findRatingsAverage(int minimumNumberOfRaterPersons){
		HashMap<String, Double> ratingsAverage = new HashMap<>();
		for (MovieInformation movie : this.listOfMovies){
			ratingsAverage.put(movie.getMovieID(), findIDBasedAverage(movie.getMovieID(), minimumNumberOfRaterPersons));
		}
		return ratingsAverage;
	}

	//test method for findRatingsAverage()
	public void testFindRatingsAverage(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println(movieRatings2.findRatingsAverage(0));
	}

	//Uses the movie ID to get the title
	public String getMovieTitle(String movieID){
		for (MovieInformation movie : this.listOfMovies){
			if (movie.getMovieID().equals(movieID)){
				return movie.getMovieTitle();
			}
		}
		
		return null;
	}

	//test method for getMovieTitle()
	public void testGetMovieTitle(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println(movieRatings2.getMovieTitle("1798709"));
	}


	//uses the movie title to get the ID
	public String getMovieID(String movieTitle){
		for (MovieInformation movie : this.listOfMovies){
			if (movie.getMovieTitle().equals(movieTitle)){
				return movie.getMovieID();
			}
		}
		return null;
	}

	//test method for getMovieID()
	public void testGetMovieID(){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		System.out.println(movieRatings2.getMovieID("Her"));
	}

	public static void main(String[] args){
		TestMovieRatings_2 movieRatings2 = new TestMovieRatings_2("data/Rated-Movies-for-Testing.csv", "data/Movie-Ratings-for-Testing.csv");
		movieRatings2.testFindNumberOfMovies();
		movieRatings2.testFindNumberOfRaterPersons();
		movieRatings2.testFindIDBasedAverage();
		movieRatings2.testFindRatingsAverage();
		movieRatings2.testGetMovieTitle();
		movieRatings2.testGetMovieID();
	}

}