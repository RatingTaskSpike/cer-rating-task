package task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;

import rx.Observable;
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse;
import rx.util.functions.Func1;

public class AgentRatingBasedTaskGenerator  {

    private final CloseableHttpAsyncClient httpClient;
    private final ResponseToTasks responseToTasks;

    public AgentRatingBasedTaskGenerator(CloseableHttpAsyncClient httpClient, AgentRatingTaskToResponseConverter converter) {
//        httpClient = HttpAsyncClients.createDefault();
        this.httpClient = httpClient;
        this.responseToTasks = new ResponseToTasks(converter);
        this.httpClient.start();
    }

    public Observable<GeneratedSmallUnitAuditTask> observable() throws UnsupportedEncodingException {

        Observable<ObservableHttpResponse> httpResponseObservable;
            String request = "{\"MAP\": 0, \"COMPLIANCE_PAPER_WORK\": 0, \"PHONE\": 0}";
            httpResponseObservable = ObservableHttp.createRequest(
                    HttpAsyncMethods.createPost("http://localhost:30000/task-generator", "123", ContentType.APPLICATION_JSON),
                    httpClient).toObservable();

        return httpResponseObservable.flatMap(new Func1<ObservableHttpResponse,
                Observable<GeneratedSmallUnitAuditTask>>() {

            @Override
            public Observable<GeneratedSmallUnitAuditTask> call(ObservableHttpResponse response) {
                Observable<byte[]> content = response.getContent();

                Observable<List<GeneratedSmallUnitAuditTask>> map = content.map(responseToTasks);

                return flatten(map);
            }
        });
    }

    @PreDestroy
    public void preDestroy() {
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing http client", e);
        }
    }

    public static <T> Observable<T> flatten(Observable<? extends Iterable<? extends T>> source) {
        Func1<Iterable<? extends T>, Observable<T>> f = new Func1<Iterable <? extends T>, Observable<T>>() {
            public Observable<T> call(Iterable <? extends T> t1) {
                return Observable.from(t1);
            }
        };
        return Observable.concat(source.map(f));
    }

    private static class ResponseToTasks implements Func1<byte[], List<GeneratedSmallUnitAuditTask>> {

        private final AgentRatingTaskToResponseConverter converter;

        private ResponseToTasks(AgentRatingTaskToResponseConverter converter) {
            this.converter = converter;
        }

        public List<GeneratedSmallUnitAuditTask> call(byte[] bytes) {
            String responseString = new String(bytes);
            return converter.convert(responseString);
        }

    }
}
