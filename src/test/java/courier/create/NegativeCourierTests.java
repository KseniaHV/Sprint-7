package courier.create;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import courier.Courier;
import courier.CourierAuthorization;
import courier.CourierChecks;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NegativeCourierTests {
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
    @DisplayName("Негативный тест: создание двух одинаковых курьеров")
    public void testDuplicateCouriers() {
        Courier courier = Courier.createdCourier();
        ValidatableResponse createResponse = check.createCourier(courier); // Создаем первого курьера
        check.successfulCreated(createResponse);

        ValidatableResponse duplicateResponse = check.createCourier(courier); // Создаем второго курьера
        check.conflictCourier(duplicateResponse);

        ValidatableResponse loginResponse = check.loginCourier(CourierAuthorization.from(courier)); // Авторизовываемся, чтобы достать id для удаления
        courierId = check.authorization(loginResponse);

    }

    @Test
    @DisplayName("Негативный тест: если одного из обязательных полей нет")
    public void testMissingFields() {
        Courier withoutLogin = Courier.noRequiredField();

        ValidatableResponse errorResponse = check.createCourier(withoutLogin);
        check.withoutLogin(errorResponse);

    }

    @Test
    @DisplayName("Негативный тест: создание пользователя с логином, который уже есть")
    public void testDuplicateLogin() {
        Courier courier = Courier.createdCourier();
        Courier doubleCourier = Courier.duplicateCourier();

        ValidatableResponse createResponse = check.createCourier(courier);
        check.successfulCreated(createResponse);

        ValidatableResponse duplicateResponse = check.createCourier(doubleCourier);
        check.conflictCourier(duplicateResponse);

        ValidatableResponse loginResponse = check.loginCourier(CourierAuthorization.from(courier));
        courierId = check.authorization(loginResponse);

    }

}
