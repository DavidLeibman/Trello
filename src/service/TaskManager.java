package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

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

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    List<Task> getHistory();
}
