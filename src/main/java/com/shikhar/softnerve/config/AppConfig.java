package com.shikhar.softnerve.config;

import com.shikhar.softnerve.entity.Patient;
import com.shikhar.softnerve.exceptions.ResourceNotFoundException;
import com.shikhar.softnerve.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class AppConfig implements UserDetailsService {

    @Autowired
    private PatientRepo patientRepo;

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder().username("shikhar").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
        UserDetails user1 = User.builder().username("softnerve").password(passwordEncoder().encode("1234")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(admin, user1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Patient patient = this.patientRepo.findByName(username).orElseThrow(() -> new ResourceNotFoundException("Patient", "patient_email" + username, 0));

        return (UserDetails) patient;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
