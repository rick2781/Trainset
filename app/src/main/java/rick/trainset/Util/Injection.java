package rick.trainset.Util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Rick on 1/28/2018.
 */

public class Injection {

    //Firebase Authentication
    public static FirebaseAuth getAuthInstance() {
        return FirebaseAuthenticationHelper.getClassInstance().getInstance();
    }

    //Firebase Database
    public static FirebaseDatabaseHelper getDatabaseHelperInstance() {
        return FirebaseDatabaseHelper.getHelperInstance();
    }

    //Firebase Database Reference - myRef
    public static DatabaseReference getDatabaseReferenceInstance() {
        return FirebaseDatabaseHelper.getHelperInstance().getDatabaseInstance().getReference();
    }
}
