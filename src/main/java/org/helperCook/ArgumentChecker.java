package org.helperCook;

public class ArgumentChecker {
    public static int checkList(String[] args, String cmpString) { // check to see if the argument is equal to the cmpString
        boolean foundFlag = false; // flag to check if the argument is in the array only once
        int position = -1; // position of the argument in the array
        for (int i = 0; i < args.length; i++) {
            if ( args[i].equals( cmpString ) ) {
                System.out.println("Found at " + i);
                if (foundFlag) {
                    return -1; // returns -1 if the argument is in the array more than once
                }
                foundFlag = true;
                position = i;
            }
        }
        return position; // returns -1 if the argument is not in the array
    }
}
