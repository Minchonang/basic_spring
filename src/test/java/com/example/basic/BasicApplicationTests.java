package com.example.basic;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.basic.model.Owner;
import com.example.basic.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.model.Dept;
import com.example.basic.model.Emp;
import com.example.basic.model.Product;
import com.example.basic.repository.DeptRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.ProductRepository;

@SpringBootTest
class BasicApplicationTests {
	@Autowired
	DeptRepository deptRepository;

	@Autowired
	EmpRepository empRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Test
	@Transactional
	void deptTable() {
		List<Dept> deptList = deptRepository.findAll();
		System.out.println(deptList);

	}

	@Test
	void empTable() {
		List<Emp> empList = empRepository.findAll();
		System.out.println(empList);

	}

	@Test
	void empTableJob() {
		List<Emp> empJobList = empRepository.findByJob("MANAGER");
		System.out.println(empJobList);

	}

	@Test
	void empTablesSal() {
		List<Emp> empSalList = empRepository.findBySalGreaterThanEqual(2000);
		System.out.println(empSalList);

	}

	@Test
	void product() {
		Optional<Product> opt = productRepository.findById(1);
		Product product = opt.get();
		System.out.println(product);
	}

	@Test @Transactional
	void owner() {
		Optional<Owner> opt = ownerRepository.findById(1);
		Owner owner = opt.get();
		System.out.println(owner);
	}

}
