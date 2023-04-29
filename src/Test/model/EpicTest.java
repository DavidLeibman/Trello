package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private final TaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void returnStatusNewIfSubTaskListIsEmpty() {
        Epic epic = new Epic("x", "y");
        taskManager.createEpic(epic);
        Assertions.assertEquals(TaskStatus.NEW,epic.getStatus());
    }
    @Test
    public void returnStatusNewIfAllSubtasksIsNew(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic("x", "y");
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("x1", "y1", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("x2", "y2", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask2);
        Assertions.assertEquals(TaskStatus.NEW,epic.getStatus());
    }
    @Test
    public void returnStatusDoneIfAllSubtasksIsDone(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic("x", "y");
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("x1", "y1", TaskStatus.DONE, epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("x2", "y2", TaskStatus.DONE, epic.getId());
        taskManager.createSubtask(subtask2);
        Assertions.assertEquals(TaskStatus.DONE,epic.getStatus());
    }
    @Test
    public void returnStatusInProgressIfSomeSubtasksIsDoneAndSomeIsNew(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic("x", "y");
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("x1", "y1", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("x2", "y2", TaskStatus.DONE, epic.getId());
        taskManager.createSubtask(subtask2);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS,epic.getStatus());
    }
    @Test
    public void returnStatusInProgressIfAllSubtasksIsInProgress(){
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic("x", "y");
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("x1", "y1", TaskStatus.IN_PROGRESS, epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("x2", "y2", TaskStatus.IN_PROGRESS, epic.getId());
        taskManager.createSubtask(subtask2);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS,epic.getStatus());
    }

}