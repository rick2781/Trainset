package rick.trainset.Presentation.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rick.trainset.Domain.Data.FirebaseData;
import rick.trainset.Presentation.AuthActivities.LoginActivity.LoginActivity;
import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Context context = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Button button = findViewById(R.id.signout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                checkCurrentUser();
            }
        });

        checkCurrentUser();

        Injection.getDatabaseReferenceInstance();

        Injection.getDatabaseHelperInstance().getCategories();
    }

    @Subscribe
    public void getCategoryList(ArrayList<String> categoryList) {

        Log.d(TAG, "getCategoryList: executed");

        CategoryAdapter adapter = new CategoryAdapter(categoryList);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void checkCurrentUser() {

        FirebaseUser user = Injection.getAuthInstance().getCurrentUser();

        if (user == null) {

            Intent intent = new Intent(this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
