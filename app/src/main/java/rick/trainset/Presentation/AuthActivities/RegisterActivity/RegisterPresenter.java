package rick.trainset.Presentation.AuthActivities.RegisterActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rick.trainset.Domain.Data.FirebaseData;
import rick.trainset.Domain.Model.User;
import rick.trainset.Domain.Repository;
import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

/**
 * Created by Rick on 1/27/2018.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    @NonNull
    RegisterContract.View view;

    public RegisterPresenter(@NonNull RegisterContract.View view) {
        this.view = view;

        view.registerNewUserListener();

        Injection.getAuthInstance();
        Injection.getDatabaseReferenceInstance();
    }

    // Check if email is valid
    public boolean checkEmail(String email) {

        if (!email.contains("@") || !email.contains(".com")) {

            return false;
        }

        return true;
    }

    // Check if password is valid
    public boolean checkPassword(String password) {

        if (password.length() < 6) {

            return false;
        }

        return true;
    }

    // Check if editText is empty
    public boolean isStringNull(String string) {
        if(string.equals("") || string.length() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //Add new user to Firebase Database
    public void addUserDatabase(String name, String email, String company) {

        User user = new User(name, email, company);

        Repository.getInstance().addNewUser(user);
    }

    //Method that sends email verification to new user
    public void sendVerificationEmail() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            user.sendEmailVerification();
        }
    }
}
