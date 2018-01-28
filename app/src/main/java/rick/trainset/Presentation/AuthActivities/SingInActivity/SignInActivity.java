package rick.trainset.Presentation.AuthActivities.SingInActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rick.trainset.R;

/**
 * Created by Rick on 1/27/2018.
 */

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    }
}
