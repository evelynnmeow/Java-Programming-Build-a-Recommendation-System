
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings (String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings ();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    // returns the number of raters
    public int getRaterSize() {
        return myRaters.size();
        
    }
    // get average rating by each rater id
    private double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double total = 0.0;
        
        
        // for each rater
        for (Rater rater : myRaters) {
            if (rater.hasRating(id)) {
                total += rater.getRating(id);
                count += 1;
                
            }
            
        }
        if (count >= minimalRaters) {
            return total / count;
            
        }
        else {
            return 0.0;
        }
        
        
    }
    
    // get average ratings for each movie
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
         ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        
        for (String movieID : movies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        for (String movieID : filteredMovies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
    
    
    
    
    

}
