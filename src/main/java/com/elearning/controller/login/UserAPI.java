package com.elearning.controller.login;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.AuthResponseDTO;
import com.elearning.dto.login.LoginDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.email.EmailSender;
import com.elearning.email.token.ConfirmationToken;
import com.elearning.email.token.ConfirmationTokenService;
import com.elearning.entity.login.UserEntity;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.jwt.JwtGenerator;
import com.elearning.repository.security.UserRepository;
import com.elearning.service.security.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserAPI {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/register/student")
    public Result createStudentAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createStudentAccount(dto);
        return new Result(true, StatusCode.SUCCESS,
                "Create teacher success, please change password and check email to enable account!", savedDTO);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return accountService.confirmToken(token);
    }

    @PostMapping("/register/teacher")
    public Result createTeacherAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createTeacherAccount(dto);
        return new Result(true, StatusCode.SUCCESS,
                "Create teacher success, please change password and check email to enable account!", savedDTO);
    }

    @PutMapping("/user/{id}")
    public Result changPassword(@PathVariable("id") Long id, @RequestBody @Valid ChangePasswordDTO dto) {
        accountService.changePassword(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Change password success!");
    }

    @GetMapping("/user/{id}")
    public Result findOneUser(@PathVariable("id") Long id) {
        UserDTO dto = accountService.findOneUser(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success!", dto);
    }

    @GetMapping("/user")
    public Result findAllUser() {
        List<UserDTO> listDTO = accountService.findAllUser();
        return new Result(true, StatusCode.SUCCESS, "Find all success!", listDTO);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new Result(true, StatusCode.SUCCESS, "Login success", new AuthResponseDTO(token).toString());
    }

    @PostMapping("/verify")
    public Result verify(@RequestBody LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.getUsername());
        if (userEntity.isEnabled()) {
            return new Result(true, StatusCode.SUCCESS, "Account had verified");
        } else {
            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    userEntity
            );

            confirmationTokenService.saveConfirmationToken(
                    confirmationToken);
            String link = "http://localhost:9090/confirm?token=" + token;
            emailSender.send(userEntity.getEmail(),
                    accountService.buildEmail(userEntity.getUsername(), link, "PROTECTED"));
        }
        return new Result(true, StatusCode.SUCCESS, "Please check your email and click the link to verify your account!");
    }
}
