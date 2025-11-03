package com.getom.vinylhub.domains.user;

import com.getom.vinylhub.exception.ValidationException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllUsers_validData_success() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                "John",
                "Doe",
                "john@doe.com",
                "password123",
                UserRole.COLLECTOR
        );

        UserResource user = userService.createUser(createUserRequest);

        assertNotNull(userService.getAllUsers());
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void createCollectorUser_validData_success() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                "John",
                "Doe",
                "john@doe.com",
                "password123",
                UserRole.COLLECTOR
        );

        UserResource user = userService.createUser(createUserRequest);

        assertNotNull(user.id());
        assertEquals("John", user.firstName());
        assertEquals("Doe", user.lastName());
        assertEquals("john@doe.com", user.email());
        assertEquals(UserRole.COLLECTOR, user.role());
    }

    @Test
    void createAdminUser_validData_success() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                "Admin",
                "User",
                "admin@admin.com",
                "password123",
                UserRole.ADMIN
        );

        UserResource user = userService.createUser(createUserRequest);

        assertNotNull(user.id());
        assertEquals("Admin", user.firstName());
        assertEquals("User", user.lastName());
        assertEquals("admin@admin.com", user.email());
        assertEquals(UserRole.ADMIN, user.role());
    }

    @Test
    void updateUser_validData_success() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                "John",
                "Doe",
                "john@doe.com",
                "password123",
                UserRole.COLLECTOR
        );

        UserResource user = userService.createUser(createUserRequest);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "Johny",
                "Doe",
                "john@doe.com",
                "password123",
                UserRole.COLLECTOR
        );

        UserResource updated = userService.updateUser(user.id(), updateUserRequest);

        assertEquals(user.id(), updated.id());
        assertEquals("Johny", updated.firstName());
        assertEquals("Doe", updated.lastName());
        assertEquals("john@doe.com", updated.email());
    }

    @Test
    void deleteUser_success() {
        CreateUserRequest createDto = new CreateUserRequest(
                "Max",
                "Mustermann",
                "max@test.com",
                "password123",
                UserRole.COLLECTOR
        );
        UserResource user = userService.createUser(createDto);

        Long userId = user.id();

        userService.deleteUser(userId);

        assertFalse(userRepository.existsById(userId));
    }

    @Test
    void createUser_invalidDto_throwsValidationException() {
        CreateUserRequest dto = new CreateUserRequest("", "", "invalid-email", "123", UserRole.COLLECTOR);
        ValidationException ex = assertThrows(ValidationException.class, () -> userService.createUser(dto));
        System.out.println(ex.getErrors());
    }

    @Test
    void updateUser_invalidDto_throwsValidationException() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                "John",
                "Doe",
                "john@doe.com",
                "password123",
                UserRole.COLLECTOR
        );

        UserResource user = userService.createUser(createUserRequest);

        UpdateUserRequest dto = new UpdateUserRequest("", "", "invalid-email", "123", UserRole.COLLECTOR);

        ValidationException ex = assertThrows(ValidationException.class, () -> userService.updateUser(user.id(), dto));
        System.out.println(ex.getErrors());
    }
}
