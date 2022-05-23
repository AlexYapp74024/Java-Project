//do we need to import?
//scan in main.java or scan in this class?

public class UserProfile {
    private String name;
    private String bloodtype;//use enum
    private String medical_hist;
    private float height;

    //Store name, blood type, and medical history (allergies, ilnesses) of user
    UserProfile(String name, String bloodtype, String medical_hist,float height){
    this.name = name;
    this.bloodtype = bloodtype;
    this.medical_hist = medical_hist;
    this.height = height;
    }

    // Getter
    public String getName() {
        return name;
    }
    public String getBloodType() {
        return bloodtype;
    }
    public String getMedicalHistory() {
        return medical_hist;
    }
    public float getHeight() {
        return height;
    }

    // Setter
        //scan userprofile(name,bloodtype,medical history) input and store into database, 
        //if there is already one user profile, do not ask for user input again
    public void setName( String name) {
        this.name = name;
    }
    public void setBloodType(String bloodtype) {
        this.bloodtype = bloodtype;
    }
    public void setMedicalHistory(String medical_hist) {

        this.medical_hist = medical_hist;
    }
    public void setHeight(float height) {

        this.height = height;
    }
}
