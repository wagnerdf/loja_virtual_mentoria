package jdev.mentoria.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/* Criar a autenticação e retornar também a aautenticação */
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/* Token de validade de 10 dias */
	private static final long EXPIRATION_TIME = 864000000;
	
	/* Chave de senha para juntar com o JWT */
	private static final String SECRET = "hduuet455854khjhdtts-51455985482wercfyeXPFU";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/* Gera o token e da a resposta para o cliente o com JWT */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		/* Montagem do Token */
		
		String JWT = Jwts.builder(). /* Chama o gerador de token */
				setSubject(username) /* Adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /* Tempo de expiração */
		
		/* Exemplo: Bearer *-/a*asd465asda46s5d4a6s5da6sd4a65sda6s5d4a6s4da6s54d6as4d6as4d6a5s4da65s4d* */
		String token = TOKEN_PREFIX + " " + JWT;
		
		/* Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javaScript, outra chamada java */
		response.addHeader(HEADER_STRING, token);
		
		/* Usado para ver no Postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

}
