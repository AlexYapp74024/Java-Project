
public class UserProfile {
    public static UserProfile profile = UserDatastore.Retrieve();

    private String name;
    private String medical_hist;
    private float height;
    private BloodType bloodtype;

    // Default Constructor needed by Jackson library
    UserProfile() {};

    // Store name, blood type, and medical history (allergies, ilnesses) of user
    UserProfile(String name, BloodType bloodtype, String medical_hist, float height) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.medical_hist = medical_hist;
        this.height = height;
    }

    // Getter
    public String getName() {
        return name;
    }

    public BloodType getBloodType() {
        return bloodtype;
    }

    public String getMedicalHistory() {
        return medical_hist;
    }

    public float getHeight() {
        return height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodtype = bloodType;
    }

    public void setMedicalHistory(String medical_hist) {
        this.medical_hist = medical_hist;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
