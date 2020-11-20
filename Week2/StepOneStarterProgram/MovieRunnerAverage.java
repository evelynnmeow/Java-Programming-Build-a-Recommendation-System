
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (Jingjie M.) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings () {
        SecondRatings ratings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        // print out the total number of movies
        System.out.println("The total number of movies is " + ratings.getMovieSize());
        // print out the total number of raters
        System.out.println("The total number of raters is " + ratings.getRaterSize());
        // define the min number of ratings
        int minRatings = 12;
        ArrayList<Rating> avgRating = ratings.getAverageRatings(minRatings);
        Collections.sort(avgRating);
        for (Rating r: avgRating) {
            System.out.println(r.getValue() + " " + ratings.getTitle(r.getItem()));
            
        }
        System.out.println("There are " + avgRating.size() + " movies with " +
        minRatings + " or more ratings");
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings ratings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String title = "Vacation";
        int minRating = 0;
        String ID = ratings.getID(title);
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(minRating);
        for (Rating r : avgRatings) {
            if (r.getItem().equals(ID)) {
                System.out.println("The movie " + title + " the average rating is " 
                + r.getValue());
            }
        }
        
    }
    

}
