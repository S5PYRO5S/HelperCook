package org.helperCook;

public class ArgumentChecker {
    private final String[] args;
    private final String cmpString;
    public ArgumentChecker(String[] args, String value) {
        this.args = args;
        cmpString= value;
    }
    public int checkList(){ // check to see if the argument is equal to the cmpString
        for (int i=0; i<args.length; i++) {
            if ( args[i].equals( cmpString ) ) {
                return i; // returns the index of the argument in the array
            }
        }
        return -1; // returns -1 if the argument is not in the array
    }
}
