package Service;

import Model.Epic;
import Model.Subtask;
import Model.Task;
import Model.TaskStatus;


import java.util.*;

public class TaskManager {
    private int uniqueID;

    HashMap<Integer, Task> tasksMapById = new HashMap<>();
    HashMap<Integer, Subtask> subtasksMapById = new HashMap<>();
    HashMap<Integer, Epic> epicsMapById = new HashMap<>();

    public ArrayList<Task> getAllTasksList() {

        return new ArrayList<>(tasksMapById.values());
    }

    public ArrayList<Task> getAllEpicsList() {

        return new ArrayList<>(epicsMapById.values());
    }

    public ArrayList<Task> getAllSubtasksList() {

        return new ArrayList<>(subtasksMapById.values());
    }

    public void removeAllTasks() {
        tasksMapById.clear();
    }

    public void removeAllEpics() {
        epicsMapById.clear();
    }

    public void removeAllSubtasks() {
        subtasksMapById.clear();
        for (Epic epic : epicsMapById.values()) {
            epic.setStatus(TaskStatus.NEW);
            epic.getEpicSubtasksId().clear();
        }
    }

    public void removeTaskById(int id) {

        tasksMapById.remove(id);
    }

    public void removeEpicById(int id) {
        Epic epic = epicsMapById.get(id);
        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            Subtask subtask=subtasksMapById.get(subtaskId);
            subtask.setEpicId(0);
            subtasksMapById.remove(subtaskId);
        }
        epic.getEpicSubtasksId().clear();
        epicsMapById.remove(id);
    }

    public void removeSubtaskById(int id) {
        Subtask subtask = subtasksMapById.get(id);
        Epic epic = epicsMapById.get(subtask.getEpicId());
        epic.getEpicSubtasksId().remove((Integer) id);
        updateEpicStatus(epic);
        subtask.setEpicId(0);//обнуление EpicId  у Subtask так как они больше не связаны
        subtasksMapById.remove(id);
    }

    public Task getTaskById(int id) {
        return tasksMapById.get(id);
    }


    public Task getSubtaskById(int id) {
        return subtasksMapById.get(id);
    }

    public Task getEpicById(int id) {
        return epicsMapById.get(id);
    }

    public void createTask(Task task) {
        task.setId(uniqueID++);
        tasksMapById.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(uniqueID++);
        if (epic.getEpicSubtasksId().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        }
        epicsMapById.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        if (epicsMapById.containsKey(subtask.getEpicId())) {
            subtask.setId(uniqueID++);
            subtasksMapById.put(subtask.getId(), subtask);
            Epic epic = epicsMapById.get(subtask.getEpicId());
            epic.setEpicSubtasksId(subtask.getId());
            updateEpicStatus(epic);
        }
    }

    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Subtask> list = new ArrayList<>();
        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            list.add(subtasksMapById.get(subtaskId));
        }
        return list;
    }

    private void updateEpicStatus(Epic epic) {
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

    public void updateTaskStatus(Task task, TaskStatus status) {
        task.setStatus(status);
    }

    public void updateSubtaskStatus(Subtask subtask, TaskStatus status) {
        subtask.setStatus(status);
        Epic epic = epicsMapById.get(subtask.getEpicId());
        updateEpicStatus(epic);
    }
}


