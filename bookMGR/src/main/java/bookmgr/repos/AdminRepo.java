/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmgr.repos;

import bookmgr.models.User;
import org.javalite.activejdbc.Model;

/**
 * Repo for admin-related functions
 */
public class AdminRepo {
    
    public AdminRepo() {
    }
    
    public boolean checkIfAdmin(int user_id){
        UserRepo userrepo = new UserRepo();
        User user = User.findById(user_id);
        
        if(user.getInteger("isAdmin") == 1) {
            return true;
        }else {
            return false;
        }
    }
}
