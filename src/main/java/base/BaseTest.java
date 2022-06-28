package base;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    // Base URI
    private static final String URI = "http://api.openweathermap.org/data/2.5/";
    // Loads .env file
    private static final Dotenv dotenv = Dotenv.configure().load();
    // Get API_KEY from .env file
    protected static final String API_KEY = dotenv.get("API_KEY");

    @BeforeClass
    public void setUp() {
        // Basic framework setup
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(URI)
                .setContentType(ContentType.JSON)
                .setUrlEncodingEnabled(false)
                .build();

        RestAssured.requestSpecification = reqSpec;
    }
}
