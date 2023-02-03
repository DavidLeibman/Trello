package Service;

import Model.Epic;
import Model.Subtask;
import Model.Task;
import Model.TaskStatus;


import java.util.*;

public class TaskManager {
    private int uniqueID;

    HashMap<Integer, Task> tasksMapById = new HashMap<>();

    public ArrayList<Task> getTasksList() {
        ArrayList<Task> list = new ArrayList<>();
        for (Integer tasksMapByIdKey : tasksMapById.keySet()) {
            list.add(tasksMapById.get(tasksMapByIdKey));
        }
        return list;
    }

    public void removeAllTasks() {
        tasksMapById.clear();
    }

    public void removerTasksById(int id) {
        if (tasksMapById.get(id).getClass() == Task.class) {
            tasksMapById.remove(id);
        } else if (tasksMapById.get(id).getClass() == Epic.class) {
            Epic epic = (Epic) (tasksMapById.get(id));
            for (Integer subtaskId : epic.getEpicSubtasksId()) {
                tasksMapById.remove(subtaskId);
            }
            epic.getEpicSubtasksId().clear();
            tasksMapById.remove(id);
        } else if (tasksMapById.get(id).getClass() == Subtask.class) {
            Subtask subtask = (Subtask) (tasksMapById.get(id));
            Epic epic = (Epic) (tasksMapById.get(subtask.getEpicId()));
            epic.getEpicSubtasksId().remove((Integer) id);
            tasksMapById.remove(id);

        }

    }

    public Task getById(int id) {
        return tasksMapById.get(id);
    }


    public void create(Task task) {
        if (task.getClass() == Task.class) {
            task.setId(uniqueID++);
            tasksMapById.put(task.getId(), task);

        } else if (task.getClass() == Epic.class) {
            task.setId(uniqueID++);
            Epic epic = (Epic) task;
            if (((Epic) task).getEpicSubtasksId().isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
            }
            tasksMapById.put(task.getId(), task);

        } else if (task.getClass() == Subtask.class) {
            if (tasksMapById.containsKey(((Subtask) task).getEpicId())) {
                task.setId(uniqueID++);
                tasksMapById.put(task.getId(), task);
                Epic epic = (Epic) tasksMapById.get(((Subtask) task).getEpicId());
                epic.setEpicSubtasksId(task.getId());
                setEpicStatus(epic);

            }
        }
    }


    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Subtask> list = new ArrayList<>();
        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            list.add((Subtask) tasksMapById.get(subtaskId));
        }
        return list;
    }

    private void setEpicStatus(Epic epic) {

        Set<TaskStatus> epicSubtasksStatus = new HashSet<>();

        for (Integer subtaskId : epic.getEpicSubtasksId()) {
            Subtask subtask = (Subtask) (tasksMapById.get(subtaskId));
            epicSubtasksStatus.add(subtask.getStatus());
        }
        if (epicSubtasksStatus.size() <= 1 & epicSubtasksStatus.contains(TaskStatus.NEW)) {
            epic.setStatus(TaskStatus.NEW);
        } else if (epicSubtasksStatus.size() <= 1 & epicSubtasksStatus.contains(TaskStatus.DONE)) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void setStatus(Task task, TaskStatus status) {
        if (task.getClass() == Task.class) {
            task.setStatus(status);
        } else if (task.getClass() == Subtask.class) {
            task.setStatus(status);
            Subtask subtask = (Subtask) task;
            setEpicStatus((Epic) (tasksMapById.get(subtask.getEpicId())));
        }
    }

}
