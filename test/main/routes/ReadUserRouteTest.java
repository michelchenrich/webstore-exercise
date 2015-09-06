package main.routes;

import org.junit.Test;

public class ReadUserRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        assertRouteResponse("GET", "/read-user", "{\"success\":false}");
    }
}