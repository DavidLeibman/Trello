import Model.Epic;
import Model.Subtask;
import Model.Task;
import Model.TaskStatus;
import Service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
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


        System.out.println(task);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(epic);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask1);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(epic1);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask2);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();

        taskManager.updateTaskStatus(task, TaskStatus.DONE);
        taskManager.updateSubtaskStatus(subtask1, TaskStatus.DONE);
        taskManager.updateSubtaskStatus(subtask, TaskStatus.DONE);
        taskManager.updateSubtaskStatus(subtask2, TaskStatus.IN_PROGRESS);

        System.out.println(task);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(epic);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask1);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(epic1);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(subtask2);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();

        taskManager.removeSubtaskById(subtask.getId());
        taskManager.removeEpicById(epic1.getId());
        System.out.println(epic);
        System.out.println(epic1);
        System.out.println(subtask2);
        System.out.println();


    }
}
