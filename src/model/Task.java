package model;

public class Task {
    private String title;
    private String description;
    private int id;
    private TaskStatus status;
    private Type type;

    public Task() {
    }



    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.type = Type.TASK;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
