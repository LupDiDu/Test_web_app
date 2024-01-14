package com.testweb.application.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

@Route("login")
@AnonymousAllowed
@Push
public class GoogleSignInView extends VerticalLayout {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public GoogleSignInView(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        TextField usernameField = new TextField("Имя пользователя");
        TextField passwordField = new TextField("Пароль");

        Button loginButton = new Button("Войти");
        loginButton.addClickListener(event -> {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        usernameField.getValue(), passwordField.getValue()));
                Notification.show("Успешный вход в систему!");
            } catch (AuthenticationException e) {
                Notification.show("Ошибка входа: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });

        add(usernameField, passwordField, loginButton);
    }
}