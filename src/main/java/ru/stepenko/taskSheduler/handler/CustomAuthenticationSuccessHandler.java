package ru.stepenko.taskSheduler.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Отвечает за успешный вход в систему
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().flush();
        response.getWriter().close();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
