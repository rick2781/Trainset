package rick.trainset.Domain;

import android.support.annotation.NonNull;

import rick.trainset.Domain.Data.DataSource;
import rick.trainset.Domain.Data.FirebaseData;
import rick.trainset.Domain.Model.User;

/**
 * Created by Rick on 1/28/2018.
 */

public class Repository implements DataSource {

    private static Repository INSTANCE;

    public static Repository getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }

        return INSTANCE;
    }

    public void addNewUser(User user) {
        FirebaseData.addNewUser(user);
    }
}
