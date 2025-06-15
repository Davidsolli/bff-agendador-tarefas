package com.david.bffagendadortarefas.infrastructure.client;

import com.david.bffagendadortarefas.business.dto.in.AddressDTORequest;
import com.david.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.david.bffagendadortarefas.business.dto.in.PhoneDTORequest;
import com.david.bffagendadortarefas.business.dto.in.UserDTORequest;
import com.david.bffagendadortarefas.business.dto.out.AddressDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.PhoneDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.UserDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping
    UserDTOResponse findByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse createUser(@RequestBody UserDTORequest userDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO userDTORequest);

    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUserData(@RequestBody UserDTORequest userDTORequest, @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(
            @RequestBody AddressDTORequest addressDTORequest,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping("/phone")
    PhoneDTOResponse updatePhone(
            @RequestBody PhoneDTORequest phoneDTORequest,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PostMapping("/address")
    AddressDTOResponse newAddress(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressDTORequest addressDTORequest
    );

    @PostMapping("/phone")
    PhoneDTOResponse newPhone(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneDTORequest phoneDTORequest
    );

    @GetMapping("/address/{cep}")
    ViaCepDTOResponse findAddressData(@PathVariable("cep") String cep);
}
