package com.elearning.service.security;

import com.elearning.config.PasswordGenerator;
import com.elearning.converter.UserConverter;
import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.RoleDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.email.EmailSender;
import com.elearning.email.token.ConfirmationToken;
import com.elearning.email.token.ConfirmationTokenService;
import com.elearning.entity.login.RoleEntity;
import com.elearning.entity.login.UserEntity;
import com.elearning.exception.Exception400;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.security.RoleRepository;
import com.elearning.repository.security.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    @Override
    public UserDTO createStudentAccount(UserDTO dto) {
        if (userRepository.findOneByUsername(dto.getUsername()) != null) {
            throw new Exception409("This username already exists!");
        }
        if (studentRepository.findOneByCode(dto.getStudent().getCode()) != null) {
            throw new Exception409("This student code already exists!");
        }
        UserEntity entity = userConverter.toEntity(dto);
        String newPassword = PasswordGenerator.generateRandomPassword(10);
        entity.setPassword(passwordEncoder.encode(newPassword));
        entity.setRoles(roleRepository.findOneByName("STUDENT"));
        studentRepository.save(entity.getStudent());
        userRepository.save(entity);
        UserDTO returnDTO = userConverter.toDTO(entity);
        returnDTO.setPassword(newPassword);
        generateEmail(entity, newPassword);
        return returnDTO;
    }

    @Override
    public UserDTO createTeacherAccount(UserDTO dto) {
        if (userRepository.findOneByUsername(dto.getUsername()) != null) {
            throw new Exception409("This username already exists!");
        }
        UserEntity entity = userConverter.toEntity(dto);
        String newPassword = PasswordGenerator.generateRandomPassword(10);
        entity.setPassword(passwordEncoder.encode(newPassword));
        entity.setRoles(roleRepository.findOneByName("TEACHER"));
        teacherRepository.save(entity.getTeacher());
        userRepository.save(entity);
        UserDTO returnDTO = userConverter.toDTO(entity);
        returnDTO.setPassword(newPassword);
        generateEmail(entity, newPassword);
        return returnDTO;
    }

    @Override
    public void changePassword(Long id, ChangePasswordDTO dto) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) {
            throw new EntityNotFoundException("This user is not found!");
        }
        if (!userConverter.checkPassword(entity, passwordEncoder.encode(dto.getCurrentPassword()))) {
            throw new Exception400("Wrong current password!");
        }
        entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        entity.setChangedPassword(true);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findOneById(id));
    }

    @Override
    public RoleDTO addNewRole(String role) {
        if (roleRepository.findByName(role) != null) {
            throw new Exception409("This role already exist");
        }
        RoleEntity savedEntity = roleRepository.save(new RoleEntity(role));
        return new RoleDTO(savedEntity.getId(), role);
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public UserDTO findOneUser(Long id) {
        if (userRepository.findOneById(id) == null) {
            throw new Exception404("User not found with this id");
        }
        return userConverter.toDTO(userRepository.findOneById(id));
    }

    @Override
    public List<UserDTO> findAllUser() {
        List<UserEntity> listEntity = userRepository.findAll();
        return listEntity.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }
    public void generateEmail(UserEntity userEntity, String password){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userEntity
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String link = "http://localhost:9090/confirm?token=" + token;
        emailSender.send(
                userEntity.getEmail(),
                buildEmail(userEntity.getUsername(), link, password));

    }
    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableUser(confirmationToken.getUser().getUsername());
        return "confirmed";
    }
    public String buildEmail(String name, String link, String password) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering, your password is <strong>"+password+"</strong>. \nPlease click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
