package com.david.bffagendadortarefas.controller;

import com.david.bffagendadortarefas.business.UserService;
import com.david.bffagendadortarefas.business.dto.AddressDTO;
import com.david.bffagendadortarefas.business.dto.PhoneDTO;
import com.david.bffagendadortarefas.business.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Cadastro e login de usuários")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuários por email", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTO> findUserByEmail(
            @RequestParam(value = "email", required = false) String email,
            @RequestHeader("Authorization") String token
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
            @RequestHeader("Authorization") String token
    ) {
        userService.deleteUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário", description = "Atualiza dados de usuários")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UserDTO> updateUserData(
            @RequestBody UserDTO userDTO,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }

    @PutMapping("/address")
    @Operation(summary = "Atualizar endereço do usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<AddressDTO> updateAddress(
            @RequestBody AddressDTO addressDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(userService.updateAddress(id, addressDTO, token));
    }

    @PutMapping("/phone")
    @Operation(summary = "Atualizar telefone do usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<PhoneDTO> updatePhone(
            @RequestBody PhoneDTO phoneDTO,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(userService.updatePhone(id, phoneDTO, token));
    }

    @PostMapping("/address")
    @Operation(summary = "Salvar endereço do usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<AddressDTO> newAddress(
            @RequestHeader("Authorization") String token,
            @RequestBody AddressDTO addressDTO
    ) {
        return ResponseEntity.ok(userService.newAddress(token, addressDTO));
    }

    @PostMapping("/phone")
    @Operation(summary = "Salvar telefone do usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<PhoneDTO> newPhone(
            @RequestHeader("Authorization") String token,
            @RequestBody PhoneDTO phoneDTO
    ) {
        return ResponseEntity.ok(userService.newPhone(token, phoneDTO));
    }
}
