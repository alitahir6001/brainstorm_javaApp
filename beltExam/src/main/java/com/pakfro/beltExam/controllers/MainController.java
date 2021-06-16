package com.pakfro.beltExam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pakfro.beltExam.models.Idea;
import com.pakfro.beltExam.models.User;
import com.pakfro.beltExam.services.UserService;
import com.pakfro.beltExam.validation.UserValidator;

@Controller

public class MainController {
	
	private final UserService userService;
	private final UserValidator userValidator;
    
    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

	@GetMapping ("/")
	public String home(@ModelAttribute ("user") User user) {
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session ) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		
		//create a user with this information
		User u = this.userService.registerUser(user);
		//get the user that just got created's id and put it in session
		session.setAttribute("userid", u.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		//retrieve the userobject from the db who'se id matches the id stored in session
		Long id = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(id);
		
		model.addAttribute("loggedinuser", loggedinuser);
		model.addAttribute("banana", userService.findAllIdeas());

		
// 		Possibly need these later
		
//		model.addAttribute("allgroups", loggedinuser.getGroupsBelongTo());
//		model.addAttribute("notmygroups", this.userService.findGroupsNotBelongingTo(loggedinuser));
		return "dashboard.jsp";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes ) {
		
		
		Boolean isLegit = this.userService.authenticateUser(email, password);
		
		if(isLegit) {
			//if the email/pw matchy matchy, log them in redirect to dashboard
			
			//get the user with that email
			User user = this.userService.findByEmail(email);
			//put that users id in session
			session.setAttribute("userid", user.getId());
			return "redirect:/dashboard";
		}
		//if login is not successful, flash them a message
		redirectAttributes.addFlashAttribute("error", "Invalid login attempt");
		return "redirect:/";
	}
	
//	------- END OF LOGIN/REG METHODS ----------- //
	
	
	// Show all the ideas on the dashboard
	
//	@RequestMapping ("/dashboardz")
//	public String dashboard (Model model) {
////		this.userService.findAllIdeas();
//		model.addAttribute("banana", userService.findAllIdeas());
//
//		return "dashboard.jsp";
//	}
//	
	
	
	// Show the Create idea form
	@GetMapping ("/createnew")
	public String createNew(@ModelAttribute ("ideaz") Idea idea, Model model) {
//	
		return "createnew.jsp";
	}
	
	// Form processing for the create idea
	
	@PostMapping ("/makeidea")
	public String createIdea (@Valid @ModelAttribute ("ideaz")Idea idea, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			
			System.out.println(result.getAllErrors());
		return "createnew.jsp";
		} else {
			Long id = (Long)session.getAttribute("userid");
			User loggedinuser = this.userService.findUserById(id);
			idea.setCreator(loggedinuser);
			userService.createIdea(idea);
		}
		
		return "redirect:/dashboard";
		
	}
	
	// Show an idea
	
	@GetMapping("/show/{id}")
	public String showIdea(Model model, @PathVariable ("id")Long id) {
		
		Idea myIdea = this.userService.findAnIdea(id);
		model.addAttribute("thisIdea", myIdea);		
		return "show.jsp";
	}
	
	
	// Edit (aka update) an Idea
	
	@GetMapping ("/ideas/{id}/edit")
	public String editIdea (@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Idea idea = this.userService.findAnIdea(id);
        model.addAttribute("apple", idea);
        Long hoot = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(hoot);
		model.addAttribute("hoot", loggedinuser);
		
		return "edit.jsp";
	}
	
	
	
	@PostMapping ("/ideas/{id}/update")
	public String updateIDea(@Valid @ModelAttribute("apple") Idea idea, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
////			Group g = this.userService.findAGroup(id);
////			group.setMembers(g.getMembers());
			Idea i = userService.findAnIdea(id);
			i.getCreator();
			idea.setCreator(i.getCreator());
			
			this.userService.updateAnIdea(idea); 
			return "redirect:/dashboard";
		}
	}
	
	// Delete an Idea
	@GetMapping("/ideas/{id}/delete")
	public String deleteIdea(@PathVariable("id") Long id) {
//		Idea i = this.userService.findAnIdea(id);

		this.userService.deleteIdea(id);
		return "redirect:/dashboard";
	}

}
