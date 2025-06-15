package com.david.bffagendadortarefas.infrastructure.client;

import com.david.bffagendadortarefas.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "notificacao.url")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse taskDTO);
}
