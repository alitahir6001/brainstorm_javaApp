package com.pakfro.beltExam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.pakfro.beltExam.models.Idea;
import com.pakfro.beltExam.models.User;
import com.pakfro.beltExam.repositories.IdeaRepository;
import com.pakfro.beltExam.repositories.UserRepository;

@Service

public class UserService {
    private final UserRepository userRepo;
    private final IdeaRepository ideaRepo;

    public UserService (UserRepository userRepo, IdeaRepository ideaRepo) {
        this.userRepo = userRepo;
        this.ideaRepo = ideaRepo;
    }
    
    
 // register user and hash their password
    public User registerUser(User user) {
       String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
       user.setPassword(hashed);
       user.setEmail(user.getEmail().toLowerCase());
       return userRepo.save(user);

    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	return userRepo.findById(id).orElse(null);
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // -----------END OF LOGIN/REG SERVICES ------------ //
    
    
    
 // Find All the Creators/Users
    public List<User> findAllUsers(){
    		return (List<User>)this.userRepo.findAll();	
    }
    
 
 // Create a new idea
    public Idea createIdea(Idea idea) {
    		return this.ideaRepo.save(idea);
    }
//    
//    
//    // Find All ideas
    public List<Idea> findAllIdeas(){
    		return (List<Idea>)this.ideaRepo.findAll();
    }
//    
//    
//    
//    // Find one idea
    public Idea findAnIdea(Long id) {
    		return this.ideaRepo.findById(id).orElse(null);
    }
//    
//    
//    
//    // Update an idea
    public Idea updateAnIdea(Idea idea) {
    		return this.ideaRepo.save(idea);
    }
//    
//    
//    
//    // delete an idea
    public void deleteIdea(Long id) {
    		this.ideaRepo.deleteById(id);
    }
}
