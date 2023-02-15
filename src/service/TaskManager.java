package service;

import model.Epic;
import model.Subtask;
import model.Task;

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

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);

    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    HistoryManager getHistoryManager();/* зачем тут писать метод, который возрашать List айдишников тасков
    если у нас в интерфейсе HistoryManager уже есть метод который возращает лист просмотренных тасков?
    А этот метод я написал так как поле historyManager -private и хотелось бы через объект inMemoryTaskManager
    вызывать метод getHistory()*/
}
