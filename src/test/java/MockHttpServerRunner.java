import com.harlap.test.http.MockHttpServer;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import rx.Observable;
import rx.Observer;
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse;

public class MockHttpServerRunner implements Runnable {

//    private static final int PORT = 3000;
//    private static final String baseUrl = "http://localhost:" + PORT;
//    private SimpleHttpResponseProvider responseProvider;
//    private com.github.kristofa.test.http.MockHttpServerRunner server;

    private static final int PORT = 30000;
    private static final String baseUrl = "http://localhost:" + PORT;
    private MockHttpServer server;
    private HttpClient client;



    public MockHttpServerRunner() throws Exception {
//        this.responseProvider =  new SimpleHttpResponseProvider();
//        responseProvider.expect(Method.POST, "/task-generator", "application/json", "hello").respondWith(200, "application/json", "{\"name\": \"map\"}");
//        this.server =  new com.github.kristofa.test.http.MockHttpServerRunner(PORT, responseProvider);

        server = new MockHttpServer(PORT);
        server.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("start......");
            CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
            Observable<ObservableHttpResponse> observableReq = ObservableHttp.createRequest(
                    HttpAsyncMethods.createPost("http://localhost:3000/task-generator", "Hello World", ContentType.APPLICATION_JSON),
                    httpClient).toObservable();
            observableReq.subscribe(new DummyObserver());

            server.start();

            server.expect(MockHttpServer.Method.POST, "/task-generator", "Hello World").respondWith(200, "application/json", "ABCD1234");

            System.out.println("end......");

            int i = 0;


//            while (true) {
//                Thread.sleep(3000);
//                System.out.println(i++);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DummyObserver implements Observer<ObservableHttpResponse> {
        @Override
        public void onCompleted() {
            System.out.println("...");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("...");
        }

        @Override
        public void onNext(ObservableHttpResponse args) {
            System.out.println("...");
        }
    }
}
