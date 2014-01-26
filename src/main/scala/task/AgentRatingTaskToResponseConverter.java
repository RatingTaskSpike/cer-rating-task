package task;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgentRatingTaskToResponseConverter {

    public List<GeneratedSmallUnitAuditTask> convert(String responseString) {
        List<GeneratedSmallUnitAuditTask> tasks = new ArrayList();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List taskContents = objectMapper.readValue(responseString, List.class);
            for (Object taskContent : taskContents) {
                //Map is just simple, so use it
                GeneratedSmallUnitAuditTask task = new GeneratedSmallUnitAuditTask(
                        ((Map) taskContent).get("name").toString());
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing JSON response: " + responseString, e);
        }
        return tasks;
    }
}
