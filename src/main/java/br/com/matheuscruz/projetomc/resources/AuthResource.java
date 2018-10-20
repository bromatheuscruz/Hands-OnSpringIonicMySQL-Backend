package br.com.matheuscruz.projetomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.dto.EmailDTO;
import br.com.matheuscruz.projetomc.security.UserSS;
import br.com.matheuscruz.projetomc.security.utils.JWTUtils;
import br.com.matheuscruz.projetomc.services.AuthService;
import br.com.matheuscruz.projetomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	JWTUtils jwtUtils;

	@Autowired
	AuthService authService;

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtils.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@RequestBody @Valid EmailDTO emailDTO) {
		authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
