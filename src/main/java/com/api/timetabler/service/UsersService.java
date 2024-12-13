package com.api.timetabler.service;

import com.api.timetabler.models.Users;
import com.api.timetabler.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    //JWT
    @Autowired
    private JwtService jwtService;

    //BCRYPT
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); //strength

    public String signup (Users users) {
        if (usersRepository.findByEmail(users.getEmail()) != null) {
            return "User already exists!! Please Login";
        }

        users.setPassword(encoder.encode(users.getPassword()));
        usersRepository.save(users);
        return "User saved successfully!!";

    }

    public String login (String identifier, String password) {


//     if (players != null && players.getPassword().equals(password)){
//         return "Here we gooo!!"; //JWT
//      }

        if (identifier == null || password == null){
            return "Identifier and password should not be null!!";
        }

        try {
            Users users = usersRepository.findByEmail(identifier);

            if (users == null){
                users = usersRepository.findByPhoneNumber(identifier);
            }

            if (users != null && encoder.matches(password, users.getPassword())){
                return jwtService.generateToken(users.getName()); //JWT Token being returned.
            }
        }

        catch (Exception e) {
            System.err.println("An error occurred during login: " + e.getMessage());
            return "An error occurred. Please try again later.";
        }

        return "Invalid Credentials or Try to signup first!!";
    }
}
