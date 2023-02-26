import java.sql.*;
class InsertJDBC
{ 
    public static void main(String[] args) {
    


    try {
        Class.forName("com.mysql.jdbc.Driver");

        String url="jdbc:mysql://localhost:3306/ovulo";
        String username="root";
        String password="2305";

        Connection con=DriverManager.getConnection(url,username,password);

        String q="create table registration(Name varchar(20), Email varchar(50) not null , Password varchar(50) not null, Last_Period_Date DATE, Period_cycle DATE)";

        Statement stmt=con.createStatement();
        stmt.executeUpdate(q);
            
        System.out.println("table created in database..");

        con.close();
    

    } catch (Exception e)
    {
     e.printStackTrace();

    }
}
}

