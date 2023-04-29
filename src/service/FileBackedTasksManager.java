package service;

import model.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private final File saveFile;


    public FileBackedTasksManager(String filePath) {
        super();
        saveFile = new File(filePath);
    }


    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void removeTaskById(int id) {
        super.removeTaskById(id);
        save();
    }

    @Override
    public void removeEpicById(int id) {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubtaskById(int id) {
        super.removeSubtaskById(id);
        save();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            writer.write("id,type,name,status,description,epic\n");
            for (Task task : getAllTasksList()) {
                writer.write(task.toString());
                writer.newLine();
            }
            for (Task epic : getAllEpicsList()) {
                writer.write(epic.toString());
                writer.newLine();
            }
            for (Task subtask : getAllSubtasksList()) {
                writer.write(subtask.toString());
                writer.newLine();
            }
            writer.newLine();
            writer.write(historyToString(getHistoryManager()));
            writer.flush();
        } catch (IOException e) {
            throw new ManagerSaveException("Unable to save tasks to file: " + e.getMessage());
        }
    }


    public static Task fromString(String value) {
        String[] tokens = value.split(",");
        int id = Integer.parseInt(tokens[0]);
        Type type = Type.valueOf(tokens[1]);
        String title = tokens[2];
        TaskStatus status = TaskStatus.valueOf(tokens[3]);
        String description = tokens[4];
        Task task;
        if (type.equals(Type.TASK)) {
            task = new Task(title, description, status);
            task.setId(id);
            task.setType(type);
        } else if (type.equals(Type.EPIC)) {
            task = new Epic(title, description);
        } else {
            task = new Subtask(title, description, status, Integer.parseInt(tokens[5]));
        }
        return task;
    }

    public static FileBackedTasksManager loadFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        FileBackedTasksManager fileManger = new FileBackedTasksManager(file.getPath());
        String line;

        while ((line = reader.readLine()) != null) {
            Task task = fromString(line);
            if (task instanceof Subtask) {
                fileManger.createSubtask((Subtask) task);
            } else if (task instanceof Epic) {
                fileManger.createEpic((Epic) task);
            } else {
                fileManger.createTask(task);
            }
            String historyLine = reader.readLine();
            if (historyLine != null) {
                List<Integer> history = historyFromString(historyLine);
                for (int id : history) {
                    fileManger.getTaskById(id);
                }
            }
        }

        reader.close();
        return new FileBackedTasksManager(file.getPath());
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> historyIds = new ArrayList<>();
        String[] lines = value.split(",");
        for (String line : lines) {
            Task task = fromString(line);
            historyIds.add(task.getId());
        }
        return historyIds;
    }

    public static String historyToString(HistoryManager manager) {
        List<Task> history = manager.getHistory();
        List<Integer> list = new ArrayList<>();
        for (Task task : history) {
            list.add(task.getId());
        }
        Iterator<Integer> iterator = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            sb.append(number);
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager("src/FilesTest/TestFile.csv");
        Epic epic = new Epic("Поменять валюту", "обмен рублей на доллары");
        fileBackedTasksManager.createEpic(epic);
        fileBackedTasksManager.getEpicById(epic.getId());
        Subtask subtask = new Subtask("взять кошелек", "", TaskStatus.NEW, epic.getId());
        fileBackedTasksManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("пойти в банк", "", TaskStatus.NEW, epic.getId());
        fileBackedTasksManager.createSubtask(subtask1);
        fileBackedTasksManager.getSubtaskById(subtask1.getId());

        Subtask subtask2 = new Subtask("Подойти к кассе", "", TaskStatus.NEW, epic.getId());
        fileBackedTasksManager.createSubtask(subtask2);
        Task task = new Task("", "", TaskStatus.NEW);
        fileBackedTasksManager.createTask(task);



    }
}


