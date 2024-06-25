package by.artsem.druzhbahub.security.controller;

import by.artsem.druzhbahub.security.model.dto.AccountResponseDTO;
import by.artsem.druzhbahub.security.model.dto.EmailUpdateRequestDTO;
import by.artsem.druzhbahub.security.model.dto.PasswordUpdateRequestDTO;
import by.artsem.druzhbahub.security.model.dto.RoleUpdateRequestDTO;
import by.artsem.druzhbahub.security.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<HttpStatus> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody PasswordUpdateRequestDTO passwordUpdateRequestDTO
    ) {
        accountService.updatePassword(id, passwordUpdateRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<HttpStatus> updateEmail(
            @PathVariable Long id,
            @Valid @RequestBody EmailUpdateRequestDTO emailUpdateRequestDTO
    ) {
        accountService.updateEmail(id, emailUpdateRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<HttpStatus> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequestDTO roleUpdateRequestDTO
    ) {
        accountService.updateRole(id, roleUpdateRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
