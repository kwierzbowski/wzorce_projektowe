package com.example.projback.L13;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.projback.entity.User;
import com.example.projback.repository.UserRepository;
import com.example.projback.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
//###   start L13 testy jednostkowe, part 1
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        UserDetails userDetails = authService.loadUserByUsername("testUser");
        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("rawPassword");

        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> authService.registerUser(user));
    }

    @Test
    void testIsUsernameExist() {
        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));
        assertTrue(authService.isUsernameExist("existingUser"));
    }

    @Test
    void testLoadUserByUsernameThrowsException() {
        when(userRepository.findByUsername("unknownUser")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername("unknownUser"));
    }

    @Test
    void testRegisterExistingUserThrowsException() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(user));
        assertThrows(IllegalArgumentException.class, () -> authService.registerUser(user));
    }
}
//###   end L13 testy jednostkowe, part 1