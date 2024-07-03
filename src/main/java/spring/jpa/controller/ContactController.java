package spring.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import spring.jpa.model.Contact;
import spring.jpa.model.Personnel;
import spring.jpa.model.Professionnel;
import spring.jpa.repository.ContactRepository;



@Controller
@RequestMapping(value = "/contact")
public class ContactController {

	@Autowired
    private ContactRepository contactRepos;
	
	 // Afficher le formulaire de connexion
    @GetMapping("/firstPage")
    public String firstPage(Model model) {
    	 model.addAttribute("pageTitle", "Portail de Gestion de Contacts");
        return "firstPage";
    }
	
    @RequestMapping(value="/formContact",method=RequestMethod.GET)
	public String formContact (Model model)
	{
		model.addAttribute("contact", new Contact());
		return "formPage";
	}
    
	/*
	 * @RequestMapping(value="/save",method=RequestMethod.POST) public String save
	 * (Model model, @Valid Contact contact ,Personnel pers,Professionnel prof
	 * ,BindingResult bindingResult) {
	 * 
	 * if(contact instanceof Personnel) { contact = new Personnel(); } else if
	 * (contact instanceof Professionnel) { contact = new Professionnel(); } else {
	 * throw new IllegalArgumentException("Invalid contact type: "); }
	 * contactRepos.save(contact); return "redirect:homePage"; }
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model,Contact contact,
                       @RequestParam String type,
                       @RequestParam String name,
                       @RequestParam int number,
                       @RequestParam(required = false) String relationship,
                       @RequestParam(required = false) String company,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPage";
        }

      // Contact contact;

        if ("Personnel".equalsIgnoreCase(type)) {
            Personnel personnel = new Personnel();
            personnel.setName(name);
            personnel.setNumber(number);
            personnel.setRelationship(relationship);
            contact = personnel;
        } else if ("Professionnel".equalsIgnoreCase(type)) {
            Professionnel professionnel = new Professionnel();
            professionnel.setName(name);
            professionnel.setNumber(number);
            professionnel.setCompany(company);
            contact = professionnel;
        } else {
            throw new IllegalArgumentException("Invalid contact type: " + type);
        }

        contactRepos.save(contact);
        return "redirect:homePage";
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete (Long id, String motCle)
	{
    	contactRepos.deleteById(id);
    	return "redirect:homePage";
	}
    
    @RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit (Model model,
	@RequestParam (name="id")Long id)
	{
    	Contact c =(Contact)contactRepos.findById(id).orElse(null);
    	model.addAttribute("contact", c);
    	return "editPage";
	}
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
	public String update (Model model, @Valid Contact contact ,
	BindingResult bindingResult) {

	if (bindingResult.hasErrors())
	return "editProduit";
	contactRepos.save(contact);
	return "redirect:homePage";

	}
    
    @RequestMapping(value = "/homePage")
    public String homePage(Model model, 
                           @RequestParam(name = "motCle", defaultValue = "") String mc,
                           @RequestParam(name = "type", defaultValue = "tout") String type) {
        List<Contact> contacts = new ArrayList<>();
        if ("tout".equals(type)) {
            // Assurez-vous que cette méthode existe et accepte des jokers
            contacts = contactRepos.findByNameContainingIgnoreCaseOrderByNameAsc(mc);
        } else {
            // Assurez-vous que cette méthode existe et accepte des jokers
            contacts = contactRepos.findByTypeAndNameContainingIgnoreCaseOrderByNameAsc(type, mc);
        }
        
        model.addAttribute("motCle", mc);
        model.addAttribute("type", type);
        model.addAttribute("contact", contacts);
        return "homePage";
    }

}
    
//http://localhost:8080/contact/firstPage