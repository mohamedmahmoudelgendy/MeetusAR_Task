package MeetusAR.Task.Controller;

import MeetusAR.Task.Entity.Task;
import MeetusAR.Task.Entity.User;
import MeetusAR.Task.Service.TaskService;
import MeetusAR.Task.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody Task task) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        task.setUser(userOpt.get());
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> list(@PathVariable Long userId) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<Task> tasks = taskService.getTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{userId}/{taskId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @PathVariable Long taskId,
                                    @RequestBody Task task) {
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Optional<Task> existingTask = taskService.getTaskById(taskId);
        if (existingTask.isEmpty()) {
            return ResponseEntity.status(404).body("Task not found");
        }

        if (!existingTask.get().getUser().getId().equals(userId)) {
            return ResponseEntity.status(403).body("Cannot update another user's task");
        }

        task.setId(taskId);
        task.setUser(userOpt.get());
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<?> delete(@PathVariable Long userId, @PathVariable Long taskId) {
        Optional<Task> existingTask = taskService.getTaskById(taskId);
        if (existingTask.isEmpty()) {
            return ResponseEntity.status(404).body("Task not found");
        }

        if (!existingTask.get().getUser().getId().equals(userId)) {
            return ResponseEntity.status(403).body("Cannot delete another user's task");
        }

        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
