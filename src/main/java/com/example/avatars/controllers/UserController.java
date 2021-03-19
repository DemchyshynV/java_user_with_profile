package com.example.avatars.controllers;

import com.example.avatars.models.User;
import com.example.avatars.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(user);
        return userService.save(user);
    }

    @PostMapping("/{id}/avatar")
    public User savePhoto(@PathVariable int id, @RequestParam MultipartFile avatar) throws IOException {
        return userService.savePhoto(id, avatar);
    }
}
