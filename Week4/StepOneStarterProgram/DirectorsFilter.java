
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    String directors;
    
    public DirectorsFilter (String dirc) {
        directors = dirc;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] director = directors.split(",");
        for (int i=0; i < director.length; i++) {
            if (MovieDatabase.getDirector(id).indexOf(director[i]) != -1) {
                return true;
            }
        }
        return false;
    }

}
