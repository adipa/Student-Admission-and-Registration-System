package studAdmReg;

import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
 
	public static Connection ConnectDb(){
		
		try
		{
			Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\eclipse\\Admission and Registration System\\StudentDatabase (1).db");
            JOptionPane.showMessageDialog(null, "Connection Established ! ");
            return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
