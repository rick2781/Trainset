package rick.trainset.Util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rick.trainset.Domain.Model.User;

/**
 * Created by Rick on 1/28/2018.
 */

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";

    public static FirebaseDatabaseHelper INSTANCE;
    public static FirebaseDatabase mDatabase;
    public static DatabaseReference myRef;

    public static FirebaseDatabaseHelper getHelperInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseDatabaseHelper();
        }

        return INSTANCE;
    }

    public FirebaseDatabase getDatabaseInstance() {

        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
        }

        return mDatabase;
    }

    public void addNewUser(User user) {

        final String userID = Injection.getAuthInstance().getCurrentUser().getUid();

        myRef = getDatabaseInstance().getReference();

        myRef.child("users")
                .child(userID)
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "Added new user entry successfully: " + userID);
                    }
                });

    }
}
