package examples.pltw.org.collegeapp;

/**
 * Created by wdumas on 4/8/16.
 */
public abstract class FamilyMember extends ApplicantData {
    private String firstName;
    private String lastName;

    public FamilyMember() {
        firstName = "Ada";
        lastName = "Lovelace";
    }

    public FamilyMember(String firstName, String lastName) {
       this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
