package com.david.bffagendadortarefas.business;

import com.david.bffagendadortarefas.business.dto.in.TaskDTORequest;
import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import com.david.bffagendadortarefas.infrastructure.client.TaskClient;
import com.david.bffagendadortarefas.infrastructure.enums.TaskStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;

    public TaskDTOResponse createTask(TaskDTORequest taskDTO, String token) {
        return taskClient.createTask(taskDTO, token);
    }

    public List<TaskDTOResponse> findTasksByPeriod(LocalDateTime initialDate, LocalDateTime finalDate, String token) {
        return taskClient.findTaskListByPeriod(initialDate, finalDate, token);
    }

    public List<TaskDTOResponse> findTasksByUserEmail(String token) {
        return taskClient.findTasksByUserEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public TaskDTOResponse changeStatus(TaskStatusEnum status, String id, String token) {
        return taskClient.changeStatus(status, id, token);
    }

    public TaskDTOResponse updateTasks(TaskDTORequest taskDTO, String id, String token) {
        return taskClient.updateTasks(taskDTO, id, token);
    }
}
