
package filevalidation;


public interface Validator {
    public boolean isAlpha(String dataToCheck); 
    public boolean isDigit(String dataTocheck); 
    public boolean isASCII(String dataToCheck);
}
