package rick.trainset.Presentation.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rick.trainset.Presentation.AuthActivities.SingInActivity.SignInActivity;
import rick.trainset.R;
import rick.trainset.Util.Injection;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.addNewCategory)
    Button addNewCategoryButton;

    HomePresenter presenter;

    Context context = HomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        toolbarTitle.setText("Categories");

        presenter = new HomePresenter(this);

        Button button = findViewById(R.id.signout);
        button.setVisibility(View.GONE);
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
        Injection.getDatabaseHelperInstance().getUserCompany();
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

    @Subscribe
    public void addNewCategory(final String company) {

        addNewCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialDialog.Builder(context)
                        .title("Add New Category")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Type New Category Here", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                                if (input != "") {

                                    Injection.getDatabaseHelperInstance().addNewCategory(input.toString(), company);

                                    Injection.getDatabaseHelperInstance().getCategories();
                                }
                            }
                        })
                        .show();
            }
        });
    }
}
