package rick.trainset.Presentation.AuthActivities.RegisterActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rick.trainset.R;

/**
 * Created by Rick on 1/27/2018.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
}
