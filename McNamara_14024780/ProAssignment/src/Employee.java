import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * The purpose of this class is to define attributes associated to employee
 * @author Daniel McNamara 14024780
 * @version 1.0
 */

public class Employee extends Person {

    private String id;
    private String salary;
    private String startDate;
    private String title;
    private String email;
    private Image image;

    /**
     * Creates a new employee.
     * 
     * @param id the id of the employee
     * @param name the name of the employee
     * @param gender the gender of the employee
     * @param natInscNo the national insurance number of the employee
     * @param dob the date of birth of the employee
     * @param address the address of the employee
     * @param postcode the postcode of the employee
     * @param salary the salary of the employee
     * @param startDate the start date of the employee
     * @param title the title of the employee
     * @param email the email of the employee
     * @param image the image of the employee
     */
    public Employee(String id, String name, char gender, String natInscNo, String dob, String address, String postcode, String salary, String startDate, String title, String email, Image image) {
        super(name, gender, natInscNo, dob, address, postcode);
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.title = title;
        this.email = email;
        this.image = image;
    }

    /**
     * Returns the email of the employee.
     * @return the email of the employee
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the employee.
     * @param email the email for the employee
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the title of the employee.
     * @return title returns the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the employee.
     * @param title the title for the employee
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the id of the employee.
     * @return id the id for the employee
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the id of the employee.
     * @param id the id for the employee
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Returns the salary of the employee.
     * @return salary the salary for the employee
     */
    public String getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the employee.
     * @param salary the salary for the employee
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * Returns the startDate of the employee.
     * @return startDate the startDate for the employee
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the startDate of the employee.
     * @param startDate the startDate for the employee
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Returns the image of the employee
     * @return image
     */
    public Image getImage() {
    	return image;
    }
    
    /**
     * Sets the image of the employee
     * @param image
     */
    public void setImage(Image image) {
    	this.image = image;
    }
    
    public byte[] getImageData() {
        if (image != null) {
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            bufferedImage.getGraphics().drawImage(image, 0, 0, null);

            try {
                ImageIO.write(bufferedImage, "png", outputStream);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            return outputStream.toByteArray();
        } else {
            return null;
        }
    	
    }

    /**
     * strings the employee methods together
     * @return email, title, id, salary, startDate 
     */
    public String toString() {
        return super.toString() + " Email: " + this.getEmail() + " Title: " + this.getTitle() + " ID: " + this.getID() + " Salary: " + this.getSalary() + " Start Date: " + this.getStartDate();

    }
}