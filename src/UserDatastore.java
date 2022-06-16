
import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;

public class UserDatastore implements Datastore<UserProfile>{

    private static File RecordsFile = new File("User.json");

    @Override
    public UserProfile Retrieve() {
        try {
            return Json.ReadFromFile(RecordsFile, new TypeReference<UserProfile>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new UserProfile();
        }
    }

    @Override
    public void Save(UserProfile records) {
        try {
            Json.SaveToFile(RecordsFile, records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
