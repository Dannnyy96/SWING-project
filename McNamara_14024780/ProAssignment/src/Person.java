/**
 * The purpose of this class is to define attributes associated to person
 * @author Daniel McNamara 14024780
 * @version 1.0
 */

public class Person {

    private String name;
    private char gender = '?';
    private String natInscNo;
    private String dob;
    private String address;
    private String postcode;

    /**
     * creates a person
     * @param name the name of the person
     * @param gender the gender of the person
     * @param natInscNo the NIN of the person
     * @param dob the date of birth of the person
     * @param address the address of the person
     * @param postcode the postcode of the person
     */
    public Person(String name, char gender, String natInscNo, String dob, String address, String postcode) {
        this.name = name;
        this.gender = gender;
        this.natInscNo = natInscNo;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
    }

    /**
     * returns the name of the person
     * @return name name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person
     * @param name name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the gender of the person
     * @return gender gender of the person
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person
     * @param gender gender of the person
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Returns the natInscNo of the person
     * @return natInscNo natInscNo of the person
     */
    public String getNatInscNo() {
        return natInscNo;
    }

    /**
     * Sets the natInscNo of the person
     * @param natInscNo natInscNo of the person
     */
    public void setNatInscNo(String natInscNo) {
        this.natInscNo = natInscNo;
    }

    /**
     * Returns the dob of the person
     * @return dob dob of the person
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the dob of the person
     * @param dob dob of the person
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * Returns the address of the person
     * @return address address of the person
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the person
     * @param address address of the person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the postcode of the person
     * @return postcode postcode of the person
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the postcode of the person
     * @param postcode postcode of the person
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * strings the person methods together
     * @return name, gender, natInscNo, dob, address, postcode         
     */
    public String toString() {
        return "Name: " + this.getName() + " Gender: " + this.getGender() + " NatInscNo: " + this.getNatInscNo() + " Dob:  " + this.getDob() + " Address: " + this.getAddress() + " Postcode: " + this.getPostcode();
    }
}