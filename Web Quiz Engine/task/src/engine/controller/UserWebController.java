package engine.controller;

import engine.entity.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/api/register")
@RestController
public class UserWebController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    private User registerUser(@RequestBody User user){

        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        return userRepository.save(user);
    }

}
