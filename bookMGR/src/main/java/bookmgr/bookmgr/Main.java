package bookmgr.bookmgr;

import bookmgr.UI.SignIn;
import org.javalite.activejdbc.Base;
 
public class Main {
    public static void main(String[] args) {
        Connection conn = new Connection();
        SignIn newSession = new SignIn();
        newSession.render();
        conn.close();
    }
   
}