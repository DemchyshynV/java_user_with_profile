package com.example.avatars.services;

import com.example.avatars.dao.UserRepository;
import com.example.avatars.models.Profile;
import com.example.avatars.models.User;
import com.example.avatars.services.interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUser {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User updateById(int id, String email, String password) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            User user = u.get();
            user.setEmail(email);
            user.setPassword(password);
            return user;
        }

        return null;
    }

    @Override
    public User savePhoto(int id, MultipartFile img) throws IOException {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            User user = u.get();
            if (user.getProfile() == null) {
                user.setProfile(new Profile());
            }
            File path = new File(
                    System.getProperty("user.home")
                            + File.separator
                            + "avatars"
                            + File.separator
                            + user.getProfile().getSurname()
            );
            if (!path.exists()) {
                path.mkdirs();
            }
            String filePath = path.getAbsolutePath() + File.separator + img.getOriginalFilename();
            img.transferTo(new File(filePath));

            user.getProfile().setAvatar(img.getOriginalFilename());
            return userRepository.save(user);

        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
