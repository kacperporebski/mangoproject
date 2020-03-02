package model;

import java.util.UUID;

public class UserModel {
    private UUID userID;
    private String username; 
    private String password; 
    
    //Constructor. Generates a random userID
    public UserModel(String username,String password) {
    	this.username = username;
    	this.password = password;
    	userID = UUID.randomUUID();
    }

//    //Constructor. Assigns the userID
//    public UserModel(String username,String password, UUID userID)
//    {
//        this.username = username;
//        this.password = password;
//        this.userID = userID;
//    }

    //Getters and setters
    public String getUsername()  { return username; }
    public void setUsername(String username)  {
        this.username = username; 
    }

    public String getPassword()  {
        return password; 
    }
    public void setPassword(String password)  {
        this.password = password; 
    }

    public UUID getId()
    {
        return userID;
    }
    public void setUserID(UUID userID)  {
        this.userID = userID;
    }
}