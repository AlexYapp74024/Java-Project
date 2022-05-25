
public class UserProfile {
    private String name;
    private String medical_hist;
    private float height;
    private Bloodtype bloodtype;
    private enum Bloodtype { 
        A_MINUS("A+", 1), 
        B_PLUS("B+", 2), 
        B_MINUS("B-", 3), 
        O_PLUS("AO", 4), 
        O_MINUS("O-", 5), 
        AB_PLUS("AB+", 6), 
        AB_MINUS("AB-", 7);

        private Bloodtype(String value, int code) {
            this.value = value;
            this.code = code;
        }

        String value;
        int code;

        // switch case example for bloodtypes
        // public static void getBloodInformation(Bloodtype type) {
        //     switch (type) {
        //     case A_MINUS:
        //         // do something ...
        //         break;
        //     case O_MINUS:
        //         // do other things
        //         break;
        //     default:
        //         // default behavior if you didn't define all cases above
        //     }
    }

    // Default Constructor needed by Jackson library
    UserProfile() {};

    // Store name, blood type, and medical history (allergies, ilnesses) of user
    UserProfile(String name, Bloodtype bloodtype, String medical_hist, float height) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.medical_hist = medical_hist;
        this.height = height;
    }

    // Getter
    public String getName() {
        return name;
    }

    public Bloodtype getBloodType() {
        return bloodtype;
    }

    public String getMedicalHistory() {
        return medical_hist;
    }

    public float getHeight() {
        return height;
    }

    // Setter
    // scan userprofile(name,bloodtype,medical history) input and store into
    // database,
    // if there is already one user profile, do not ask for user input again
    public void setName(String name) {
        this.name = name;
    }

    public void setBloodType(Bloodtype bloodtype) {
        this.bloodtype = bloodtype;
    }

    public void setMedicalHistory(String medical_hist) {

        this.medical_hist = medical_hist;
    }

    public void setHeight(float height) {

        this.height = height;
    }
}
