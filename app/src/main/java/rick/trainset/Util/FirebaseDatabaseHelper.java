package rick.trainset.Util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rick.trainset.Domain.Model.User;

/**
 * Created by Rick on 1/28/2018.
 */

public class FirebaseDatabaseHelper {

    private static final String TAG = "FirebaseDatabaseHelper";

    public static FirebaseDatabaseHelper INSTANCE;
    public static FirebaseDatabase mDatabase;
    public static DatabaseReference myRef;

    //Admin - allowed to Read/Watch/Post/Delete/Give Roles/Delete Users
    int ADMIN = 3;

    //Manager - allowed to Read/Watch/Post/Delete
    int MANAGER = 2;

    //Employee - allowed to Read/Watch
    int EMPLOYEE = 1;

    public static FirebaseDatabaseHelper getHelperInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseDatabaseHelper();
        }

        return INSTANCE;
    }

    public FirebaseDatabase getDatabaseInstance() {

        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            myRef = mDatabase.getReference();
        }

        return mDatabase;
    }

    // Add user to database passing personal data and company data
    public void addNewUser(User user) {

        final String userID = Injection.getAuthInstance().getCurrentUser().getUid();

        myRef.child("user")
                .child(userID)
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "Added new user entry successfully: " + userID);
                    }
                });

        setInitialRole(user);

        myRef.child("company")
                .child(user.getCompany())
                .child("workers")
                .setValue(userID);

        initDatabaseContent(user);
    }

    //Set user level for certain company
    private void setInitialRole(final User user) {

        final String userID = Injection.getAuthInstance().getCurrentUser().getUid();

        myRef.child("company")
                .child(user.getCompany())
                .child("workers")
                .child(userID)
                .child("role")
                .setValue(ADMIN);

        myRef.child("company")
                .child(user.getCompany())
                .child("workers")
                .child(userID)
                .child("name")
                .setValue(user.getName());
    }

    public void getCategories() {

        if (Injection.getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.getAuthInstance().getCurrentUser().getUid();

            myRef.child("user")
                    .child(userID)
                    .child("company")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null) {

                                String company = dataSnapshot.getValue().toString();

                                getCategoriesData(company);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    public void getCategoriesData(String company) {

       final ArrayList<String> categoryList = new ArrayList<>();

        myRef.child("company")
                .child(company)
                .child("content_database_ref")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot savedCategories : dataSnapshot.getChildren()) {

                            String categories = savedCategories.getValue().toString();

                            categoryList.add(categories);

                        }

                        EventBus.getDefault().post(categoryList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void initDatabaseContent(User user) {

        addNewCategory("Espresso", user.getCompany());
        addNewCategory("Bread n butter", user.getCompany());
    }

    public void addNewCategory(String category, String company) {

            myRef.child("company")
                    .child(company)
                    .child("content_database_ref")
                    .child(category)
                    .setValue(category);
    }

    public void getUserCompany() {

        if (Injection.getAuthInstance().getCurrentUser() != null) {

            String userID = Injection.getAuthInstance().getCurrentUser().getUid();

            myRef.child("user")
                    .child(userID)
                    .child("company")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null) {

                                String company = dataSnapshot.getValue().toString();

                                EventBus.getDefault().post(company);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }
}
