package com.bookmgr.bookmgr;

import org.javalite.activejdbc.Base;

public class Main {
    public static void Main() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://52.16.13.120/test", "sofia", "iambatgirl");
        Base.close();
    }
    
}
