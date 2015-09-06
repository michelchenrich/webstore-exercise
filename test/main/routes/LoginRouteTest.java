package main.routes;

import org.junit.Test;

public class LoginRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        assertRouteResponse("POST", "/login", "{}", "{\"success\":false,\"invalidEmailOrPassword\":true}");
    }
}
