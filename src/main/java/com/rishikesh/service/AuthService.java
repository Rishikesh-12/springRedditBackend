package com.rishikesh.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rishikesh.dto.RegisterRequest;
import com.rishikesh.exception.SpringRedditException;
import com.rishikesh.model.NotificationEmail;
import com.rishikesh.model.User;
import com.rishikesh.model.VerificationToken;
import com.rishikesh.repository.UserRepository;
import com.rishikesh.repository.VerificationTokenRepository;
import com.sun.istack.NotNull;

import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEnabled(false);
		user.setCreated(Instant.now());
		userRepository.save(user);
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account", user.getEmail(),
				"Thank you for signing up, please click on the below url to activate your account:\n http://localhost:8080/api/auth/accountverification/"
						+ token));
	}

	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new SpringRedditException("Token is invalid"));
		fetchUserandEnable(verificationToken.get());
	}

	@Transactional
	private void fetchUserandEnable(VerificationToken verificationToken) {
		@NotBlank(message="Username is required") String userName = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new SpringRedditException("User not found"));
		user.setEnabled(true);
		Log.info("User Enabled");
		userRepository.save(user);
	}
}
