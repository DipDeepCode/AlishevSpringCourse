package ru.ddc.securityapp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ddc.securityapp.dto.AuthenticationDto;
import ru.ddc.securityapp.dto.PersonDto;
import ru.ddc.securityapp.model.Person;
import ru.ddc.securityapp.security.JwtUtil;
import ru.ddc.securityapp.service.RegistrationService;
import ru.ddc.securityapp.util.PersonValidator;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JwtUtil jwtUtil;
    private final ModelMapper mapper;
    private final AuthenticationManager authManager;

    public AuthController(PersonValidator personValidator,
                          RegistrationService registrationService,
                          JwtUtil jwtUtil,
                          ModelMapper mapper,
                          AuthenticationManager authManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.authManager = authManager;
    }

//    @GetMapping("/login")
//    public String loginPage() {
//        return "auth/login";
//    }
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") Person person) {
//        return "auth/registration";
//    }

    @PostMapping("/login")
    public Map<String, Object> performLogin(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                authenticationDto.getUsername(),
                authenticationDto.getPassword()
        );
        try {
            authManager.authenticate(authInputToken);
        } catch (AuthenticationException e) {
            return Map.of("message", "Incorrect credentials");
        }
        String token = jwtUtil.generateToken(authenticationDto.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDto personDto,
                                      BindingResult bindingResult) {
        System.out.println("there");

        Person person = mapper.map(personDto, Person.class);

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message", "error");
        }
        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }
}
