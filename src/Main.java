import javax.annotation.processing.SupportedSourceVersion;

class Main {

    public static void main(String[] args) {

        String Name = "";
        String BloodType = "";
        String MedicalHistory = "";

        UserProfile Yew = new UserProfile(Name, BloodType, MedicalHistory, 2.f);

        var data = Database.GenerateRandomRecords(30);

        for (var d : data) {
            System.out.println(d.height + " , " + d.height + " , " + d.bodyTemp + " , " + d.date);
        }

    } // example
}
