
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;

public class UserDatastore {

    private static File RecordsFile = new File("User.json");

    public static UserProfile Retrieve() {
        try {
            return Json.ReadFromFile(RecordsFile, new TypeReference<UserProfile>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new UserProfile();
        }
    }

    public static void Save(UserProfile records) {
        try {
            Json.SaveToFile(RecordsFile, records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
