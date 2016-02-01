package com.example;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class HajibootJpaApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... strings) throws Exception {
		// 데이터 추가
		Customer created = customerRepository.save(new Customer(null, "byungeun", "woo"));
		System.err.println(created + "is created!!");

		// 페이징 처리
		Pageable pageable = new PageRequest(0, 3);
		Page<Customer> page = customerRepository.findAllOrderByName(pageable);
//		Page<Customer> page = customerRepository.findAll(pageable);
		System.err.println("한 페이지당 데이터 수=" + page.getSize());
		System.err.println("현재 페이지=" + page.getNumber());
		System.err.println("전체 페이지 수=" + page.getTotalPages());
		System.err.println("전체 데이터 수=" + page.getTotalElements());
		page.getContent().forEach(System.err::println);

		// 데이터 표시
		//customerRepository.findAll().forEach(System.err::println);
		//customerRepository.findAllOrderByName().forEach(System.err::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(HajibootJpaApplication.class, args);
	}
}
