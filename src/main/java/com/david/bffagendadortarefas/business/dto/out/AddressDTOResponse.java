package com.david.bffagendadortarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String street;
    private Long number;
    private String complement;
    private String city;
    private String state;
    private String cep;
}
