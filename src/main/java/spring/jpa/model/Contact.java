package spring.jpa.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CONTACT")
@DiscriminatorValue("Contact")
public class Contact implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	protected Long id;
	
	@NotNull(message = "Le numéro est obligatoire")
	@Digits(integer = 9, fraction = 0, message = "Le numéro doit contenir exactement 9 chiffres")
	protected int number; 
	@Column(length = 50)
	protected String name;
	protected String type;
	
	public Contact() {
		super();
		
	}
	
	public Contact(Long id, int number, String name) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
	}

	public Contact(int number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	 public String getType() {
	        return type;
	    }

	 public void setType(String type) {
	        this.type = type;
	    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Contact [number=" + number + ", name=" + name + "]";
	}
	
	
}
