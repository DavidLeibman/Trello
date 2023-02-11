package Service;

import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> lastVisit = new ArrayList<>();
    private List<Integer> taskIdRequest = new ArrayList<>();
    private List<Integer> epicIdRequest = new ArrayList<>();
    private List<Integer> subtaskIdRequest = new ArrayList<>();


    public void add(Task task) {}

    public List<Task> getHistory(){
       return lastVisit;
    }

}
