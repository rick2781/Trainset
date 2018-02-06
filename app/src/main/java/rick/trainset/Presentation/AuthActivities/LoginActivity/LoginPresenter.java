package rick.trainset.Presentation.AuthActivities.LoginActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import rick.trainset.R;

/**
 * Created by Rick on 1/28/2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    LoginContract.View view;

    public LoginPresenter(@NonNull LoginContract.View view) {

        this.view = view;
        view.authUser();
    }

    public boolean isStringNull(String string) {
        if(string.equals("") || string.length() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkEmail(String email) {

        if (!email.contains("@") || !email.contains(".com")) {
            return false;
        }

        return true;
    }

    public boolean checkPassword(String password) {

        if (password.length() < 6) {
            return false;
        }

        return true;
    }
}
