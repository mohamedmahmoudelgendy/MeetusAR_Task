package MeetusAR.Task.DTOs;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String status;
}

