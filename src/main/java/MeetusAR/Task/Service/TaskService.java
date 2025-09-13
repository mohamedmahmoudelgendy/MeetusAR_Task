package MeetusAR.Task.Service;

import MeetusAR.Task.Entity.Task;
import MeetusAR.Task.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task createTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getTasks(Long userId) {
        return repo.findByUserId(userId);
    }

    public Optional<Task> getTaskById(Long taskId) {
        return repo.findById(taskId);
    }

    public Task updateTask(Task task) {
        return repo.save(task);
    }

    public void deleteTask(Long taskId) {
        repo.deleteById(taskId);
    }
}
