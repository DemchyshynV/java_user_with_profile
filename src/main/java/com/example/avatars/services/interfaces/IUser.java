package com.example.avatars.services.interfaces;

import com.example.avatars.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUser {
    User save(User user);

    List<User> getAll();

    User findById(int id);

    User updateById(int id, String email, String password);

    User savePhoto(int id, MultipartFile img) throws IOException;

    void deleteById(int id);
}
