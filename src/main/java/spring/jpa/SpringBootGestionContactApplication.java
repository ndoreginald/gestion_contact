package spring.jpa;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import spring.jpa.model.Contact;
import spring.jpa.repository.ContactRepository;


@SpringBootApplication
public class SpringBootGestionContactApplication {
	
	static ContactRepository contactRepos;

	public static void main(String[] args) {
		
		ApplicationContext contexte = SpringApplication.run(SpringBootGestionContactApplication.class, args);
		
		contactRepos =contexte.getBean(ContactRepository.class);
		
		//Contact c1 = new Contact(6996110,"germain");
		//contactRepos.save(c1);
		
		
		afficherTousLesContact();
		
	}

	static void afficherTousLesContact()
	{
		System.out.println("***************************************");
		// Lister l'ensemble des contacts
		System.out.println("Afficher tous les contacts...");
		
		System.out.println("***************************************");
		
		List<Contact> lp = contactRepos.findAll();
		for (Contact p : lp)
		{
			System.out.println(p);
		}
			System.out.println("***************************************");
	}
}
