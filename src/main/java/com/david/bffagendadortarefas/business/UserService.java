package com.david.bffagendadortarefas.business;

import com.david.bffagendadortarefas.business.dto.in.AddressDTORequest;
import com.david.bffagendadortarefas.business.dto.in.LoginRequest;
import com.david.bffagendadortarefas.business.dto.in.PhoneDTORequest;
import com.david.bffagendadortarefas.business.dto.in.UserDTORequest;
import com.david.bffagendadortarefas.business.dto.out.AddressDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.PhoneDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.UserDTOResponse;
import com.david.bffagendadortarefas.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTOResponse createUser(UserDTORequest userDTORequest) {
        return userClient.createUser(userDTORequest);
    }

    public String userLogin(LoginRequest userDTORequest) {
        return userClient.login(userDTORequest);
    }

    public UserDTOResponse findUserByEmail(String email, String token) {
        return userClient.findByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token) {
        userClient.deleteUserByEmail(email, token);
    }

    public UserDTOResponse updateUserData(String token, UserDTORequest userDTORequest) {
        return userClient.updateUserData(userDTORequest, token);
    }

    public AddressDTOResponse updateAddress(Long addressId, AddressDTORequest addressDTORequest, String token) {
        return userClient.updateAddress(addressDTORequest, addressId, token);
    }

    public PhoneDTOResponse updatePhone(Long phoneId, PhoneDTORequest phoneDTORequest, String token) {
        return userClient.updatePhone(phoneDTORequest, phoneId, token);
    }

    public AddressDTOResponse newAddress(String token, AddressDTORequest addressDTORequest) {
        return userClient.newAddress(token, addressDTORequest);
    }

    public PhoneDTOResponse newPhone(String token, PhoneDTORequest phoneDTORequest) {
        return userClient.newPhone(token, phoneDTORequest);
    }
}
