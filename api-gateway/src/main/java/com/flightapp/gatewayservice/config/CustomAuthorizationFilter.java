//package com.flightapp.gatewayservice.config;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Stream;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.client.HttpClientErrorException.Forbidden;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class CustomAuthorizationFilter extends OncePerRequestFilter{
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		if(request.getServletPath().equals("login path that does not require authprization")) {
//			filterChain.doFilter(request, response);
//		}else {
//			String authorizationHeader = request.getHeader("Authorization");
//			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//				try {
//					String token = authorizationHeader.substring("Bearer ".length());
//					//save the secret somewer else
//					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//					JWTVerifier verifier = JWT.require(algorithm).build();
//					DecodedJWT decodedJWT = verifier.verify(token);
//					String username= decodedJWT.getSubject();
//					//verofy role keyword
//					String[] roles = decodedJWT.getClaim("role").asArray(String.class);
//					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//					Arrays.stream(roles).forEach(role->{
//						authorities.add(new SimpleGrantedAuthority(role));
//					});
//					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null,authorities);
//					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//					filterChain.doFilter(request, response);
//				}catch(Exception E) {
//					//logggg
//				//	log.error("Error logging in" , E.getMessage());
//					response.setHeader("error",E.getMessage());
//					response.sendError(HttpStatus.FORBIDDEN.value());
//					
////					Map<String,String> error = new HashMap<>();
////					error.put("error_message", E.getMessage());
////					response.setContentType("application/json");
////					new ObjectMapper().writeValue(response.getOutputStream(), error);
//				}
//				
//			}else {
//				filterChain.doFilter(request, response);
//			}
//		}
//		
//	}
//
//}
