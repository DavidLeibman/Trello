package model;

public class Subtask extends Task {
    private int epicId;

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    public Subtask(String title, String description, TaskStatus status, int epicId) {
        setTitle(title);
        setDescription(description);
        this.epicId = epicId;
        this.setType(Type.SUBTASK);

        setStatus(status);
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s",
                getId(),
                getType().toString(),
                getTitle(),
                getStatus().toString(),
                getDescription(),
                getEpicId()

        );
    }

}
