package common;

/**
 * The type General tool.
 */
public class GeneralTool {

    /**
     * Get random number int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int getRandomNumber(int min,int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

}
