import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.Managers;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("Пойти на работу", "", TaskStatus.NEW);
        taskManager.createTask(task);

        Epic epic = new Epic("Поменять валюту", "обмен рублей на доллары");
        taskManager.createEpic(epic);
        Subtask subtask = new Subtask("взять кошелек", "", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("пойти в банк", "", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask1);

        Epic epic1 = new Epic("Сходить в магазин", "купить продукты на неделю");
        taskManager.createEpic(epic1);
        Subtask subtask2 = new Subtask("Одеться", "", TaskStatus.NEW, epic1.getId());
        taskManager.createSubtask(subtask2);


        taskManager.getTaskById(task.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getEpicById(epic.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask1.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getEpicById(epic1.getId());
        System.out.println();
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getEpicById(epic1.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
        taskManager.getSubtaskById(subtask2.getId());
        System.out.println(taskManager.getHistory());
        System.out.println();
    }
}