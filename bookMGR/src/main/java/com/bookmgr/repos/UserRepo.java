/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookmgr.repos;

import com.bookmgr.models.User;
        

public class UserRepo {
    
    public void UserRepo(){
        
    }
    
    public static User createUser(String uname, String pw){
        User a = new User();
        a.set("username", uname);
        a.set("password", pw);
        return null;
    }
    
}
