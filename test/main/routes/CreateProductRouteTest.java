package main.routes;

import org.junit.Test;

public class CreateProductRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        assertRouteResponse("POST", "/products", "{}",
                "{\"success\":false," +
                        "\"invalidName\":true," +
                        "\"invalidDescription\":true," +
                        "\"invalidPrice\":true," +
                        "\"invalidUnitsInStock\":true}");
    }
}
