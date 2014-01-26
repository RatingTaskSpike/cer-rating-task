import com.harlap.test.http.MockHttpServer;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse;
import task.AgentRatingBasedTaskGenerator;
import task.AgentRatingTaskToResponseConverter;
import task.GeneratedSmallUnitAuditTask;

/**
 * Created by DennyCloud on 14-1-21.
 */

class MockServer implements Runnable {

    @Override
    public void run() {
        MockHttpServer server = new MockHttpServer(30000);
        try {
            server.start();
        } catch (Exception e) {
        }
        server.expect(MockHttpServer.Method.POST, "/task-generator", "123").respondWith(200, "application/json", "{\"name\": \"map\"}");

    }
}

public class TestMain {

    private static final int PORT = 30000;
//    private static final String baseUrl = "http://localhost:" + PORT;
//    private MockHttpServer server;
//    private HttpClient client;

    @Before
    public void setUp() throws Exception {

//        new MockServer().run();
//        MockHttpServer server = new MockHttpServer(PORT);
//        server.start();
//        server.expect(MockHttpServer.Method.POST, "/task-generator", "123").respondWith(200, "application/json", "{\"name\": \"map\"}");
    }

    @Test
    public void shouldGetResponse() throws Exception {
//        new MockHttpServerRunner().run();
//        AgentRatingBasedTaskGenerator generator = new AgentRatingBasedTaskGenerator(HttpAsyncClients.createDefault(), new AgentRatingTaskToResponseConverter());
//
//        Observable<GeneratedSmallUnitAuditTask> observable = generator.observable();
//
//        observable.subscribe(new DummyObserver());



//        httpClient = HttpAsyncClients.createDefault();
//        httpClient.start();
//        Observable<ObservableHttpResponse> observableReq3 = ObservableHttp.createRequest(
//                HttpAsyncMethods.createPost("http://localhost:30000/task-generator", "Hello World", ContentType.APPLICATION_JSON),
//                httpClient).toObservable();
//        observableReq3.subscribe(new DummyObserver());


//        int i = 0;
//
//            httpClient = HttpAsyncClients.createDefault();
//            httpClient.start();
//            Observable<ObservableHttpResponse> tmpObservable = ObservableHttp.createRequest(
//                    HttpAsyncMethods.createPost("http://localhost:30000/task-generator", "Hello World", ContentType.APPLICATION_JSON),
//                    httpClient).toObservable();
//            DummyObserver observer = new DummyObserver();
//            tmpObservable.subscribe(observer);
////            Thread.sleep(1000);
//
//            if (observer.isOnNext)
//            System.out.println("success............................................................");
//            System.out.println(i++);


        AgentRatingBasedTaskGenerator generator3 = new AgentRatingBasedTaskGenerator(HttpAsyncClients.createDefault(), new AgentRatingTaskToResponseConverter());

        Observable<GeneratedSmallUnitAuditTask> observable3 = generator3.observable();

        observable3.subscribe(new DummyObserver());


       while(true) {

       }


    }


}

class DummyObserver implements Observer<GeneratedSmallUnitAuditTask> {

    public boolean isOnNext = false;
    @Override
    public void onCompleted() {
        System.out.println("completed");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("error: " + e);
    }

    @Override
    public void onNext(GeneratedSmallUnitAuditTask args) {
        isOnNext = true;
        System.out.println("next");
    }
}
      //{"MAP": 0, "COMPLIANCE_PAPER_WORK": 0, "PHONE": 0}