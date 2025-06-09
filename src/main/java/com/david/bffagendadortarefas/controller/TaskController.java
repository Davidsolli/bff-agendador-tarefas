package com.david.bffagendadortarefas.controller;

import com.david.bffagendadortarefas.business.TaskService;
import com.david.bffagendadortarefas.business.dto.in.TaskDTORequest;
import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import com.david.bffagendadortarefas.infrastructure.enums.TaskStatusEnum;
import com.david.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Tag(name = "Task", description = "Cadastro de tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuário", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TaskDTOResponse> createTask(
            @RequestBody TaskDTORequest taskDTO,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(taskService.createTask(taskDTO, token));
    }

    @GetMapping("/events")
    @Operation(summary = "Buscar tarefas por período", description = "Buca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TaskDTOResponse>> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(taskService.findTasksByPeriod(initialDate, finalDate, token));
    }

    @GetMapping
    @Operation(
            summary = "Encontrar lista de tarefas pelo email do usuário",
            description = "Encontra uma lista de tarefas pelo email do usuário"
    )
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TaskDTOResponse>> findTasksByUserEmail(
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(taskService.findTasksByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa por id", description = "Deleta tarefa por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deleteTaskById(
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar status de tarefa", description = "Altera status de tarefa")
    @ApiResponse(responseCode = "200", description = "Status de tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TaskDTOResponse> changeStatus(
            @RequestParam("status") TaskStatusEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(taskService.changeStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar dados de tarefas", description = "Altera dados de tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefa alteradas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TaskDTOResponse> updateTasks(
            @RequestBody TaskDTORequest taskDTO,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(taskService.updateTasks(taskDTO, id, token));
    }
}
