package com.chs.service;




import com.chs.entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User createUser(User user);
//    List<User> saveUsersFromUserModule(List<User> userList);
}
