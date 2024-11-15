package net.mohamed.demospringang;
import net.mohamed.demospringang.entities.Payment;
import net.mohamed.demospringang.entities.PaymentStatus;
import net.mohamed.demospringang.entities.PaymentType;
import net.mohamed.demospringang.entities.Student;
import net.mohamed.demospringang.repository.PaymentRepository;
import net.mohamed.demospringang.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class DemoSpringAngApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAngApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner( StudentRepository studentRepository,
										 PaymentRepository paymentRepository){
		return args -> {


			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
							.firstName("Mohamed").code("112233").programId("SDIA")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Imen").code("112244").programId("SDIA")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Yasimne").code("112255").programId("GLSID")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Najat").code("112266").programId("BDCC")
					.build());
			PaymentType[] paymentTypes = PaymentType.values();
			studentRepository.findAll().forEach(st->{
				for (int i=0;i<10;i++	){
					int index = new Random().nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});

			};

		}
	}
