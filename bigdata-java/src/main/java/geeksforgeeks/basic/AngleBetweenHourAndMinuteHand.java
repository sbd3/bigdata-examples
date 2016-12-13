package geeksforgeeks.basic;

import java.util.Scanner;

public class AngleBetweenHourAndMinuteHand {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            float hr = scan.nextFloat();
            float min = scan.nextFloat();
            printAngle(hr, min);
        }
        scan.close();
    }

    private static void printAngle(float hr, float min) {
        if(min == 60) {
            min = 0;
        }
        float angle = Math.abs(getHourAngle(hr, min) - getMinuteAngle(min));
        System.out.println((int) Math.floor(Math.min(angle, 360-angle)));
    }
    
    private static float getHourAngle(float hr, float min) {
        float hours = hr + min / 60;
        return 30 * hours;
    }
    
    private static float getMinuteAngle(float min) {
        return 6 * min;
    }

}
