import com.github.kristofa.test.http.Method;
import com.github.kristofa.test.http.MockHttpServer;
import com.github.kristofa.test.http.SimpleHttpResponseProvider;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import task.AgentRatingBasedTaskGenerator;
import task.AgentRatingTaskToResponseConverter;
import task.GeneratedSmallUnitAuditTask;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class MockHttpServerBehaviour {

    private static final int PORT = 3000;
    private static final String baseUrl = "http://localhost:" + PORT;
    private MockHttpServer server;
    private SimpleHttpResponseProvider responseProvider;
    private HttpClient client;

    @Before
    public void setUp() throws Exception {
        responseProvider = new SimpleHttpResponseProvider();
        server = new MockHttpServer(PORT, responseProvider);
        server.start();
        client = new DefaultHttpClient();
    }

    @After
    public void tearDown() throws Exception {
        client.getConnectionManager().shutdown();
        server.stop();
    }

    @Test
    public void testShouldHandleGetRequests() throws ClientProtocolException, IOException {
        // Given a mock server configured to respond to a GET / with "OK"
        responseProvider.expect(Method.GET, "/").respondWith(200, "text/plain", "OK");

        // When a request for GET / arrives
        final HttpGet req = new HttpGet(baseUrl + "/");
        final HttpResponse response = client.execute(req);
        final String responseBody = IOUtils.toString(response.getEntity().getContent());
        final int statusCode = response.getStatusLine().getStatusCode();

        // Then the response is "OK"
        assertEquals("OK", responseBody);

        // And the status code is 200
        assertEquals(200, statusCode);
    }

    @Test
    public void testShouldHandlePostRequests() throws ClientProtocolException, IOException {
        // Given a mock server configured to respond to a GET / with "OK"
        responseProvider.expect(Method.POST, "/task-generator", "application/json", "hello").respondWith(200, "application/json", "{\"name\": \"map\"}");

        // When a request for GET / arrives
        final HttpPost req = new HttpPost(baseUrl + "/task-generator");
        final HttpResponse response = client.execute(req);
        final String responseBody = IOUtils.toString(response.getEntity().getContent());
        final int statusCode = response.getStatusLine().getStatusCode();

        // Then the response is "OK"
        assertEquals("{\"name\": \"map\"}", responseBody);

        // And the status code is 200
        assertEquals(200, statusCode);
    }

    @Test
    public void testName() throws Exception {
//        responseProvider.expect(Method.POST, "/task-generator",
//                "application/json; charset=UTF-8", "{\"MAP\": 0, \"COMPLIANCE_PAPER_WORK\": 0, \"PHONE\": 0}").respondWith(200, "application/json", "{\"name\": \"map\"}");
//        AgentRatingBasedTaskGenerator taskGenerator = new AgentRatingBasedTaskGenerator(HttpAsyncClients.createDefault(), new AgentRatingTaskToResponseConverter());
//
//        Observable<GeneratedSmallUnitAuditTask> observable = taskGenerator.observable();
//        observable.subscribe(new DummyObserver());
//
//
//        Thread.sleep(10000);
    }


    private class DummyObserver implements Observer<GeneratedSmallUnitAuditTask> {

        @Override
        public void onCompleted() {
            System.out.println("123");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("123");
        }

        @Override
        public void onNext(GeneratedSmallUnitAuditTask args) {
            System.out.println("123");
        }
    }
}