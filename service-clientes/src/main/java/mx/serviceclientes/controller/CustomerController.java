package mx.serviceclientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ms.serviceclientes.models.Customer;
import mx.serviceclientes.bussineslogic.CustomerBussinesLogic;

@RestController
@RequestMapping("customer/")
public class CustomerController {
	
	
	@Autowired
	CustomerBussinesLogic logic;

	@GetMapping
	public ResponseEntity<List<Customer>> verTodos() {
		List<Customer> customer = logic.display();
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Customer> guardar(@RequestBody Customer request) {
		Customer customer = logic.add(request);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Customer> editar(@RequestBody Customer request) {
		Customer customer = logic.update(request);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("find/{id}")
	public ResponseEntity<Customer> buscar(@PathVariable int id) {
		Customer customer = logic.find(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> eliminar(@PathVariable int id) {
		String mensaje = logic.delete(id);
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	}
	

}
