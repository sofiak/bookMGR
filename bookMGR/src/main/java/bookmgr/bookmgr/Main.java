package bookmgr.bookmgr;

import bookmgr.UI.SignIn;
import org.javalite.activejdbc.Base;
 
public class Main {
    public static void main(String[] args) {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
        
        SignIn newSession = new SignIn();
        newSession.render();
        Base.close();
    }
   
}