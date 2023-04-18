import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.Managers;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        test.test1();
    }
}

class test {
    public static void test1() {
        TaskManager taskManager = Managers.getDefault();
        Epic epic = new Epic("Поменять валюту", "обмен рублей на доллары");
        taskManager.createEpic(epic);
        Subtask subtask = new Subtask("взять кошелек", "", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("пойти в банк", "", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("Подойти к кассе", "", TaskStatus.NEW, epic.getId());
        taskManager.createSubtask(subtask2);
        Task task =new Task("","",TaskStatus.NEW);
        taskManager.createTask(task);
        System.out.println(subtask);
//        Epic epic1 = new Epic("Сходить в магазин", "купить продукты на неделю");
//        taskManager.createEpic(epic1);
        taskManager.getEpicById(epic.getId());
        taskManager.getSubtaskById(subtask.getId());
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getTaskById(task.getId());
//        taskManager.getEpicById(epic1.getId());
//        System.out.println(taskManager.getHistory());
//        taskManager.getEpicById(epic.getId());
//
//        System.out.println(taskManager.getHistory());
//        taskManager.removeEpicById(epic.getId());
//        System.out.println(taskManager.getHistory());
    }

}