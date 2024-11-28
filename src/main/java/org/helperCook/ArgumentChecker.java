package org.helperCook;

public class ArgumentChecker {
    public static int checkList(String[] args, String cmpString) { // check to see if the argument is equal to the cmpString

        int position = -1; // position of the argument in the array
        for (int i = 0; i < args.length; i++) {
            if ( args[i].equals( cmpString ) ) {
                System.out.println( "Found at " + i );
                if ( position != -1 )
                    return -1; // returns -1 if the argument is in the array more than once
                position = i;
            }
        }
        return position; // returns -1 if the argument is not in the array
    }
}
