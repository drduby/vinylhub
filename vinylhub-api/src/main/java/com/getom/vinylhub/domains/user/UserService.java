package com.getom.vinylhub.domains.user;

import com.getom.vinylhub.exception.ValidationException;
import com.getom.vinylhub.shared.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResource> getAllUsers() {
        return userMapper.toResourceList(userRepository.findAll());
    }

    public UserResource getUserById(Long id) {
        return userMapper.toResource(
                userRepository.findById(id).orElseThrow()
        );
    }

    public UserResource createUser(CreateUserRequest createUserRequest) {
        this.validate(createUserRequest);

        User existing = userRepository.findByEmail(createUserRequest.email());
        if (existing != null) {
            throw new ValidationException("email", "Email already exists");
        }

        User user = userMapper.createEntityFromDto(createUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(createUserRequest.email().trim().toLowerCase());

        return userMapper.toResource(userRepository.save(user));
    }

    public UserResource updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = this.findUserById(id);

        this.validate(updateUserRequest);

        User byEmail = userRepository.findByEmail(updateUserRequest.email());
        if (byEmail != null && !byEmail.getId().equals(user.getId())) {
            throw new ValidationException("email", "Email already exists");
        }

        userMapper.updateEntityFromDto(user, updateUserRequest);
        return userMapper.toResource(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        User user = this.findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist"));
    }

}
