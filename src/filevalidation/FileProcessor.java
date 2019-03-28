
package filevalidation;

import java.io.File;
import java.util.List;

public interface FileProcessor {
    public List<String> readFromFile(File inputFile); 
    public void writeToFile(File outputFile, List<String> list);
}
