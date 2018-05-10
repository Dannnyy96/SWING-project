/**
 * The purpose of this class is to connect to the database and have the CRUD methods
 * @author Daniel McNamara 14024780
 * @version 1.0
 */

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EmployeeDAO {

    Connection dbConnection;

    /**
     * Connects to the database
     *  @return dbConnection database connection
     */

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        try {
            String dbURL = "jdbc:sqlite:empdb.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /**
     * This closes the connection to the database
     */
    public void closeConnection() {
        try {

            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Selects all the employees in the database
     * @return employees returns an ArrayList "employees"
     * @throws SQLException 
     */

    public ArrayList selectAllEmployees() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultset = null;
        ArrayList < Employee > employees = new ArrayList();

        String query = "SELECT * FROM employees;";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println(query);

            // execute SQL query
            resultset = statement.executeQuery(query);

            while (resultset.next()) {

                String id = resultset.getString("id");
                String name = resultset.getString("name");
                char gender = resultset.getString("gender").charAt(0);
                String dob = resultset.getString("dob");
                String address = resultset.getString("address");
                String postcode = resultset.getString("postcode");
                String natInscNo = resultset.getString("NIN");
                String title = resultset.getString("jobtitle");
                String startDate = resultset.getString("startdate");
                String salary = resultset.getString("salary");
                String email = resultset.getString("email");
                byte[] imageData = resultset.getBytes("image");
                
                Image image = null;
                if (imageData != null) {
                    final InputStream imageStream = new ByteArrayInputStream(imageData);

                    try {
                        image = ImageIO.read(imageStream);
                    } catch (final IOException ignored) {}
                } else {
                    image = null;
                }


                Employee employee = new Employee(id, name, gender, natInscNo, dob, address, postcode, salary, startDate, title, email, image);
                

                employees.add(employee);
                System.out.println(employee);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (resultset != null) {
                resultset.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return employees;

    }

    /**
     * Selects an employee depending on the name entered
     * @param name gets the name of the employee the user wants to select
     * @return employee returns an employee where name = the name entered
     */

    public Employee selectEmployeeByName(String name) {
        Connection connection = null;
        Statement statement = null;
        Employee employee = null;
        try {
            connection = getDBConnection();
            System.out.println("Read operation - database successfully opened");
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM employees WHERE name LIKE '%" + name.toLowerCase() + "%';");
            while (resultset.next()) {
                String id = resultset.getString("id");
                String nName = resultset.getString("name");
                char gender = resultset.getString("gender").charAt(0);
                String dob = resultset.getString("dob");
                String address = resultset.getString("address");
                String postcode = resultset.getString("postcode");
                String natInscNo = resultset.getString("NIN");
                String title = resultset.getString("jobtitle");
                String startDate = resultset.getString("startdate");
                String salary = resultset.getString("salary");
                String email = resultset.getString("email");
                byte[] imageData = resultset.getBytes("image");
                
  
                Image image = null;
                if (imageData != null) {
                    final InputStream imageStream = new ByteArrayInputStream(imageData);

                    try {
                        image = ImageIO.read(imageStream);                        
                    } catch (final IOException ignored) {}
                } else {
                    image = null;
                }


                employee = new Employee(id, nName, gender, natInscNo, dob, address, postcode, salary, startDate, title, email, image);
            }
            resultset.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Read operation successfully done");
        return employee;
    }

    /**
     * Inserts a new employee into the database
     * @param e creates a new employee 'e'
     * @return false
     * @throws SQLException
     */

    public boolean insertEmployee(Employee e) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection = getDBConnection(); // 
            System.out.println("Create operation -database successfully opened");
            String sql = "INSERT INTO employees(name, gender, DOB, Address, Postcode, NIN, JobTitle, StartDate,Salary, email, image)  " +
                "VALUES ('" + e.getName() + "','" + e.getGender() + "', '" + e.getDob() + "', '" + e.getAddress() + "', '" + e.getPostcode() + "','" + e.getNatInscNo() + "', '" + e.getTitle() + "', '" + e.getStartDate() + "', '" + e.getSalary() + "', '" + e.getEmail() + "', ?)";
            statement = dbConnection.prepareStatement(sql);
            System.out.println(sql);
            statement.setBytes(1, e.getImageData());
            statement.execute();
            statement.close();
            dbConnection.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(0);
        }
        System.out.println("Table successfully created");
        return false;
    }

    /**
     * Inserts an employee at the ID entered - updates current details
     * @param e creates a new employee 'e'
     * @param id gets the string 'id' to match to an employee
     * @return false
     */

    public boolean insertEmployeeAtID(Employee e, String id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection = getDBConnection(); 
            System.out.println("Create operation -database successfully opened");
            
            String sql = "UPDATE employees SET ID = " + id + " , Name = '" + e.getName() + "' , Gender = '" + e.getGender() + "' , DOB = '" + e.getDob() + "' , Address = '" + e.getAddress() + "' , Postcode = '" + e.getPostcode() + "' , NIN = '" + e.getNatInscNo() + "' , JobTitle = '" + e.getTitle() + "' , StartDate = '" + e.getStartDate() + "' , Salary = '" + e.getSalary() + "' , Email = '" + e.getEmail() + "', Image = ? WHERE id = '" + id +  "'";
            statement = dbConnection.prepareStatement(sql);
            statement.setBytes(1, e.getImageData());
            statement.execute();
            statement.close();
            dbConnection.close();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(0);
        }
        System.out.println("Table successfully created");
        return false;
    }

    /**
     * Deletes an employee where the id matches
     * @param id gets the string 'id' to match to an employee 
     * @return false
     */

    public boolean deleteEmployeeByID(String id) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:empdb.sqlite");
            connection.setAutoCommit(false);
            System.out.println("Delete operation -database successfully opened");
            statement = connection.createStatement();
            String sql = "DELETE from employees where ID=" + id;
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Delete operation successfully done");
        return false;
    }   
}