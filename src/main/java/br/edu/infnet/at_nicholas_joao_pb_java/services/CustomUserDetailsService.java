/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.at_nicholas_joao_pb_java.services;

import br.edu.infnet.at_nicholas_joao_pb_java.Entities.User;
import br.edu.infnet.at_nicholas_joao_pb_java.details.CustomUserDetails;
import br.edu.infnet.at_nicholas_joao_pb_java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Nicholas
 */
public class CustomUserDetailsService implements UserDetailsService{
     @Autowired
    private UserRepository userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
