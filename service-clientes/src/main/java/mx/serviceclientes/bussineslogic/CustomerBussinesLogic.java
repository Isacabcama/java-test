package mx.serviceclientes.bussineslogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ms.serviceclientes.models.Customer;

@Service
public class CustomerBussinesLogic {

	int autoId = 0;
	
	File file = new File(
			"C:\\Users\\Croni\\Documents\\workspace\\service-clientes\\src\\main\\resources\\customersdb.json");
	HashMap<Integer, Customer> Customers = new HashMap<Integer, Customer>();
	ObjectMapper mapper = new ObjectMapper();

	
	
	public List<Customer> display() {
		List<Customer> list = new ArrayList<Customer>();
		for (Integer key : this.read().keySet()) {
			list.add(this.read().get(key));
		}
		return list;
	}

	
	public Customer add(Customer customer) {
		autoId++;
		customer.setCustomerId(autoId);
		Customers.put(autoId, customer);
		this.persist(file, Customers);
		return new Customer(autoId, customer.getName(), customer.getEmail());
	}

	
	public Customer update(Customer customer) {
		Customers.put(customer.getCustomerId(), customer);
		this.persist(file, Customers);
		return new Customer(customer.getCustomerId(), customer.getName(), customer.getEmail());
	}

	
	public Customer find(int id) {
		//Recupero el elemento buscado del estado actual del json
		return read().get(id);
	}

	
	public String delete(int id) {
		HashMap<Integer, Customer> c = this.read();
		c.remove(id);
		this.persist(file, c);
		return "Eliminado";
	}

	public HashMap<Integer, Customer> read() {
		HashMap<Integer, Customer> CustomersExistentes = new HashMap<Integer, Customer>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inStr = new FileInputStream(file);
			TypeReference<HashMap<Integer, Customer>> typeReference = new TypeReference<HashMap<Integer, Customer>>() {
			};
			CustomersExistentes = mapper.readValue(inStr, typeReference);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CustomersExistentes;
	}

	public void persist(File file, HashMap<Integer, Customer> hash) {
		try {
			mapper.writeValue(file, hash);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
