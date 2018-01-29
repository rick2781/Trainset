package rick.trainset.Presentation.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.R;

public class HomeActivity extends AppCompatActivity {

    Context context = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startActivity(new Intent(context, SignInActivity.class));
    }
}
