package courier.login;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import courier.CourierAuthorization;
import courier.CourierChecks;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NegativeAuthorizationTests {
    private final CourierChecks check = new CourierChecks();
    int courierId;
    String errorCode;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse response = check.deleteCourier(courierId);
            check.deleteSuccesfully(response);
        }
    }


    @Test
    @DisplayName("Негативный тест: неправильный пароль")
    public void testInvalidPassword() {
        CourierAuthorization autho =CourierAuthorization.wrongPassword();

        ValidatableResponse loginResponse = check.loginCourier(autho);
        check.invalidCredentials(loginResponse);

    }


    @Test
    @DisplayName("Негативный тест: отсутствие логина")
    public void testMissingLogin() {
        CourierAuthorization autho = CourierAuthorization.missedLogin();

        ValidatableResponse missedLoginResponse = check.loginCourier(autho);
        check.withoutLogin(missedLoginResponse);

    }


    @Test
    @DisplayName("Негативный тест: авторизация несуществующего пользователя")
    public void testNonExistentUser() {
        CourierAuthorization autho = CourierAuthorization.invalidLogin();
        ValidatableResponse invalidLoginResponse = check.loginCourier(autho);
        check.invalidCredentials(invalidLoginResponse);

    }

}
