package com.poke.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.poke.persistence.domain.SentUser;
import com.poke.persistence.domain.User;
import com.poke.persistence.repository.UserRepository;
import com.poke.util.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repo;
	private JmsTemplate jmsTemplate;

	@Autowired
	public UserServiceImpl(UserRepository repo, JmsTemplate jmsTemplate) {
		this.repo = repo;
		this.jmsTemplate = jmsTemplate;
	}

	// READ
	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	// READ
	@Override
	public User getUser(Long id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new UserNotFoundException(id.toString()));
	}

	// CREATE
	@Override
	public User addUser(User user) {
		sendToQueue(user);
		return repo.save(user);
	}

//	 CHECK IF ACCOUNT EXISTS
	public Boolean userExists(Long id) {
		Optional<User> userOptional = repo.findById(id);
		return userOptional.isPresent();
	}

	// RECORD SEARCH - SEND TO QUEUE
	private void sendToQueue(User user) {
		SentUser userToStore = new SentUser(user);
		jmsTemplate.convertAndSend("UserQueue", userToStore);
	}

}
