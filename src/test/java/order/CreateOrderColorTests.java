package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class CreateOrderColorTests {
    private final OrderChecks check = new OrderChecks();
    private String colorBlack;
    private String colorGrey;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    public CreateOrderColorTests(String colorBlack, String colorGrey) {
        this.colorBlack = colorBlack;
        this.colorGrey = colorGrey;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"BLACK", null},
                {null, "GREY"},
                {"BLACK", "GREY"},
                {null, null}
        });
    }
    @Test
    @DisplayName("Создание заказа")
    public void createOrder(){
        List<String> colors = Arrays.asList(colorBlack, colorGrey);
        OrderClient orderClient =OrderClient.сreatOrderClient(colors);

        ValidatableResponse createResponse = check.createdOrder(orderClient);

        check.checkOrder(createResponse);

    }

}
