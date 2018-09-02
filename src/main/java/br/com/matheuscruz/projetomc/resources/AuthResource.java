package br.com.matheuscruz.projetomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.security.UserSS;
import br.com.matheuscruz.projetomc.security.utils.JWTUtils;
import br.com.matheuscruz.projetomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	JWTUtils jwtUtils;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtils.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
