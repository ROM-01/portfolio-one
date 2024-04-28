package com.rom1.portfolio.portfolioone.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rom1.portfolio.portfolioone.data.CustomUserDetails;
import com.rom1.portfolio.portfolioone.data.user.UserProfileRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var profile = userProfileRepository.findByUsernameIgnoreCase(username);
        if (profile.isPresent())
            return new CustomUserDetails(profile.get());
        throw new UsernameNotFoundException("User not found.");
    }
}
