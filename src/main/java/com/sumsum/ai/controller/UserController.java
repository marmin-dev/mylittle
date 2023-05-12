package com.sumsum.ai.controller;

import com.sumsum.ai.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class UserController {

    private final HttpSession httpSession;

    @GetMapping("/user")
    public ResponseEntity<SessionUser> getUser () {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/callback/google")
    public RedirectView redirectReact() {
        String redirectUrl = "http://localhost:3000";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }

    @GetMapping("/togoogle")
    public RedirectView toGoogle(){
        String redirectUrl = "http://localhost:8080/oauth2/authorization/google";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrl);
        return redirectView;
    }

}
