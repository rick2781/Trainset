package rick.trainset.RegisterActivity;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import rick.trainset.Domain.Repository;
import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterActivity;
import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterContract;
import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterPresenter;
import rick.trainset.Util.FirebaseAuthenticationHelper;
import rick.trainset.Util.FirebaseDatabaseHelper;
import rick.trainset.Util.Injection;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rick on 1/29/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterTest {

    @Mock
    private RegisterPresenter presenter;

    @Mock
    private RegisterContract.View registerContractView;

    @Before
    public void setupRegisterPresenter() {

        MockitoAnnotations.initMocks(this);

        presenter = new RegisterPresenter(registerContractView);
    }

    @Test
    public void checkIfUserInputIsEmpty() {

        String errorString = "";

        assertThat(presenter.isStringNull(errorString), is(true));
    }

    @Test
    public void checkEmailIsValid() {

        String notValidEmail = "asdfasdfsadf";
        String notValidEmail2 = "sarrada@hotmailcom";

        String[] emailList = {notValidEmail, notValidEmail2};

        for (String notValidEmails : emailList) {

            assertThat(presenter.checkEmail(notValidEmails), is(false));
        }
    }

    @Test
    public void checkPasswordIsValid() {

        String password = "trainset";

        assertThat(presenter.checkPassword(password), is(true));
    }
}
