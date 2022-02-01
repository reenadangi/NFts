package com.user.art.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.user.art.models.Art;
import com.user.art.models.Rating;
import com.user.art.models.User;
import com.user.art.services.ArtService;
import com.user.art.services.UserService;
import com.user.art.validators.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  UserValidator validators;
		
	@Autowired
	private ArtService aService;
	
	private String IMAGE_FOLDER="src/main/resources/static/imgs/";
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        // if result has errors, return the registration page (don't worry about validations just now)
		// Email/password validation 
		validators.validate(user, result);
		
		if(result.hasErrors()) {
			return "index.jsp";
		} 
		else {
			User newUser=userService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			return "redirect:/arts";

		}
        // else, save the user in the database, save the user id in session, and redirect them to the /home route
    }
	
	@PostMapping("/login") 
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	        // if the user is authenticated, save their user id in session
		if(userService.authenticateUser(email, password)) {
			User user=userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/arts";
			
			
		}
	        // else, add error messages and return the login page
		 redirectAttributes.addFlashAttribute("error", "Invaild Email/Pass!!!");
		 return "redirect:/";
		
	    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
		session.invalidate();
        // redirect to login page
		return "redirect:/";
		
    }
	 
//	 **********************Art Routes************************* 

	// Art Dashboard
		@GetMapping("/arts")
		public String arts(Model model, HttpSession session) {
			if (session.getAttribute("userId") != null) {
				List<Art> arts = this.aService.allArts();
//				Get user from session 
				User user = userService.findUserById((Long) session.getAttribute("userId"));
				model.addAttribute("arts", arts);
				model.addAttribute("user", user);
				return "arts.jsp";
			}
			return "redirect:/";
		}

		// New Art Page
		@GetMapping("/arts/new")
		public String index(@ModelAttribute("newArt") Art art) {
			//Get User from session and pass it in model
			return "new.jsp";
		}
		// Create Art
		@PostMapping("/arts/create")
		public String create(@Valid @ModelAttribute("newArt") Art art, BindingResult result, @RequestParam("pic") MultipartFile file) {
			if(file.isEmpty()) {
				System.err.println("No file");
			}
			else {
				System.out.println(file.getOriginalFilename());
				try {
					byte[] bytes=file.getBytes();
					Path path=Paths.get(IMAGE_FOLDER + file.getOriginalFilename());
					FileOutputStream output = new FileOutputStream(IMAGE_FOLDER+file.getOriginalFilename());
				     output.write(file.getBytes());
					art.setArtUrl("/imgs/" + file.getOriginalFilename());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (result.hasErrors()) {
				
				return "new.jsp";
			} else {
				System.err.println(art.getArtUrl());	
			   aService.createArt(art);
				return "redirect:/arts";
			}
			
		}
		
		// Get One Art details
				@GetMapping("/arts/art/{id}")
				public String project(@PathVariable("id") Long artId, Model model, HttpSession session, @ModelAttribute("newRating") Rating rating) {
					Art art = aService.getOneArt(artId);
					model.addAttribute("art", art);
					model.addAttribute("userLoggedIn",(Long)session.getAttribute("userId"));
					model.addAttribute("user",userService.findUserById((Long)session.getAttribute("userId")));
					return "art.jsp";
				}

		
//				Add rating - /arts/rateArt/
				@PostMapping("/arts/rateArt")
				public String rate( @Valid @ModelAttribute("newRating") Rating  rating,
						BindingResult result) {
						aService.addRating(rating);
						return "redirect:/arts";
				}
		
				@GetMapping("/arts/delete/{id}")
				public String delete(@PathVariable("id") long artId) {
					System.out.println(artId);
					aService.deleteArt(artId);
					return "redirect:/arts";
				}
				
				@GetMapping("/arts/edit/{id}")
				public String edit(@PathVariable("id")long id, Model model) {
					model.addAttribute("editArt",aService.getOneArt(id));
					return "edit.jsp";
				}
				
				@PatchMapping("/arts/update/{id}")
				public String update(@Valid @ModelAttribute("editArt") Art art,BindingResult results,@PathVariable("id") long id ) {
					if(results.hasErrors()) {
						return "edit.jsp";
					}else {
						
						aService.updateArt(art);
						return "redirect:/arts";
					}
					
					
					
				}
	
}
