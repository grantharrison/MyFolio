/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gthgg6finalproject;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author grantharrison
 */
public class Stock implements java.io.Serializable {
    private String stock1 = "";
    private String stock2 = "";
    private String stock3 = "";
    private String stock1notes = "";
    private String stock2notes = "";
    private String stock3notes = "";
    
    
    public Stock() {
        
    }
    
    public void setStock1(String stock1) {
        this.stock1 = stock1;
    }
    
    public void setStock2(String stock2) {
        this.stock2 = stock2;
    }
    
    public void setStock3(String stock3) {
        this.stock3 = stock3;
    }
    
    public void setStock1Notes(String stock1notes) {
        this.stock1notes = stock1notes;
    }
    
    public void setStock2Notes(String stock2notes) {
        this.stock2notes = stock2notes;
    }
    
    public void setStock3Notes(String stock3notes) {
        this.stock3notes = stock3notes;
    }
    
    public String getStock1() {
        return stock1;
    }
    
    public String getStock2() {
        return stock2;
    }
    
    public String getStock3() {
        return stock3;
    }
    
    public String getStock1Notes() {
        return stock1notes;
    }
    
    public String getStock2Notes() {
        return stock2notes;
    }
    
    public String getStock3Notes() {
        return stock3notes;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Stock)) {
            return false;
        }
        
        Stock other = (Stock) obj;
        
        if (stock1 == null) {
            if (!(other.getStock1() == null)) {
                return false;
            }
        }
        else if (!stock1.equals(other.getStock1())) {
            return false;
        }
        
        if (stock2 == null) {
            if (!(other.getStock2() == null)) {
                return false;
            }
        }
        else if (!stock2.equals(other.getStock2())) {
            return false;
        }
        
        if (stock3 == null) {
            if (!(other.getStock3() == null)) {
                return false;
            }
        }
        else if (!stock3.equals(other.getStock3())) {
            return false;
        }
        
        if (stock1notes == null) {
            if (!(other.getStock1Notes() == null)) {
                return false;
            }
        }
        else if (!stock1notes.equals(other.getStock1Notes())) {
            return false;
        }
        
        if (stock2notes == null) {
            if (!(other.getStock2Notes() == null)) {
                return false;
            }
        }
        else if (!stock2notes.equals(other.getStock2Notes())) {
            return false;
        }
        
        if (stock3notes == null) {
            if (!(other.getStock3Notes() == null)) {
                return false;
            }
        }
        else if (!stock3notes.equals(other.getStock3Notes())) {
            return false;
        }
        
        return true;
    }
    
    public String toJsonString() {
        JSONObject obj=new JSONObject();
        if (stock1 != null) obj.put("stock1", stock1);
        if (stock2 != null)  obj.put("stock2", stock2);
        if (stock3 != null) obj.put("stock3", stock3);
        if (stock1notes != null)  obj.put("stock1notes", stock1notes);
        if (stock2notes != null)  obj.put("stock2notes", stock2notes);
        if (stock3notes != null)  obj.put("stock3notes", stock3notes);
        
        return obj.toJSONString(); 
    }
    
    public void initFromJsonString(String jsonString) {
        stock1 = "";
        stock2 = "";
        stock3 = "";
        stock1notes = "";
        stock2notes = "";
        stock3notes = "";
       
        
        if (jsonString == null || jsonString == "") return;
        
        JSONObject jsonObj;
        try {
            jsonObj = (JSONObject)JSONValue.parse(jsonString);
        } catch (Exception ex) {
            return;
        }
        
        if (jsonObj == null) {
            return;
        }
        
        stock1 = (String)jsonObj.getOrDefault("stock1", "");
        stock2 = (String)jsonObj.getOrDefault("stock2", "");
        stock3 = (String)jsonObj.getOrDefault("stock3", "");
        stock1notes = (String)jsonObj.getOrDefault("stock1notes", "");
        stock2notes = (String)jsonObj.getOrDefault("stock2notes", "");
        stock3notes = (String)jsonObj.getOrDefault("stock3notes", "");
        
    }
}
