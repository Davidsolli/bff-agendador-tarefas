package com.david.bffagendadortarefas.business;

import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import com.david.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void emailSender(TaskDTOResponse taskDTOResponse) {
        emailClient.sendEmail(taskDTOResponse);
    }
}
