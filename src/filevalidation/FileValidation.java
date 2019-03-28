
package filevalidation;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


public class FileValidation implements Validator{
    
    @Override 
    public boolean isAlpha(String dataToCheck){
        String pattern = "^(?=.*[a-z])(?=.*[A-Z]).+$"; 
        return checkRegex(pattern, dataToCheck);
    } 
    
    @Override 
    public boolean isDigit(String dataToCheck){
        String pattern = "(.)*(\\d)(.)*"; 
        return checkRegex(pattern, dataToCheck);
    } 
    
    @Override 
    public boolean isASCII(String dataToCheck){
        String pattern = ".*"; 
        return checkRegex(pattern, dataToCheck);
    }
    
    public boolean checkRegex(String pattern, String matcher){ 
        boolean result = false;
        Pattern p = Pattern.compile(pattern); 
        Matcher m = p.matcher(matcher); 
        if(m.find()){
            result = true;
        } 
        return result;
    } 
    public boolean condition(String row){
        boolean result = false; 
        if(isAlpha(row) && isDigit(row) && isASCII(row)){
            result = true;
        }
        
        return result;
    } 
    public List<String> validation(List<String> list){
        List<String> outputList = list.stream() 
            .map(e -> condition(e)?e+"VALID":e+"INVALID")
            .collect(toList()); 
        return outputList;
    }

    public static void main(String[] args) {
        File inputFile = new File("FileToRead.txt"); 
        FileProcessorImp fileObj = new FileProcessorImp(); 
        FileValidation mainObj = new FileValidation();
        
        List<String> list = new ArrayList<>(); 
        list.addAll(fileObj.readFromFile(inputFile)); 
        
        File outputFile = new File("ValidationResults.txt");
        fileObj.writeToFile(outputFile, mainObj.validation(list)); 
        
        List<State> stateList = list.stream() 
                                    .map(e -> {
                                        State state = new State(); 
                                        state.setName(e); 
                                        state.setCity(e); 
                                        state.setPopulation(e); 
                                        return state;
                                    })
                                    .collect(toList());  
       
        Map<String, List<State>> stateByName = stateList.stream()
                                                        .collect(groupingBy(State::getName)); 
        
        for(Map.Entry<String, List<State>> entry : stateByName.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue()); 
            System.out.println("-------------------------------------------------------------");
        }
       
        
        
    }
    
}
