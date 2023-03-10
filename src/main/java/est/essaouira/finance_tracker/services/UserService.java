package est.essaouira.finance_tracker.services;


import est.essaouira.finance_tracker.dtos.UserDto;
import est.essaouira.finance_tracker.models.User;
import est.essaouira.finance_tracker.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserDto createUser(UserDto user) {
        User newUser = mapper.map(user, User.class);
        return mapper.map(userRepository.save(newUser), UserDto.class);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}