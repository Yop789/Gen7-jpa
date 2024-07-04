package com.lopez.app.jpa.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopez.app.jpa.models.Usuario;
import com.lopez.app.jpa.services.UsuariosService;
import com.lopez.app.jpa.utils.JwtUtil;


@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
   @Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuariosService usuarioService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("login")
	public Map<String, Object> login( @RequestBody Usuario c) throws Exception {
		Map<String, Object> mapa = new HashMap<>();
		//mapa.put("hola", "hola");
		//mapa.put("token", jwtUtil.generarToken(c.getEmail(), c.getRole()));
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(c.getUsername(), c.getPassword())
			);
			
			if(auth.isAuthenticated()) {
				final UserDetails userDetails = usuarioService.loadUserByUsername(c.getUsername());
				mapa = new HashMap<>();
				Map<String, Object> claims = new HashMap<>();
				claims.put("edad", 12);
				claims.put("nombre", c.getNombre());
				claims.put("role", c.getRole());
				mapa.put("token", jwtUtil.generateToken(c.getUsername(), claims));
				return mapa;
			}
			/*else {
				mapa = new HashMap<>();
				mapa.put("token", jwtUtil.generarToken(customerDetailsService.getUsuarioDetail().getEmail(), customerDetailsService.getUsuarioDetail().getRole()));
				return mapa;
			}*/
		}
		
		
		catch (UsernameNotFoundException e) {
           throw new Exception("Incorrect username or password", e);
       }
		return mapa;
	}
	
	@PostMapping("/registry")
	public Map<String, String> guardar( @RequestBody Usuario c) {
		usuarioService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Usuario guardado correctamente");
		return respuesta;
	}


    

}
