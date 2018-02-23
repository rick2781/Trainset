package rick.trainset.Presentation.HomeActivity;

import android.support.annotation.NonNull;

/**
 * Created by Rick on 2/18/2018.
 */

public class HomePresenter implements HomeContract.presenter {

    @NonNull
    HomeContract.View view;

    public HomePresenter(@NonNull HomeContract.View view) {
        this.view = view;
    }
}
