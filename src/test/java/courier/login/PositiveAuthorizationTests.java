package courier.login;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import courier.Courier;
import courier.CourierAuthorization;
import courier.CourierChecks;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class PositiveAuthorizationTests {

    private final CourierChecks check = new CourierChecks();
    int courierId;

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
    @DisplayName("Тест на авторизацию курьера")
    public void testAuthorizationCourier() {
        var autho = CourierAuthorization.from(Courier.createdCourier());
        ValidatableResponse loginResponse = check.loginCourier(autho);
        courierId = check.authorization(loginResponse); // Проверка, что курьера авторизовался
        assertNotEquals(0, courierId);

    }
}
