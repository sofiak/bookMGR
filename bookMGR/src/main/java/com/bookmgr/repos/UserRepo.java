/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookmgr.repos;

import com.bookmgr.models.User;
import java.util.Date;
        

public class UserRepo {
    
    public void UserRepo(){
        
    }
    
    public User createUser(String uname, String pw){
        User user = new User();
        user.set("username", uname);
        user.set("password", pw);
        java.util.Date date= new java.util.Date();
        user.set("created_at", date.getTime());
        user.set("updated_at", date.getTime());
        return user;
    }
    
}
