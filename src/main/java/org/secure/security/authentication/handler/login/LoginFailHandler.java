package org.secure.security.authentication.handler.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.secure.security.common.web.model.Result;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * AbstractAuthenticationProcessingFilter抛出AuthenticationException异常后，会跑到这里来
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String errorMessage = exception.getMessage();
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    PrintWriter writer = response.getWriter();
    Result responseData = Result.builder()
        .data(null)
        .code("login.fail")
        .message(errorMessage)
        .build();
    ObjectMapper objectMapper = new ObjectMapper();
    writer.print(objectMapper.writeValueAsString(responseData));
    writer.flush();
    writer.close();
  }
}
