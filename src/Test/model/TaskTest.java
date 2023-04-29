package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import service.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    public void shouldAddPublication(){
        Task task = new Task("x","y",TaskStatus.NEW);
        task.setId(0);
        TaskManager taskManager = new InMemoryTaskManager();
        taskManager.createTask(task);
        Assertions.assertEquals(task,taskManager.getTaskById(task.getId()));
    }

}