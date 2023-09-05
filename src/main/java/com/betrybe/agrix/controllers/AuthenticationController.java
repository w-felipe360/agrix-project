package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** controller do auth. */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /** ligação POST com autenticação. */
  @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authenticationDto) {
    try {
      Authentication auth = authenticateUser(authenticationDto.username(),
                    authenticationDto.password());
      Person person = (Person) auth.getPrincipal();

      String token = generateToken(person);

      TokenDto tokenDto = new TokenDto(token);
      return ResponseEntity.ok(tokenDto);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }

  private Authentication authenticateUser(String username, String password) {
    UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
    return authenticationManager.authenticate(authenticationToken);
  }

  private String generateToken(Person person) {
    return tokenService.generateToken(person);
  }
}