package rick.trainset.Presentation.AuthActivities.SingInActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import rick.trainset.Presentation.AuthActivities.LoginActivity.LoginActivity;
import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterActivity;
import rick.trainset.R;

/**
 * Created by Rick on 1/27/2018.
 */

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    Context context = SignInActivity.this;

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.register)
    Button registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

        goLogin();
        goRegister();
    }

    //Takes to Login Screen
    private void goLogin() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //Takes to Register Screen
    private void goRegister() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
