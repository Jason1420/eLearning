package com.elearning.controller.login;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.AuthResponseDTO;
import com.elearning.dto.login.LoginDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.jwt.JwtGenerator;
import com.elearning.repository.security.UserRepository;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPI {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserRepository userRepository;

    @PostMapping("/register/student")
    public Result createStudentAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createStudentAccount(dto);
        return new Result(true, StatusCode.SUCCESS, "Create student success!", savedDTO);
    }

    @PostMapping("/register/teacher")
    public Result createTeacherAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createTeacherAccount(dto);
        return new Result(true, StatusCode.SUCCESS, "Create teacher success!", savedDTO);
    }

    @PutMapping("/user/{id}")
    public Result changPassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO dto) {
//        if(u.getId() == id || u.getRoles().contains("ADMIN")) {
//            //accept uer
//        }
        accountService.changePassword(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Change password success!");
    }

    @GetMapping("/user/{id}")
    public Result findOneUser(@PathVariable("id") Long id) {
        UserDTO dto = accountService.findOneUser(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success!", dto);
    }
//    @GetMapping("/user/{id}")
//    public Long findOneUser(@PathVariable("id") Long id) {
//       Long idsa = accountService.findOneUser(id);
//        return idsa;
//    }

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
}
