
package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> epicSubtasksId = new ArrayList<>();

    public Epic(String title, String description) {
        super(title,description,TaskStatus.NEW);
        this.setType(Type.EPIC);
    }

    public void setEpicSubtasksId(int id) {
        epicSubtasksId.add(id);
    }

    public ArrayList<Integer> getEpicSubtasksId() {
        return epicSubtasksId;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s",
                getId(),
                getType().toString(),
                getTitle(),
                getStatus().toString(),
                getDescription()
        );
    }


}
