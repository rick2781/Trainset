package rick.trainset.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterContract;
import rick.trainset.Presentation.AuthActivities.RegisterActivity.RegisterPresenter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Rick on 2/5/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {

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
