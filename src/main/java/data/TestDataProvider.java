package data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "dpLatLong")
    public Object[][] dpLatLong() {
        return new Object[][] {
                {-27.594870f, -48.548222f, "Florianópolis", "BR"},
                {38.722252f, -9.139337f, "Lisbon Municipality", "PT"},
                {52.370216f, 4.895168f, "Amsterdam", "NL"},
                {-21.128128203523946f, -56.4884355331936f, "Bonito", "BR"} // Bonito MS
        };
    }

}
