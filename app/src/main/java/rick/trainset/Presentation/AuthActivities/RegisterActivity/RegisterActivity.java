package rick.trainset.Presentation.AuthActivities.RegisterActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.company)
    EditText etCompany;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Context context = RegisterActivity.this;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        presenter = new RegisterPresenter(this);
    }

    @Override
    public void registerNewUserListener() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkUserInput();
            }
        });
    }

    // Check user input and pass data for the user creation
    @Override
    public void checkUserInput() {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String company = etCompany.getText().toString();

        if (!presenter.isStringNull(name) && !presenter.isStringNull(email) &&
                !presenter.isStringNull(password) && !presenter.isStringNull(company)) {

            if (presenter.checkEmail(email) || presenter.checkPassword(password)) {

                showProgress(true);

                registerNewAccount(name, email, password, company);

            } else if (!presenter.checkEmail(email)) {

                Toast.makeText(context, context.getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();

            } else if (!presenter.checkPassword(password)) {

                Toast.makeText(context, context.getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();

            }

        } else {

            Toast.makeText(context, getString(R.string.blankEditText), Toast.LENGTH_SHORT).show();
        }
    }

    // Create new user to Firebase Authentication System
    @Override
    public void registerNewAccount(final String name, final String email, String password, final String company) {

        Injection.getAuthInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Toast.makeText(context, getString(R.string.account_creation_fail),
                                    Toast.LENGTH_SHORT).show();

                            showProgress(false);

                        } else if (task.isSuccessful()) {

                            presenter.sendVerificationEmail();

                            Toast.makeText(context, getString(R.string.account_creation_success),
                                    Toast.LENGTH_SHORT).show();

                            presenter.addUserDatabase(name, email, company);

                            Intent intent = new Intent(context, SignInActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void showProgress(boolean show) {

        if (show) {

            progressBar.setVisibility(View.VISIBLE);

        } else {

            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        showProgress(false);
    }
}
