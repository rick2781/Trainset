package rick.trainset.Domain.Data;

import rick.trainset.Domain.Model.User;
import rick.trainset.Util.Injection;

/**
 * Created by Rick on 1/28/2018.
 */

public class FirebaseData implements DataSource {

    private static FirebaseData INSTANCE;

    public static FirebaseData getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new FirebaseData();
        }

        return INSTANCE;
    }

    public User getUser() {
        return null;
    }

    public static void addNewUser(User user){
        Injection.getDatabaseHelperInstance().addNewUser(user);
    }
}
