package Service;

import Model.Epic;
import Model.Subtask;
import Model.Task;
import Model.TaskStatus;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private List<Task> History = new ArrayList<>();
    private List<Integer> taskIdRequest = new ArrayList<>();
    private List<Integer> epicIdRequest = new ArrayList<>();
    private List<Integer> subtaskIdRequest = new ArrayList<>();
    private int uniqueID;

    HashMap<Integer, Task> tasksMapById = new HashMap<>();
    HashMap<Integer, Subtask> subtasksMapById = new HashMap<>();
    HashMap<Integer, Epic> epicsMapById = new HashMap<>();

    @Override
    public ArrayList<Task> getAllTasksList() {
        return new ArrayList<>(tasksMapById.values());
    }

    @Override
    public ArrayList<Task> getAllEpicsList() {
        return new ArrayList<>(epicsMapById.values());
    }

    @Override
    public ArrayList<Task> getAllSubtasksList() {
        return new ArrayList<>(subtasksMapById.values());
    }

    @Override
    public void removeAllTasks() {
        tasksMapById.clear();
    }

    @Override
    public void removeAllEpics() {
        epicsMapById.clear();
    }

    @Override
    public void removeAllSubtasks() {
        subtasksMapById.clear();
        for (Epic epic : epicsMapById.values()) {
            epic.setStatus(TaskStatus.NEW);
            epic.getEpicSubtasksId().clear();
        }
    }

    @Override
    public void removeTaskById(int id) {
        tasksMapById.remove(id);
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = epicsMapById.get(id);
        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            Subtask subtask = subtasksMapById.get(subtaskId);
            subtask.setEpicId(0);
            subtasksMapById.remove(subtaskId);
        }
        epic.getEpicSubtasksId().clear();
        epicsMapById.remove(id);
    }

    @Override
    public void removeSubtaskById(int id) {
        Subtask subtask = subtasksMapById.get(id);
        Epic epic = epicsMapById.get(subtask.getEpicId());
        epic.getEpicSubtasksId().remove((Integer) id);
        updateEpicStatus(epic);
        subtask.setEpicId(0);
        subtasksMapById.remove(id);
    }

    @Override
    public Task getTaskById(int id) {
        if (History.size() == 10) {
            History.remove(0);
        }
        taskIdRequest.add(id);
        History.add(tasksMapById.get(id));

        return tasksMapById.get(id);
    }

    @Override
    public Task getSubtaskById(int id) {
        if (History.size() == 10) {
            History.remove(0);
        }
        subtaskIdRequest.add(id);
        History.add(subtasksMapById.get(id));

        return subtasksMapById.get(id);
    }

    @Override
    public Task getEpicById(int id) {
        if (History.size() == 10) {
            History.remove(0);
        }
        epicIdRequest.add(id);
        History.add(epicsMapById.get(id));

        return epicsMapById.get(id);
    }

    @Override
    public void createTask(Task task) {
        task.setId(uniqueID++);
        tasksMapById.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(uniqueID++);
        if (epic.getEpicSubtasksId().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        }
        epicsMapById.put(epic.getId(), epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        if (epicsMapById.containsKey(subtask.getEpicId())) {
            subtask.setId(uniqueID++);
            subtasksMapById.put(subtask.getId(), subtask);
            Epic epic = epicsMapById.get(subtask.getEpicId());
            epic.setEpicSubtasksId(subtask.getId());
            updateEpicStatus(epic);
        }
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Subtask> list = new ArrayList<>();
        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            list.add(subtasksMapById.get(subtaskId));
        }
        return list;
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        Set<TaskStatus> uniqueSubtasksStatus = new HashSet<>();

        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            Subtask subtask = subtasksMapById.get(subtaskId);
            uniqueSubtasksStatus.add(subtask.getStatus());
        }
        if (uniqueSubtasksStatus.size() <= 1 && uniqueSubtasksStatus.contains(TaskStatus.NEW)) {
            epic.setStatus(TaskStatus.NEW);
        } else if (uniqueSubtasksStatus.size() <= 1 && uniqueSubtasksStatus.contains(TaskStatus.DONE)) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
    }

    @Override
    public void updateSubtaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        Epic epic = epicsMapById.get(subtask.getEpicId());
        updateEpicStatus(epic);
    }

    @Override
    public List<Task> getHistory() {
        return History;
    }
}

