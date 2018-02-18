package rick.trainset.Presentation.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

import rick.trainset.Presentation.AuthActivities.LoginActivity.LoginActivity;
import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

public class HomeActivity extends AppCompatActivity {

    Context context = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Injection.getAuthInstance().signOut();
        checkCurrentUser();
    }

    public void checkCurrentUser() {

        FirebaseUser user = Injection.getAuthInstance().getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
