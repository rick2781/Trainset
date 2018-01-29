package rick.trainset.Util;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Rick on 1/28/2018.
 */

public class FirebaseAuthenticationHelper {

    private static final String TAG = "FirebaseAuthentication";

    public static FirebaseAuthenticationHelper INSTANCE;
    public FirebaseAuth mAuth;

    public static FirebaseAuthenticationHelper getClassInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseAuthenticationHelper();
        }

        return INSTANCE;
    }

    public FirebaseAuth getInstance() {

        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }

        return mAuth;
    }
}
