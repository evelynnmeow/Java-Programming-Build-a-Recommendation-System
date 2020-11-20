
/**
 * Write a description of FirstRatings here.
 * 
 * @author (Jingjie M.) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String id = i.get("id");
            String title = i.get("title");
            String year = i.get("year");
            String country = i.get("country");
            String genre = i.get("genre");
            String director = i.get("director");
            int minutes = Integer.parseInt(i.get("minutes"));
            String poster = i.get("poster");
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of movies " + movies.size());
        int comedyCount = 0;
        int maxTime = 0;
        for (Movie i : movies) {
            if (i.getGenres().contains("Comedy")) {
                comedyCount += 1;
            }
            
            
        }
        System.out.println("Number of Comedies: " + comedyCount);
        for (Movie i : movies) {
            if (i.getMinutes() >= maxTime) {
                maxTime = i.getMinutes();
            }
            
            
        }
        System.out.println("Max time: " + maxTime);
        int timeThred = 150;
        int longMovies = 0;
        for (Movie i : movies) {
            if (i.getMinutes() > 150) {
                longMovies += 1;
            }
        }
        System.out.println("The number of long movies are : " + longMovies);
                
            
        
        
        // mao between directors and their movies
        HashMap <String, ArrayList<Movie>> directorsMap = new HashMap<>();
        for (Movie i : movies) {
            String director = i.getDirector();
            String[] directors = director.split(", ");
            for (String k : directors) {
                if (!directorsMap.containsKey(k)) {
                    ArrayList<Movie> movie = new ArrayList<>();
                    movie.add(i);
                    directorsMap.put(k, movie);
                }
                
                else{
                    ArrayList<Movie> movie = directorsMap.get(k);
                    movie.add(i);
                    directorsMap.put(k, movie);
                }
            
            
        }
        
            
        
    }
    
    // number of movies of the director who produces the most number of movies
    int maxNumOfMovies = 0;
    // the number of the director
    String maxNumDirector = "";
    // loop through the hashMap
    for (String director : directorsMap.keySet()) {
        int currentNum = directorsMap.get(director).size();
        if(currentNum > maxNumOfMovies) {
            maxNumOfMovies = currentNum;
            maxNumDirector = director;
        }
        
    }
    
    System.out.println(maxNumDirector + " produces the most movies: " + maxNumOfMovies);
    
}
public ArrayList<Rater> loadRaters(String fileName) {
    
        ArrayList<Rater> raters = new ArrayList<Rater> ();
        ArrayList<String> raterIDs = new ArrayList<String> ();
        
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            
            if (! raterIDs.contains(currentRaterID)) {
                Rater currentRater = new EfficientRater(currentRaterID);
                raters.add(currentRater);
                currentRater.addRating(currentMovieID, currentMovieRating);
            
            } else {
                for (int k=0; k < raters.size(); k++) {
                    if (raters.get(k).getID().equals(currentRaterID)) {
                        raters.get(k).addRating(currentMovieID, currentMovieRating);
                    }
                }
            }
            
            raterIDs.add(currentRaterID);
        }
        
        return raters;
    }

public void testLoadRaters() {
     ArrayList<Rater> raters = loadRaters("data/ratings.csv");
     // int raterSize = raters.size();
     // print out the size of the rater
     System.out.println("The size of the rater is " + raters.size());
     // find out ratings for a particular rater
     for (Rater rater : raters) {
         // if the id of the rater is 2
         String id = "193";
         if (rater.getID().equals(id)) {
             int ratings = rater.numRatings();
             System.out.println("Rater id is " + id + " has number of ratings " + ratings);
             
             
         }
         
     }
     
     // find out the max number of ratings by any rater
     // determine the number of max ratings and its rater
     int max = 0;
     String maxID = "";
     // loop through each rater
     for (Rater rater : raters) {
         if (rater.numRatings() > max) {
             max = rater.numRatings();
             maxID = rater.getID();
         }
         
     }
     System.out.println("The max number of rating is " + max + " and it is from ID " + maxID);
     
     
     // find out number of ratings a particular movie has
     // id of a particular movie
     String movieID = "1798709";
     int numRatings = 0;
     int count = 0;
     // loop through each rater
     for (Rater rater : raters) {
         ArrayList<String> ratings = rater.getItemsRated();
         if (ratings.contains(movieID)) {
             
             count++;
             
         }
         
         
     }
     
     System.out.println("The number of ratings for movie ID " + movieID + " is " + count);
    
     // determine how many different movies rated for all raters
     // arrayList that store unique id of movies
     ArrayList<String> uniqIDs = new ArrayList<>();
     for (Rater rater : raters) {
         ArrayList<String> ratings = rater.getItemsRated();
         for (String rating :ratings) {
             if(!uniqIDs.contains(rating)) {
                 uniqIDs.add(rating);
                 
             }
             
         }
         
         
     }
     System.out.println("The total number of different movies is " + uniqIDs.size());
     
    
}
}




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   


