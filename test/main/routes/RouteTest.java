package main.routes;

import main.Main;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import spark.Spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class RouteTest {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private String port;

    @Before
    public void setUp() throws Exception {
        port = System.getenv("PORT");
        Main.main();
        Spark.awaitInitialization();
    }

    @After
    public void tearDown() throws Exception {
        Spark.stop();
    }

    protected void assertRouteResponse(String method, String path, String requestBody, String expectedResponse) throws Exception {
        byte[] bodyBytes = requestBody.getBytes(UTF_8);
        HttpURLConnection connection = makeConnection(path);
        writeRequestHeader(method, connection, bodyBytes);
        writeRequestBody(connection, bodyBytes);
        assertEquals(expectedResponse, getResponse(connection));
    }

    private HttpURLConnection makeConnection(String path) throws IOException {
        return (HttpURLConnection) new URL("http://localhost:" + port + path).openConnection();
    }

    private void writeRequestHeader(String method, HttpURLConnection connection, byte[] bodyBytes) throws ProtocolException {
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Length", String.valueOf(bodyBytes.length));
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }

    private void writeRequestBody(HttpURLConnection connection, byte[] bodyBytes) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(bodyBytes);
        outputStream.close();
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = inputReader.readLine()) != null) {
            responseBuilder.append(line);
            responseBuilder.append('\r');
        }
        inputReader.close();
        return responseBuilder.toString();
    }
}
