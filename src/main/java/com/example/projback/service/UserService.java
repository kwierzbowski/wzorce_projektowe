package com.example.projback.service;

import com.example.projback.entity.Role;
import com.example.projback.entity.User;
import com.example.projback.wzorce.L1.builder.UserBuilder;
import com.example.projback.repository.UserRepository;
import com.example.projback.wzorce.L10.UserFilter;
import com.example.projback.wzorce.L12.UserReportGenerator;
import com.example.projback.wzorce.L12.UserReportTemplate;
import com.example.projback.wzorce.L2.Bridge.AbstractClasses.UserServiceBridge;
import com.example.projback.wzorce.L2.Bridge.UserValidator;
import com.example.projback.wzorce.L5.Command.Command;
import com.example.projback.wzorce.L5.Command.User_Create_Command;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//###   start L2 Bridge -> UserService -> Usage
@Service
public class UserService extends UserServiceBridge {
    //###   end L2 Bridge -> UserService -> Usage

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    private final UserValidator validator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, @Qualifier("advancedValidator_User") UserValidator validator) {
        super(userRepository, passwordEncoder, validator);
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.validator = validator;

    }

    public void registerUser(User user) {
        //###   start L2 Bridge -> User -> Part 4
        if (!validator.validate(user)) {
            throw new IllegalArgumentException("Użytkownik niepoprawny");
        }
        //###   end L2 Bridge -> User -> Part 4

        //###   start L1 Factory -> Part 4 - usage
//        User newUser = UserFactory.createUser(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getRole());
//        userRepository.save(newUser);
        //###   end L1 Factory -> Part 4 - usage

        //###   start L1 Builder -> Part 4 - usage
        User newUser = new UserBuilder()
                .setUsername(user.getUsername())
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRole(user.getRole())
                .build();
//        userRepository.save(newUser);

        //###   start L5 Command -> User - usage
        Command createUserCommand = new User_Create_Command(newUser, userRepository);
        createUserCommand.execute();
        //###   end L5 Command -> User - usage

        //###   end L1 Builder -> Part 4 - usage
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username); // Return Optional<User> from the repository
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //###   start L10, UserFilter (part 2)
    public List<User> getUsersByRole(Role role) {
        UserFilter roleFilter = user -> user.getRole() == role;

        return userRepository.findAll()
                .stream()
                .filter(roleFilter::test)
                .collect(Collectors.toList());
    }
    //###   end L10, UserFilter (part 2)

    //###   start L12, 2.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

        public ResponseEntity<byte[]> generateUserReportResponse(String format) {
            List<User> users = getAllUsers();
            UserReportTemplate strategy = UserReportGenerator.getStrategy(format);
            byte[] report = strategy.generateReport(users);

            String contentType = format.equalsIgnoreCase("xlsx")
                    ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                    : "text/csv";

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=users." + format)
                    .header("Content-Type", contentType)
                    .body(report);
        }
    }

    //### end L12, 2.
