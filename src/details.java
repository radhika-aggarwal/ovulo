import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.Buffer;
import java.sql.*;

class details{
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url="jdbc:mysql://localhost:3306/ovulo";
            String username="root";
            String password="2305";
    
            Connection con=DriverManager.getConnection(url,username,password);

            // create a query
            String q="insert into registration(Name,Email,Password) values (?,?,?)";

            // get the prepared statement

            PreparedStatement pstmt=con.prepareStatement(q);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

             System.out.println("Name:");
             String name=br.readLine();

            System.out.println("Email: ");
            String email=br.readLine();

            System.out.println("Password: ");
            String pass=br.readLine();


              pstmt.setString(1,name);
             pstmt.setString(2, email);
             pstmt.setString(3, pass);

             pstmt.executeUpdate();

             System.out.println("inserted..");

             con.close();

        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}
    

