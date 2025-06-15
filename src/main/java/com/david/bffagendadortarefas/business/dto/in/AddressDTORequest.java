package com.david.bffagendadortarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTORequest {

    private String street;
    private Long number;
    private String complement;
    private String city;
    private String state;
    private String cep;
}
