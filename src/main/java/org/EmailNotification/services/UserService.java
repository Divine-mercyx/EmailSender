package org.EmailNotification.services;

import org.EmailNotification.data.models.EmailDetail;
import org.EmailNotification.data.models.User;
import org.EmailNotification.data.repositories.EmailDetailRepository;
import org.EmailNotification.data.repositories.UserRepository;
import org.EmailNotification.dto.UserLoginRequest;
import org.EmailNotification.exceptions.InvalidPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailDetailRepository emailDetailRepository;

    public User registerUser(User user) {
        hashPassword(user);
        return userRepository.save(user);
    }

    private static void hashPassword(User user) {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
    }

    public User login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        validatePassword(request, user);
        return user;
    }

    public EmailDetail sendMail(EmailDetail emailDetail) {
        return emailDetailRepository.save(emailDetail);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public Long countAllUsers() {
        return userRepository.count();
    }

    private static void validatePassword(UserLoginRequest request, User user) {
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) throw new InvalidPasswordException("invalid password");
    }
}
