package main.routes;

import org.junit.Test;

public class CreateSalesOrderRouteTest  extends RouteTest {
    @Test
    public void integration() throws Exception {
        assertRouteResponse("POST", "/sales-order", "{}",
                "{\"success\":false," +
                        "\"invalidProductId\":true}");
    }
}
