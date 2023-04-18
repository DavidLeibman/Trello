package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomLinkedList {

    private int size = 0;
    private Node head;
    private Node tail;


    Node addLast(Task task) {
        final Node l = tail;
        final Node newNode = new Node(task, l, null);
        tail = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return newNode;

    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
            node.prev = null;
        }
        if (node.next == null) {
            tail = null;
        } else {
            node.next.prev = null;
            node.next = null;
        }

    }


    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.task);
            current = current.next;
        }
        return list;
    }

    class Node {
        public Task task;
        public Node prev;
        public Node next;


        public Node(Task task, Node prev, Node next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }
    }
}
