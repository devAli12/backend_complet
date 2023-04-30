package com.form.fiche.services;

/*import com.commercial.commercial.email.EmailService;
import com.commercial.commercial.model.ResetPasswordToken;
import com.commercial.commercial.model.User;
import com.commercial.commercial.repository.UserRepository;
import com.commercial.commercial.utils.BuildEmail;
import jakarta.mail.MessagingException;*/
import com.form.fiche.email.EmailService;
import com.form.fiche.entities.ResetPasswordToken;
import com.form.fiche.entities.User;
import com.form.fiche.repositories.UserRepository;
import com.form.fiche.utils.BuildEmail;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ResetPasswordTokenService resetPasswordTokenService;
    EmailService emailService;


    @Override
    public User findByEmail(String email) {
        Optional<User>  userFound= userRepository.findByEmail(email);
        if(userFound.isEmpty()){
            throw new RuntimeException("User not Found ");
        }
        return userFound.get();
    }

    @Override
    public User save(User user) {
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());
        if(userFound.isPresent()){
            throw new RuntimeException("user already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean resetPassword(String email) {
        boolean userFound = false;
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            return userFound;
        }
         String token = UUID.randomUUID().toString();
         ResetPasswordToken resetPasswordToken = new ResetPasswordToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user.get());
         resetPasswordTokenService.save(resetPasswordToken);
        //String link = "http://localhost:8080/api/resetPassword/confirmation?token=" + token;
           try{
               emailService.send(email, BuildEmail.buildEmail("ali",token));
           }catch (MessagingException excep){
               return false;
           }
        return true;
    }

    @Override
    public String confirmToken(String token) {
        ResetPasswordToken resetPasswordToken= resetPasswordTokenService.getResetPasswordTokenBy(token).orElseThrow(() ->
                new IllegalStateException("token not found"));
        if(resetPasswordToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expireAt=resetPasswordToken.getExpireAt();
        if(expireAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        resetPasswordTokenService.updateConfirmedAt(LocalDateTime.now(),token);
        return "confirmed";
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        Optional<User> userFound = userRepository.findByEmail(email);
        User newUser= userFound.get();
        newUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(newUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
