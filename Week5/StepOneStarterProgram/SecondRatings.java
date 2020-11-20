
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingFile);
        
        
    }
    // returns the number of movies
    public int getMovieSize() {
        return myMovies.size();
        
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
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        // for each movie 
        for (Movie m : myMovies) {
            String ID = m.getID();
            Double avg =getAverageByID(ID, minimalRaters);
            // check whether the movie has minimal raters
            if (avg != 0.0) {
                Rating rating = new Rating(ID, avg);
                avgRatings.add(rating);
                
            }
            
        }
        
        return avgRatings;
    }
    
    // returns the title of the movie with the movie id
    public String getTitle (String id) {
        String title = "";
        // for each movie
        for (Movie m : myMovies) {
            // check the id of the movie
            if (m.getID().equals(id)) {
                title = m.getTitle();
                
            }
            
        }
        // check if the title exist
        if (! title.equals("")) {
            return title;
            
        }
        else {
            return "No movie with the id is found";
        }
    }
    
    // returns the id of the movie with given title
    public String getID (String title) {
        String id = "";
        // for each movie 
        for (Movie m: myMovies) {
            if (m.getTitle().equals(title)) {
                id = m.getID();
            }
            
        }
        
        if (! id.equals("")) {
            return id;
            
        }
        else {
            return "No movie with the title is found";
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
