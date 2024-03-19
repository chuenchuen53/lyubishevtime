package com.example.lyubishevtime.interceptor;

import com.example.lyubishevtime.auth.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final JwtHelper jwtHelper;

    public LoginInterceptor(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws IOException {
        if (Objects.equals(request.getMethod(), "OPTIONS")) {
            return true;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") && !jwtHelper.isExpiration(authorizationHeader)) {
            Integer userId = jwtHelper.getUserId(authorizationHeader).intValue();
            request.setAttribute("userId", userId);
            return true;
        }

        Map<String, String> result = Map.of("errorMessage", "Not login");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
