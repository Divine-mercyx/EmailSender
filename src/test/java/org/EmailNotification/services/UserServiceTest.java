package org.EmailNotification.services;

import org.EmailNotification.data.models.EmailDetail;
import org.EmailNotification.data.models.User;
import org.EmailNotification.dto.UserLoginRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        userService.deleteAllUsers();
        user = new User();
    }

    @AfterEach
    void tearDown() {
        userService.deleteAllUsers();
    }

    @Test
    void registerUser() {
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("test");
        User savedUser = userService.registerUser(user);
        assertNotNull(savedUser);
        assertEquals(savedUser.getId(), user.getId());
    }

    @Test
    void login() {
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("test");
        User savedUser = userService.registerUser(user);
        assertNotNull(savedUser);
        UserLoginRequest request = new UserLoginRequest();
        request.setEmail("test@test.com");
        request.setPassword("password");
        User loggedInUser = userService.login(request);
        assertNotNull(loggedInUser);
        assertEquals(loggedInUser.getId(), savedUser.getId());
    }

    @Test
    void sendEmail_countEmailsTest() {
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setUsername("test");
        User savedUser = userService.registerUser(user);
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setSubject("Test Subject");
        emailDetail.setBody("Test Body");
        emailDetail.setUser(savedUser);
        emailDetail.setRecipient("divineObinali9@gmail.com");
        EmailDetail savedEmailDetail = userService.sendMail(emailDetail);
        assertNotNull(savedEmailDetail);
        assertEquals(savedEmailDetail.getSubject(), emailDetail.getSubject());
    }
}