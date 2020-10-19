package engine.controller;

import engine.entity.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("/api/register")
@RestController
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    private User registerUser(@Valid @RequestBody User user){

        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSolvedQuizzes(new ArrayList<>());

        return userRepository.save(user);
    }

}
