package Model;

public class Subtask extends Task{
    private int epicId;






    public Subtask(String title, String description, TaskStatus status, int epicId){
        setTitle(title);
        setDescription(description);
        this.epicId=epicId;

        setStatus(status);
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status='" + getStatus() + '\'' +
                ", EpicId='"+getEpicId()+'\''+
                '}';
    }

}
