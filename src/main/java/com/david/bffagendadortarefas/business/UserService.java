package com.david.bffagendadortarefas.business;

import com.david.bffagendadortarefas.business.dto.AddressDTO;
import com.david.bffagendadortarefas.business.dto.PhoneDTO;
import com.david.bffagendadortarefas.business.dto.UserDTO;
import com.david.bffagendadortarefas.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTO createUser(UserDTO userDTO) {
        return userClient.createUser(userDTO);
    }

    public String userLogin(UserDTO userDTO) {
        return userClient.login(userDTO);
    }

    public UserDTO findUserByEmail(String email, String token) {
        return userClient.findByEmail(email, token);
    }

    public void deleteUserByEmail(String email, String token) {
        userClient.deleteUserByEmail(email, token);
    }

    public UserDTO updateUserData(String token, UserDTO userDTO) {
        return userClient.updateUserData(userDTO, token);
    }

    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO, String token) {
        return userClient.updateAddress(addressDTO, addressId, token);
    }

    public PhoneDTO updatePhone(Long phoneId, PhoneDTO phoneDTO, String token) {
        return userClient.updatePhone(phoneDTO, phoneId, token);
    }

    public AddressDTO newAddress(String token, AddressDTO addressDTO) {
        return userClient.newAddress(token, addressDTO);
    }

    public PhoneDTO newPhone(String token, PhoneDTO phoneDTO) {
        return userClient.newPhone(token, phoneDTO);
    }
}
