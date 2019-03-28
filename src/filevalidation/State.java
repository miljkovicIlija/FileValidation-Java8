
package filevalidation;

import java.util.regex.*;


public class State {
    String name; 
    String city; 
    int population; 
    
    public void setName(String name){
        String stateName = findAttribute("^(.*?);", name); 
        this.name = stateName.toUpperCase();
    }  
    public String getName(){ return name;} 
    
    public void setCity(String city){
        String stateCity = findAttribute(";(.*?)=", city); 
        this.city = stateCity;
    } 
    public String getCity(){ return city;}
    
    public void setPopulation(String pop){
        String statePop = findAttribute("=(.*?);",pop); 
        statePop = statePop.replaceAll("[^\\d.]", "");
        this.population = Integer.parseInt(statePop);
    } 
    public int getPop(){ return population;}
    
    public String findAttribute(String pattern, String rowToCheck){
        String attribute = ""; 
        Pattern p = Pattern.compile(pattern); 
        Matcher m = p.matcher(rowToCheck);
        if(m.find()){
            attribute = m.group(1);
        } 
        return attribute;
    } 
    
    @Override 
    public String toString(){
        return getCity() + " (" + getPop() + ")";
    }
}
