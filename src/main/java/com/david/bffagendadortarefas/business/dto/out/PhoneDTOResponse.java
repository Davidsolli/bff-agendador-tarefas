package com.david.bffagendadortarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTOResponse {

    private Long id;
    private String number;
    private String ddd;
}
