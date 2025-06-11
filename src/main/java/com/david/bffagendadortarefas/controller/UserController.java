package com.david.bffagendadortarefas.controller;

import com.david.bffagendadortarefas.business.UserService;
import com.david.bffagendadortarefas.business.dto.in.AddressDTORequest;
import com.david.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.david.bffagendadortarefas.business.dto.in.PhoneDTORequest;
import com.david.bffagendadortarefas.business.dto.in.UserDTORequest;
import com.david.bffagendadortarefas.business.dto.out.AddressDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.PhoneDTOResponse;
import com.david.bffagendadortarefas.business.dto.out.UserDTOResponse;
import com.david.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.createUser(userDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody LoginRequestDTO userDTORequest) {
        return userService.userLogin(userDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuários por email", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTOResponse> findUserByEmail(
            @RequestParam(value = "email", required = false) String email,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(userService.findUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuário", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deleteUserByEmail(
            @PathVariable String email,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário", description = "Atualiza dados de usuários")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTOResponse> updateUserData(
            @RequestBody UserDTORequest userDTORequest,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(userService.updateUserData(token, userDTORequest));
    }

    @PutMapping("/address")
    @Operation(summary = "Atualizar endereço do usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<AddressDTOResponse> updateAddress(
            @RequestBody AddressDTORequest addressDTORequest,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTORequest, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Atualizar telefone do usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<PhoneDTOResponse> updatePhone(
            @RequestBody PhoneDTORequest phoneDTORequest,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(userService.updatePhone(id, phoneDTORequest, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Salvar endereço do usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<AddressDTOResponse> newAddress(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody AddressDTORequest addressDTORequest
    ) {
        return ResponseEntity.ok(userService.newAddress(token, addressDTORequest));
    }

    @PostMapping("/phone")
    @Operation(summary = "Salvar telefone do usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<PhoneDTOResponse> newPhone(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody PhoneDTORequest phoneDTORequest
    ) {
        return ResponseEntity.ok(userService.newPhone(token, phoneDTORequest));
    }
}
