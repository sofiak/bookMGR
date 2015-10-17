package bookmgr.main;

import org.javalite.activejdbc.Base;

public class Connection {

    public Connection() {
        try {
            Base.connection();
        } catch (Exception e)
        {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/bookMGR", "sofia", "iambatgirl");
        }
    }
    
    public void close()
    {
        Base.close();
    }

}