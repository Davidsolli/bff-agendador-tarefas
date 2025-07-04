package com.david.bffagendadortarefas.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {

    private String name;
    private String email;
    private String password;
    private List<AddressDTOResponse> address;
    private List<PhoneDTOResponse> phone;
}
