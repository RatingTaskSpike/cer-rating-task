import com.harlap.test.http.MockHttpServer;

/**
 * Created by DennyCloud on 14-1-22.
 */
public class StartServer {

    public static void main(String[] args) throws Exception {
        MockHttpServer server = new MockHttpServer(30000);
        server.start();
        server.expect(MockHttpServer.Method.POST, "/task-generator", "123").respondWith(200, "application/json", "{\"name\": \"map\"}");

        while(true) {

        }
    }
}
