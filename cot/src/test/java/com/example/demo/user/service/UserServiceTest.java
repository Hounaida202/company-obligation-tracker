package com.example.demo.user.service;

import com.example.demo.user.dto.*;
import com.example.demo.user.entity.User;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.common.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        UserDto dto1 = new UserDto();
        UserDto dto2 = new UserDto();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.toDto(user1)).thenReturn(dto1);
        when(userMapper.toDto(user2)).thenReturn(dto2);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toDto(user1);
        verify(userMapper, times(1)).toDto(user2);
    }

    @Test
    void testLogin() {
        LoginRequest request = new LoginRequest();
        request.setUsername("hounaida:)");
        request.setPassword("password");

        User user = new User();
        user.setUsername("hounaida:)");
        UserDto dto = new UserDto();
        dto.setName("hounaida:)");

        UserDetails userDetails = mock(UserDetails.class);

        when(userRepository.findByUsername("hounaida:)")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(dto);
        when(userDetailsService.loadUserByUsername("hounaida:)")).thenReturn(userDetails);
        when(jwtUtils.generateToken(userDetails)).thenReturn("token123");

        AuthResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("token123", response.getToken());
        assertEquals("hounaida:)", response.getUser().getName());
        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testChangePassword_Success() {
        User user = new User();
        user.setPassword("encodedOld");

        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setCurrentPassword("old");
        request.setNewPassword("new");

        when(userRepository.findByUsername("hounaida:)")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("old", "encodedOld")).thenReturn(true);
        when(passwordEncoder.encode("new")).thenReturn("encodedNew");

        userService.changePassword("hounaida:)", request);

        assertEquals("encodedNew", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateProfile() {
        User user = new User();
        user.setName("Old Name");

        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setName("New Name");

        UserDto dto = new UserDto();
        dto.setName("New Name");

        when(userRepository.findByUsername("hounaida:)")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(dto);

        UserDto result = userService.updateProfile("hounaida:)", request);

        assertEquals("New Name", result.getName());
        verify(userRepository, times(1)).save(user);
    }
}