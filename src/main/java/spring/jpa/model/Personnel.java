package spring.jpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Personnel")
public class Personnel extends Contact{

	 private String relationship;

	
	public Personnel() {
		super();
		this.setType("Personnel");
	}

	public Personnel(int number, String name) {
		super(number, name);
		
	}

	public Personnel(int number, String name,String relationship) {
		super(number, name);
		 this.relationship = relationship;
	}
	
	 public String getRelationship() {
	        return relationship;
	    }

	    public void setRelationship(String relationship) {
	        this.relationship = relationship;
	    }

		@Override
		public String toString() {
			return "Personnel [relationship=" + relationship + ", number=" + number + ", name=" + name + "]";
		}

	

	
}
