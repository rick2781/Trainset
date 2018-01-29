package rick.trainset.Util;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Rick on 1/28/2018.
 */

public class Injection {

    //Firebase Authentication
    public static FirebaseAuth getAuthInstance() {
        return FirebaseAuthenticationHelper.getClassInstance().getInstance();
    }
}
