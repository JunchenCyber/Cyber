package bloggame.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bloggame.Blogger;
import bloggame.data.BloggerRepository;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

	private BloggerRepository bloggerRepository;

	@Autowired
	public BloggerController(BloggerRepository bloggerRepository) {
		this.bloggerRepository = bloggerRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm() {
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@Valid Blogger blogger, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		bloggerRepository.save(blogger);
		return "redirect:/blogger/" + blogger.getUsername();
	}

	@RequestMapping(value = "/{username}", method = GET)
	public String showBloggerProfile(@PathVariable String username, Model model) {
		Blogger blogger = bloggerRepository.findByUsername(username);
		model.addAttribute(blogger);
		return "profile";
	}

}
