
public class UserProfile{
    private static UserDatastore datastore = new UserDatastore();
    public static UserProfile profile = datastore.Retrieve();

    public static void SaveData() {
        datastore.Save(profile);
    }

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

    public String getBloodType() {
        return bloodtype.toString();
    }

    public String getMedicalHistory() {
        return medical_hist;
    }

    public float getHeight() {
        return height;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setBloodType(String bloodType) {
        this.bloodtype = new BloodType(bloodType);
    }

    public void setMedicalHistory(String medical_hist) {
        this.medical_hist = medical_hist;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    private String name;
    private String medical_hist;
    private float height;
    private BloodType bloodtype;
}
