package com.poke.service;

import java.util.List;

import com.poke.persistence.domain.User;

public interface UserService {

	List<User> getUsers();

	User getUser(Long id);

	User addUser(User user);

}
