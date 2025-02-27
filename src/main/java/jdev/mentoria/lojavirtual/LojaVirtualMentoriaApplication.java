package jdev.mentoria.lojavirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**import org.springframework.boot.autoconfigure.domain.EntityScan;**/

@SpringBootApplication
/**Anotação abaixo ativar somente se estiver com problemas de não criar as tabelas**/
/**@EntityScan(basePackages = "jdev.mentoria.lojavirtual.model")**/
public class LojaVirtualMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualMentoriaApplication.class, args);
	}

}
