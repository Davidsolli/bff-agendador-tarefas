package com.david.bffagendadortarefas.infrastructure.client;

import com.david.bffagendadortarefas.business.dto.in.TaskDTORequest;
import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import com.david.bffagendadortarefas.infrastructure.enums.TaskStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TaskClient {

    @PostMapping
    TaskDTOResponse createTask(
            @RequestBody TaskDTORequest taskDTO,
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/events")
    List<TaskDTOResponse> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token
    );

    @GetMapping
    List<TaskDTOResponse> findTasksByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTOResponse changeStatus(
            @RequestParam("status") TaskStatusEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping
    TaskDTOResponse updateTasks(
            @RequestBody TaskDTORequest taskDTO,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );
}
