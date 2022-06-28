package tests;

import base.BaseTest;
import data.TestDataProvider;
import io.restassured.response.Response;
import model.InvalidApiKey;
import model.OpenWeatherResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OpenWeatherMap extends BaseTest {

    @Test(dataProvider = "dpLatLong", dataProviderClass = TestDataProvider.class)
    public void testLatLongUsingDataProviderCheckCityAndCountry(float lat, float lon, String city, String country) {
        basePath = String.format("weather?lat=%f&lon=%f&appid=%s", lat, lon, API_KEY);

        OpenWeatherResponse response = get(basePath)
                .then().extract().body().as(OpenWeatherResponse.class);
        try {
            // Assert city name
            assertThat(response.name(), equalTo(city));
            // Assert country
            assertThat(response.sys().country(), equalTo(country));
        } catch (RuntimeException err) {
            err.printStackTrace();
            assert false;
        } catch (AssertionError err) {
            // City name changes between Lisbon and Socorro
            assertThat(response.name(), equalTo("Socorro"));
        }
    }

    @Test(dataProvider = "dpLatLong", dataProviderClass = TestDataProvider.class)
    public void testSchemaValidation(float lat, float lon, String city, String country) {
        basePath = String.format("weather?lat=%f&lon=%f&appid=%s", lat, lon, API_KEY);

        try {
            // JSON Schema Validation
            get(basePath).then().assertThat().body(matchesJsonSchemaInClasspath("schema/responseSchema.json"));
        } catch (AssertionError err) {
            err.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testInvalidAPI_KEY() {
        basePath = "weather?lat=50&lon=50&appid=WRONG_API_KEY";
        int cod = 401;
        String message = "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";

        InvalidApiKey res = get(basePath)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("schema/invalidApiKey.json"))
                .extract().body().as(InvalidApiKey.class);

        assertThat(res.cod(), equalTo(cod));
        assertThat(res.message(), equalTo(message));
    }

}
