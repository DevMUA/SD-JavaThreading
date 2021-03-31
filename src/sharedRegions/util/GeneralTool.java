package sharedRegions.util;

public class GeneralTool {

    public static int getRandomNumber(int min,int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
