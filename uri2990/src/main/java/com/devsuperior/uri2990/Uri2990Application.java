package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> list = empregadoRepository.search1();
		List<EmpregadoDeptDTO> result1 = list.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());
		
		System.out.println("\n*** SQL Result");
		result1.forEach(x -> System.out.println(x));

		List<EmpregadoDeptDTO> result2 = empregadoRepository.search2();
		
		System.out.println("\n*** JPQL Result");
		result2.forEach(x -> System.out.println(x));
	}
}
