package main.routes;

import org.junit.Test;

public class DeleteProductRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        assertRouteResponse("DELETE", "/products/id", "null");
    }
}
