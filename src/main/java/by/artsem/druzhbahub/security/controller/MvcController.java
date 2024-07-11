package by.artsem.druzhbahub.security.controller;

import by.artsem.druzhbahub.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MvcController {

    private final AccountService accountService;

    @GetMapping("/email/confirm")
    public String confirm(@RequestParam("token") String token) {
        accountService.confirmEmail(token);
        return "Email подтвержден! Вернитесь на страницу входа";
    }
}
