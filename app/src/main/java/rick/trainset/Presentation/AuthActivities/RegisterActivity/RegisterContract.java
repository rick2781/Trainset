package rick.trainset.Presentation.AuthActivities.RegisterActivity;

/**
 * Created by Rick on 1/27/2018.
 */

public interface RegisterContract {

    interface View {

        void registerNewUserListener();

        void checkUserInput();

        void registerNewAccount(String name, String email, String password, String company);

        void showProgress(boolean show);
    }

    interface Presenter {


    }
}
