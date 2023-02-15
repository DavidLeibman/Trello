
package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> epicSubtasksId = new ArrayList<>();

    public Epic(String title, String description) {
        super(title,description,TaskStatus.NEW);
    }

    public void setEpicSubtasksId(int id) {
        epicSubtasksId.add(id);
    }

    public ArrayList<Integer> getEpicSubtasksId() {
        return epicSubtasksId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status='" + getStatus() + '\'' +
                ", epicSubtasksId.size()= '" + epicSubtasksId.size() + '\'' +
                '}';
    }


}
