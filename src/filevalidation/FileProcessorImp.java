
package filevalidation;

import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class FileProcessorImp implements FileProcessor{

    @Override
    public List<String> readFromFile(File inputFile) {
            List<String> list = new ArrayList<>(); 
            
            try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
                String line = reader.readLine(); 
                while(line != null) {
                    list.add(line); 
                    line = reader.readLine();
                }
            } catch(IOException ioe) {
                System.out.println("Error occuredwhile reading the file: " + ioe);
            }
            
            return list;
    }

    @Override
    public void writeToFile(File outputFile, List<String> list) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            int lineNum = 0; 
            for(String line : list){
                if(lineNum++ > 0){
                    writer.newLine();
                } 
                writer.write(line);
            }
        } catch(IOException ioe) {
            System.out.println("Error occured while writting to the file: " + ioe);
        }
    }
    
}
