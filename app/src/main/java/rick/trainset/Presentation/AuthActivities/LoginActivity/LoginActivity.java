package rick.trainset.Presentation.AuthActivities.LoginActivity;

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
import butterknife.ButterKnife;
import rick.trainset.Presentation.HomeActivity.HomeActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

/**
 * Created by Rick on 1/28/2018.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.email)
    EditText etEmail;

    @BindView(R.id.password)
    EditText etPassword;

    @BindView(R.id.confirmButton)
    Button confirmButton;

    LoginPresenter presenter;

    Context mContext = LoginActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);

        Injection.getAuthInstance();
    }

    @Override
    public void authUser() {

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (!presenter.isStringNull(email) || !presenter.isStringNull(password)) {

                    if (presenter.checkEmail(email) && presenter.checkPassword(password)) {

//                        showprogress()

                        Injection.getAuthInstance().signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (!task.isSuccessful()) {

                                            Toast.makeText(mContext, getString(R.string.auth_failed),
                                                    Toast.LENGTH_SHORT).show();

//                                            hideProgress();

                                        } else {

                                            //Check if user has verified email
                                            try {
                                                if (Injection.getAuthInstance().getCurrentUser().isEmailVerified()) {

                                                    Intent intent = new Intent(mContext, HomeActivity.class);
                                                    startActivity(intent);
                                                } else {

                                                    Toast.makeText(mContext, getString(R.string.email_not_verified), Toast.LENGTH_SHORT).show();
                                                }

                                            } catch (NullPointerException e) {

                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });

                    } else if (!presenter.checkEmail(email)) {

                        Toast.makeText(mContext, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();

                    } else if (!presenter.checkPassword(password)) {

                        Toast.makeText(mContext, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(mContext, getString(R.string.blankEditText), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (Injection.getAuthInstance().getInstance().getCurrentUser() != null) {

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
