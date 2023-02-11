package Service;

public class Managers<T extends TaskManager> {
    private T taskManager;
    public T getDefault(){
        return null;
    }
    public HistoryManager getDefaultHistory(){
     return null;
    }
}
