package by.artsem.druzhbahub.security.controller;

import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.ConfirmationToken;
import by.artsem.druzhbahub.security.model.dto.*;
import by.artsem.druzhbahub.security.service.AccountService;
import by.artsem.druzhbahub.security.service.ConfirmationTokenService;
import by.artsem.druzhbahub.security.service.EmailService;
import by.artsem.druzhbahub.security.service.EmailTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
@CrossOrigin
public class SecurityController {

    private final ConfirmationTokenService confirmationTokenService;

    private final AccountService accountService;

    private final EmailTextService emailTextService;

    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationRequestDTO dto) {
        Account account = accountService.createAccountAndProfile(dto);
        ConfirmationToken token = confirmationTokenService.create(account);
        emailTextService.createEmailMessage(token.getToken());
        emailService.sendEmail(
                account.getEmail(),
                emailTextService.getSubject(),
                emailTextService.getBody()
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return new ResponseEntity<>(accountService.authenticate(dto), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<HttpStatus> confirm(@RequestParam("token") String token) {
        accountService.confirmEmail(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/current-user")
    public ResponseEntity<CurrentUserResponse> getCurrentUser() {
        return new ResponseEntity<>(accountService.getCurrentUser(), HttpStatus.OK);
    }

    @GetMapping("/is-confirmed/{id}")
    public ResponseEntity<AccountIsEmailConfirmedDto> isEmailConfirmed(@PathVariable Long id) {
        return new ResponseEntity<>(
                AccountIsEmailConfirmedDto.builder()
                        .isEmailConfirmed(accountService.isEmailConfirmed(id))
                        .build(),
                HttpStatus.OK
        );
    }
}