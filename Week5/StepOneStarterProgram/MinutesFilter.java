
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int min;
    private int max;
    
    public MinutesFilter (int minM, int maxM) {
        min = minM;
        max = maxM;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
    

}
