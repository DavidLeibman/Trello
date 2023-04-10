package service;

import model.Task;

import java.util.*;
import service.CustomLinkedList.Node;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList customLinkedList;
    private final Map<Integer, Node> nodeMap;

    public InMemoryHistoryManager() {
        this.customLinkedList = new CustomLinkedList();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        if (nodeMap.get(task.getId())!= null)
            remove(task.getId());
        Node newNode = customLinkedList.addLast(task);
        nodeMap.put(task.getId(), newNode);
    }

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks();
    }

    @Override
    public void remove(int id) {
        Node nodeToRemove= nodeMap.get(id);
        if (nodeToRemove==null)
            return;
        customLinkedList.removeNode(nodeToRemove);
        nodeMap.remove(id);
    }

}


