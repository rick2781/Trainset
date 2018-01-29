package rick.trainset.Presentation.AuthActivities.RegisterActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import rick.trainset.R;

/**
 * Created by Rick on 1/27/2018.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    @NonNull
    RegisterContract.View view;

    public RegisterPresenter(@NonNull RegisterContract.View view) {
        this.view = view;
    }

    // Check if email is valid
    public boolean checkEmail(String email, Context context) {

        if (!email.contains("@") || !email.contains(".com")) {
            Toast.makeText(context, context.getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }

    // Check if password is valid
    public boolean checkPassword(String password, Context context) {

        if (password.length() < 6) {
            Toast.makeText(context, context.getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
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
}
