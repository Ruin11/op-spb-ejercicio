package com.example.opspbejercicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.opspbejercicio.entities.Laptop;
import com.example.opspbejercicio.repository.LaptopRepository;

@SpringBootApplication
public class OpSpbEjercicioApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OpSpbEjercicioApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop laptopOne = new Laptop(null, "Apple", "BA", 20.99);
		Laptop laptopOneBlack = new Laptop(null, "Lenovo", "RC", 30.99);

		repository.save(laptopOne);
		repository.save(laptopOneBlack);

		System.out.println("Numero de laptos en deposito: "+repository.findAll().size());
	

	}

}
