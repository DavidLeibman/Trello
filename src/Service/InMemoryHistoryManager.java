package Service;

import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public List<Task> History = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (History.size() == 10) {
            History.remove(0);
        }
        History.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return History;
    }

}
