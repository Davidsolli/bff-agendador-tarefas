package com.david.bffagendadortarefas.business;

import com.david.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import com.david.bffagendadortarefas.infrastructure.enums.TaskStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;
    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.hour}")
    public void findTasksNextHours() {

        String token = login(converterToRequestDTO());
        log.info("Iniciada a busaca de tarefas");
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFiveMin = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TaskDTOResponse> taskList = taskService.findTasksByPeriod(futureHour, futureHourPlusFiveMin, token);
        log.info("Tarefas encontradas{}", taskList);
        taskList.forEach(task -> {
            emailService.emailSender(task);
            log.info("Email enviado para o usuário{}", task.getUserEmail());
            taskService.changeStatus(TaskStatusEnum.NOTIFIED, task.getId(), token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginRequestDTO loginRequest) {
        return userService.userLogin(loginRequest);
    }

    public LoginRequestDTO converterToRequestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}
