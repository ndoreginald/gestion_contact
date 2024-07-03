package spring.jpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Professionnel")
public class Professionnel extends Contact{

	private String company;
	
	public Professionnel() {
		super();
		this.setType("Professionnel");
	}

	public Professionnel(int number, String name) {
		super(number, name);
	}

	public Professionnel(int number, String name,String company) {
		super(number, name);
		 this.company = company;
	}

	 public String getCompany() {
	        return company;
	    }

	    public void setCompany(String company) {
	        this.company = company;
	    }

		@Override
		public String toString() {
			return "Professionnel [company=" + company + ", number=" + number + ", name=" + name + "]";
		}

		
	

	
}
