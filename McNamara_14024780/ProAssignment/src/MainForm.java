/**
 * The purpose of this class is to display a form where the user can create an employee,
 *  read an employees details, update employee details and delete an employee
 *  Can also press next or previous to scroll through each employee
 * @author Daniel McNamara 14024780
 * @version 1.0
 */


import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainForm extends JFrame {

    EmployeeDAO dao = new EmployeeDAO();
    ArrayList < Employee > allEmployees = new ArrayList < > ();
    private JLabel entername = null;
    private JLabel entergender = null;
    private JTextField name = null;
    private JLabel idlabel = null;
    private JRadioButton male = null;
    private JRadioButton female = null;
    private JLabel enterdate = null;
    private JSpinner date;
    private JSpinner month;
    private JSpinner year;
    private JLabel entersalary = null;
    private JTextField salary = null;
    private JLabel enterNIN = null;
    private JTextField NIN = null;
    private JLabel enteremail = null;
    private JTextField email = null;
    private JLabel enterstartdate = null;
    private JSpinner startdate;
    private JSpinner startmonth;
    private JSpinner startyear;
    private JLabel entertitle = null;
    private JTextField title = null;
    private JLabel enteraddress = null;
    private JTextField address = null;
    private JLabel enterpostcode = null;
    private JTextField postcode = null;
    private JButton imagebrowser = null;
    private JLabel imageLabel = null;
    private JButton enter = null;
    private JButton clear = null;
    private JButton update = null;
    private JButton next = null;
    private JButton previous = null;
    private JButton delete = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int counter = 0;
    private Image currentImage;
    private JLabel entersearch = null;
    private JTextField search = null;
    private JButton searchb = null;
    protected Component frame;

    public MainForm() {
        super("Employee Record System");

        try {
            allEmployees = dao.selectAllEmployees();
        } catch (SQLException e2) {

            e2.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        
         //creates the name label
         
        entername = new JLabel("Enter Name: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(entername, c);

       
          //creates the name text field
         
        name = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(name, c);

        
          //creates the id label 
         
        idlabel = new JLabel("ID:");
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(idlabel, c);


        
          //   creates the gender label 
         
        entergender = new JLabel();
        entergender.setText("Select Gender: ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(entergender, c);

        /**
         * creates the gender radio buttons
         */
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup bG = new ButtonGroup();
        bG.add(male);
        bG.add(female);
        this.setSize(100, 200);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(male, c);
        c.gridx = 3;
        this.add(female, c);
        male.setSelected(true);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates the date label   
        enterdate = new JLabel();
        enterdate.setText("Select Date of birth: ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enterdate, c);

        
        //creates the date spinner
        date = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(date, c);

        //creates the month spinner
        month = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(month, c);

        
         // creates the year spinner
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        year = new JSpinner(new SpinnerNumberModel(1950, 1950, currentYear, 1));
        year.setEditor(new JSpinner.NumberEditor(year, "#"));
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(year, c);

        //creates the salary label
        entersalary = new JLabel();
        entersalary.setText("Enter Salary: ");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(entersalary, c);

         //creates the salary label        
        salary = new JTextField();
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(salary, c);

         //creates the National insurance number label         
        enterNIN = new JLabel();
        enterNIN.setText("Enter NIN: ");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enterNIN, c);

        
        //creates the National insurance text field
        NIN = new JTextField();
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(NIN, c);

        
        //creates the email label
        enteremail = new JLabel();
        enteremail.setText("Enter Email: ");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enteremail, c);
        
        //creates the email text field 
        email = new JTextField();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(email, c);

        //creates the start date label
        enterstartdate = new JLabel();
        enterstartdate.setText("Select Start date: ");
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enterstartdate, c);
        
        //creates the startdate spinner
        startdate = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(startdate, c);

        //creates the startmonth spinner
        startmonth = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(startmonth, c);

        //creates the startyear spinner
        startyear = new JSpinner(new SpinnerNumberModel(1950, 1950, currentYear, 1));
        startyear.setEditor(new JSpinner.NumberEditor(startyear, "#"));
        c.gridx = 3;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(startyear, c);

        //creates the title label
        entertitle = new JLabel();
        entertitle.setText("Enter Job Title: ");
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(entertitle, c);
        
        //creates the title textfield
        title = new JTextField();
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(title, c);

        //creates the address label
        enteraddress = new JLabel();
        enteraddress.setText("Enter Address: ");
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enteraddress, c);

        //creates the address textfield
        address = new JTextField();
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(address, c);

        //creates the postcode label
        enterpostcode = new JLabel();
        enterpostcode.setText("Enter Postcode: ");
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enterpostcode, c);

        //creates the postcode textfield
        postcode = new JTextField();
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(postcode, c);

        //creates the image browser button
        imagebrowser = new JButton("Browse for image");
        c.gridx = 4;
        c.gridy = 9;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(imagebrowser, c);
        
        imagebrowser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	final JFileChooser fc = new JFileChooser();
            	fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            	int result = fc.showOpenDialog(getParent());
            	if (result == JFileChooser.APPROVE_OPTION) {
            	    // user selects a file
            	File selectedFile = fc.getSelectedFile();
            	
            	try {
            		currentImage = ImageIO.read(selectedFile);
            	} catch (IOException ee) {
            		currentImage = null;
            	}
            	
            	if (currentImage == null) {
            		JOptionPane.showMessageDialog(frame, "That's an invalid image!");
            	} else{
            		currentImage = currentImage.getScaledInstance(199, 274, Image.SCALE_SMOOTH);
            		
            		imageLabel.setText(null);
     	    	    imageLabel.setIcon(new ImageIcon(currentImage));
            	}
            	
       
              }
            }
        });
        
        //creates the image label
        imageLabel = new JLabel("No Image Available");
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 8;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(imageLabel, c);
        
        //creates the enter button
        enter = new JButton("Create");
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(enter, c);

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // This sets the validation for name
                String eName = name.getText();
                //validation of length check
                if (eName.length() > 0 && eName.length() <= 45) {
                	//validation to check only letters are used for the
                    if (eName.matches("[a-zA-Z ]+")) {

                    } else {
                        JOptionPane.showMessageDialog(frame, "Only letters may be used for the name! ");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Name needs to be between 1-45 characters ");
                    return;
                }

                char eGender = male.isSelected() ? 'M' : 'F';

              //This converts the spinner to a string value for date of birth
                Calendar cal = Calendar.getInstance();
                cal.set((int) year.getValue(), (int) month.getValue() - 1, (int) date.getValue());
                String eDate = dateFormat.format(cal.getTime());

                 //This sets the validation for Salary
                String eSalary = salary.getText();
                //this check that the validation is 10 digits or less but at least 1 digit
                if (eSalary.length() > 0 && eSalary.length() <= 10) {
                	//this checks that only numbers can be used for the salary
                	if (eSalary.matches("[0-9 ]+")) {
                		
                	}else{
                		JOptionPane.showMessageDialog(frame, "Only numbers may be used for the salary! ");
                		return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the salary needs to be between 1-10 digits ");
                    return;
                }

                //This sets the validation for NIN
                String eNIN = NIN.getText();
                //this checks that the NIN is 10 characters or less
                if (eNIN.length() > 0 && eNIN.length() <= 10) {
                	//this checks that only numbers and letters may be used for NIN
                	if (!eNIN.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "NIN needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of NIN needs to be between 1-10 characters");
                    return;
                }

                //This sets the validation for email
                String eEmail = email.getText();
                //validation to make sure the email is between 1-60 character length
                if (eEmail.length() > 0 && eEmail.length() <= 60) {
                	//validation to make sure the email is the correct format
                	if (!eEmail.matches(".+@.+\\..+")) {
                		JOptionPane.showMessageDialog(frame, "Email must be valid");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Email needs to be between 1-60 characters ");
                    return;
                }

                 //This converts the spinner values to a string for starting date
                cal = Calendar.getInstance();
                cal.set((int) startyear.getValue(), (int) startmonth.getValue() - 1, (int) startdate.getValue());
                String eStartDate = dateFormat.format(cal.getTime());
                
                //this converts the strings to dates
                try {
                    Date eDob = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
                        .parse(eDate);
                    Date eStart = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
                        .parse(eStartDate);
                    //this sets validation of dates, by making sure the starting date is after the date of birth
                    if (eDob.after(eStart)) {
                        JOptionPane.showMessageDialog(frame, "Date of birth has to be before Start Date! ");
                        return;
                    } else {

                    }
                    
                    if (!eDob.before(new Date())) {
                    	JOptionPane.showMessageDialog(frame, "Date of birth has to be in the past");
                        return;
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                
                //This sets the validation for title
                String eTitle = title.getText();
                //validation for title length being between 1-45
                if (eTitle.length() > 0 && eTitle.length() <= 45) {
                	//validation to make sure only letters and numbers may be used
                	if (!eTitle.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "Title needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Job title needs to be between 1-45 characters ");
                    return;
                }
                
                 //This sets the validation for address
                String eAddress = address.getText();
                	//validation for title length being between 1-60
                if (eAddress.length() > 0 && eAddress.length() <= 60) {
                	//validation to make sure only letters and numbers may be used
                	if (!eAddress.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "Address needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the address needs to be between 1-60 characters ");
                    return;
                }
                String ePostcode = postcode.getText();
              //validation for title length being between 1-10
                if (ePostcode.length() > 0 && ePostcode.length() <= 10) {
                	//validation to make sure only letters and numbers may be used
                	if (!ePostcode.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "NIN needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the postcode needs to be between 1-10 characters");
                    return;
                }


                Employee employee = new Employee("", eName, eGender, eNIN, eDate, eAddress, ePostcode, eSalary, eStartDate, eTitle, eEmail, currentImage);


                try {
                    dao.insertEmployee(employee);
                    allEmployees = dao.selectAllEmployees();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //creates the update button
        update = new JButton("Update");
        c.gridx = 3;
        c.gridy = 10;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(update, c);

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This sets the validation for name
                String eName = name.getText();
                //validation of length check
                if (eName.length() > 0 && eName.length() <= 45) {
                	//validation to check only letters are used for the
                    if (eName.matches("[a-zA-Z ]+")) {

                    } else {
                        JOptionPane.showMessageDialog(frame, "Only letters may be used for the name! ");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Name needs to be between 1-45 characters ");
                    return;
                }

                char eGender = male.isSelected() ? 'M' : 'F';

              //This converts the spinner to a string value for date of birth
                Calendar cal = Calendar.getInstance();
                cal.set((int) year.getValue(), (int) month.getValue() - 1, (int) date.getValue());
                String eDate = dateFormat.format(cal.getTime());

                 //This sets the validation for Salary
                String eSalary = salary.getText();
                //this check that the validation is 10 digits or less but at least 1 digit
                if (eSalary.length() > 0 && eSalary.length() <= 10) {
                	//this checks that only numbers can be used for the salary
                	if (eSalary.matches("[0-9 ]+")) {
                		
                	}else{
                		JOptionPane.showMessageDialog(frame, "Only numbers may be used for the salary! ");
                		return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the salary needs to be between 1-10 digits ");
                    return;
                }

                //This sets the validation for NIN
                String eNIN = NIN.getText();
                //this checks that the NIN is 10 characters or less
                if (eNIN.length() > 0 && eNIN.length() <= 10) {
                	//this checks that only numbers and letters may be used for NIN
                	if (!eNIN.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "NIN needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of NIN needs to be between 1-10 characters");
                    return;
                }

                //This sets the validation for email
                String eEmail = email.getText();
                //validation to make sure the email is between 1-60 character length
                if (eEmail.length() > 0 && eEmail.length() <= 60) {
                	//validation to make sure the email is the correct format
                	if (!eEmail.matches(".+@.+\\..+")) {
                		JOptionPane.showMessageDialog(frame, "Email must be valid");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Email needs to be between 1-60 characters ");
                    return;
                }

                 //This converts the spinner values to a string for starting date
                cal = Calendar.getInstance();
                cal.set((int) startyear.getValue(), (int) startmonth.getValue() - 1, (int) startdate.getValue());
                String eStartDate = dateFormat.format(cal.getTime());
                
                //this converts the strings to dates
                try {
                    Date eDob = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
                        .parse(eDate);
                    Date eStart = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
                        .parse(eStartDate);
                    //this sets validation of dates, by making sure the starting date is after the date of birth
                    if (eDob.after(eStart)) {
                        JOptionPane.showMessageDialog(frame, "Date of birth has to be before Start Date! ");
                        return;
                    } else {

                    }
                    
                    if (!eDob.before(new Date())) {
                    	JOptionPane.showMessageDialog(frame, "Date of birth has to be in the past");
                        return;
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                
                //This sets the validation for title
                String eTitle = title.getText();
                //validation for title length being between 1-45
                if (eTitle.length() > 0 && eTitle.length() <= 45) {
                	//validation to make sure only letters and numbers may be used
                	if (!eTitle.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "Title needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of Job title needs to be between 1-45 characters ");
                    return;
                }
                
                 //This sets the validation for address
                String eAddress = address.getText();
                	//validation for title length being between 1-60
                if (eAddress.length() > 0 && eAddress.length() <= 60) {
                	//validation to make sure only letters and numbers may be used
                	if (!eAddress.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "Address needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the address needs to be between 1-60 characters ");
                    return;
                }
                String ePostcode = postcode.getText();
              //validation for title length being between 1-10
                if (ePostcode.length() > 0 && ePostcode.length() <= 10) {
                	//validation to make sure only letters and numbers may be used
                	if (!ePostcode.matches("[A-Za-z0-9 ]+")) {
                		JOptionPane.showMessageDialog(frame, "NIN needs to be alphanumeric");
                        return;
                	}
                } else {
                    JOptionPane.showMessageDialog(frame, "Length of the postcode needs to be between 1-10 characters");
                    return;
                }
                

                Employee employee = allEmployees.get(counter);

                employee.setName(eName);
                employee.setGender(eGender);
                employee.setDob(eDate);
                employee.setEmail(eEmail);
                employee.setNatInscNo(eNIN);
                employee.setTitle(eTitle);
                employee.setStartDate(eStartDate);
                employee.setAddress(eAddress);
                employee.setPostcode(ePostcode);
                employee.setSalary(eSalary);
                employee.setImage(currentImage);

                try {
                    dao.insertEmployeeAtID(employee, employee.getID());
                    allEmployees = dao.selectAllEmployees();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //creates the clear button
        clear = new JButton("Clear");
        c.gridx = 1;
        c.gridy = 10;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(clear, c);

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                date.setValue(1);
                month.setValue(1);
                year.setValue(1970);
                email.setText("");
                male.setSelected(true);
                NIN.setText("");
                title.setText("");
                startdate.setValue(1);
                startmonth.setValue(1);
                startyear.setValue(1970);
                salary.setText("");
                address.setText("");
                postcode.setText("");
            }
        });

        //creates the delete button
        delete = new JButton("Delete");
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(delete, c);

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dao.deleteEmployeeByID(allEmployees.get(counter).getID());
                try {
                    allEmployees = dao.selectAllEmployees();
                } catch (SQLException e1) {

                }
                previous.doClick();
            }
        });

        //creates the previous button
        previous = new JButton("Previous");
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(previous, c);

        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter > 0) {  
                    setEmployee(allEmployees.get(--counter));
                }
            }
        });

        //creates the next button
        next = new JButton("Next");
        c.gridx = 3;
        c.gridy = 11;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(next, c);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter < allEmployees.size() - 1) {
                    counter++;
                    setEmployee(allEmployees.get(counter));
                }
            }
        });

        //creates the search label
        entersearch = new JLabel();
        entersearch.setText("Enter Name to search: ");
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(entersearch, c);

        //creates the search textfield
        search = new JTextField();
        c.gridx = 1;
        c.gridy = 12;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(search, c);

        //creates the search button
        searchb = new JButton("Search!");
        c.gridx = 3;
        c.gridy = 12;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(4, 4, 4, 4);
        this.getContentPane().add(searchb, c);

        searchb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = search.getText();
                Employee employee = dao.selectEmployeeByName(name);

                if (employee == null) {
                    JOptionPane.showMessageDialog(null, "No employee found with the name \"" + name + "\"");
                } else {
                    counter = Integer.parseInt(employee.getID());
                    setEmployee(employee);
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        
        imageLabel.setMaximumSize(imageLabel.getSize());
    }

    /**
     * creates the setEmployee method
     */
    public void setEmployee(Employee employee) {
        idlabel.setText("ID: " + employee.getID());
        name.setText(employee.getName());

        if (employee.getGender() == 'M' || employee.getGender() == 'm') {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }

        Calendar cal = Calendar.getInstance();

        try {

            cal.setTime(dateFormat.parse(employee.getDob()));

            date.setValue(cal.get(Calendar.DAY_OF_MONTH));
            month.setValue(cal.get(Calendar.MONTH) + 1);
            year.setValue(cal.get(Calendar.YEAR));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        salary.setText(employee.getSalary());
        NIN.setText(employee.getNatInscNo());
        email.setText(employee.getEmail());


        try {
            cal.setTime(dateFormat.parse(employee.getStartDate()));

            startdate.setValue(cal.get(Calendar.DAY_OF_MONTH));
            startmonth.setValue(cal.get(Calendar.MONTH) + 1);
            startyear.setValue(cal.get(Calendar.YEAR));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        title.setText(employee.getTitle());
        
        address.setText(employee.getAddress());
        postcode.setText(employee.getPostcode());
        
       if (employee.getImage() != null) {
    	   imageLabel.setText(null);
    	   imageLabel.setIcon(new ImageIcon(employee.getImage()));
       } else {
    	   imageLabel.setText("No Image Available");
    	   imageLabel.setIcon(null);
       }
        	
    }
}