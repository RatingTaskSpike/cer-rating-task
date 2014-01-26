package httpClient;


import au.gov.cer.registry.smallunits.task.vo.Task;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgentRatingTaskToResponseConverter123 {

    public List<Task> convert(String responseString) {
        List<Task> tasks = new ArrayList();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List taskContents = objectMapper.readValue(responseString, List.class);
            for (Object taskContent : taskContents) {
                //Map is just simple, so use it
                Task task = new Task(
                        ((Map) taskContent).get("name").toString());
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response: " + responseString, e);
        }
        return tasks;
    }
}
