package by.artsem.druzhbahub.security.controller;

import by.artsem.druzhbahub.security.model.Account;
import by.artsem.druzhbahub.security.model.ConfirmationToken;
import by.artsem.druzhbahub.security.model.dto.RegistrationRequestDTO;
import by.artsem.druzhbahub.security.service.AccountService;
import by.artsem.druzhbahub.security.service.ConfirmationTokenService;
import by.artsem.druzhbahub.security.service.EmailService;
import by.artsem.druzhbahub.security.service.EmailTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final ConfirmationTokenService confirmationTokenService;

    private final AccountService accountService;

    private final EmailTextService emailTextService;

    private final EmailService emailService;

    @PostMapping
    public void register(@RequestBody RegistrationRequestDTO dto) {
        Account account = accountService.createAccountAndProfile(dto);
        ConfirmationToken token = confirmationTokenService.createToken(account);
        emailTextService.createEmailMessage(token.getToken());
        emailService.sendEmail(
                account.getEmail(),
                emailTextService.getSubject(),
                emailTextService.getBody()
        );
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        accountService.confirmEmail(token);
        return "Email confirmed successfully";
    }
}