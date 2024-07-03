package spring.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.jpa.model.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {

	//List<Contact> findByTypeLikeOrderByNameAsc(String mc);
	//List<Contact> findByNameOrderByNameAsc(String mc);
	//List<Contact> findByTypeLikeOrderByNameAsc(String mc, String type);
	//List<Contact> findByNameLikeAndType(String mc, Contact user);
	List<Contact> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    List<Contact> findByTypeAndNameContainingIgnoreCaseOrderByNameAsc(String type, String name);

}
