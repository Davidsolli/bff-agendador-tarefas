package com.david.bffagendadortarefas.infrastructure.client;

import com.david.bffagendadortarefas.business.dto.AddressDTO;
import com.david.bffagendadortarefas.business.dto.PhoneDTO;
import com.david.bffagendadortarefas.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTO findByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    String login(@RequestBody UserDTO userDTO);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTO updateUserData(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTO updateAddress(
            @RequestBody AddressDTO addressDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping("/phone")
    PhoneDTO updatePhone(
            @RequestBody PhoneDTO phoneDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PostMapping("/address")
    AddressDTO newAddress(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressDTO addressDTO
    );

    @PostMapping("/phone")
    PhoneDTO newPhone(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneDTO phoneDTO
    );
}
