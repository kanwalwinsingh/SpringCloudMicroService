package com.JoshLong.SpringCloud.reservationservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import sun.text.resources.el.CollationData_el;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
public class ReservationServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServicesApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ReservationRepository reservationRepository){

		return strings -> {
			Stream.of("Josh","pieter", "tasha", "Eric", "Sussie", "Max")
					.forEach(n->reservationRepository.save(new Reservation(n)));
		};
	}

}

@Entity
class Reservation{
	public Reservation() {
	}

	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}
}


@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>
{
	@RestResource(path ="by-name")
	Collection<Reservation> findByReservationName(@Param("rn") String rn);
}

@RestController
class MesssageRestController{

    @Value("$")
}




