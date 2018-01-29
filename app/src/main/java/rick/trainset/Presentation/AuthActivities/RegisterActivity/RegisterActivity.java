package rick.trainset.Presentation.AuthActivities.RegisterActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import rick.trainset.Domain.Model.User;
import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.Presentation.HomeActivity.HomeActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

/**
 * Created by Rick on 1/27/2018.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    @BindView(R.id.confirmButton)
    Button confirm;

    @BindView(R.id.email)
    EditText etEmail;

    @BindView(R.id.name)
    EditText etName;

    @BindView(R.id.password)
    EditText etPassword;

    Context context = RegisterActivity.this;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void registerNewUserListener() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerNewUser();
            }
        });

    }

    // First step for user registration
    public void registerNewUser() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!presenter.isStringNull(name) && !presenter.isStringNull(email) &&
                !presenter.isStringNull(password)) {

            if (presenter.checkEmail(email, context) || presenter.checkPassword(password, context)) {

//                showProgress();
                registerNewAccount(name, email, password);
            }

        } else {

            Toast.makeText(context, getString(R.string.blankEditText), Toast.LENGTH_SHORT).show();
        }
    }

    // Gather information to register user on database and send verification email
    public void registerNewAccount(final String name, final String email, String password) {

        Injection.getAuthInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(context, getString(R.string.account_creation_fail),
                                    Toast.LENGTH_SHORT).show();

                        } else if (task.isSuccessful()) {

                            Injection.getAuthInstance().sendVerificationEmail();
                            Toast.makeText(context, getString(R.string.account_creation_success),
                                    Toast.LENGTH_SHORT).show();

                            addUserDatabase(name, email);

                            Intent intent = new Intent(context, SignInActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    public void addUserDatabase(String name, String email) {

        User user = new User(name, email);

        repository.setUser(user, mContext);

        UserAccountSettings userAccountSettings = new UserAccountSettings(
                "none",
                name,
                "none");

        repository.setUserAccountSettings(userAccountSettings, mContext);
    }
}
