package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?"
            );

            st.setInt(1, 5);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        }catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public void updateData(){
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)"
            );

            st.setDouble(1, 200.0);
            st.setInt(2, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }


    public void insertData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        Connection conn = null;
        PreparedStatement st = null;

        try{

            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, "Caique Sobral");
            st.setString(2, "Caique@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("27/07/2001").getTime()));
            st.setDouble(4, 4000);
            st.setInt(5, 1);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs =  st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            }else{
                System.out.println("No rows affected");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public void getDataFromDb(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DB.getConnection();

            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM department");

            while (rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
