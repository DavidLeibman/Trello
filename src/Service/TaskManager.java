package Service;

import Model.Epic;
import Model.Subtask;
import Model.Task;

import java.util.ArrayList;

public interface TaskManager {
    ArrayList<Task> getAllTasksList();

    ArrayList<Task> getAllEpicsList();

    ArrayList<Task> getAllSubtasksList();

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubtaskById(int id);

    Task getTaskById(int id);

    Task getSubtaskById(int id);

    Task getEpicById(int id);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    HistoryManager getHistoryManager();

}
