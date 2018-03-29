package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spittr.Spitter;
import spittr.data.SpittleRepository;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpittleRepository spittleRepository;


    @Autowired
    public SpitterController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = {"/register", "registerForm"}, method = POST)
    public String processRegistration(
            @Valid Spitter spitter, Errors errors) {

        if (errors.hasErrors()) {
            return "registerForm";
        }

        spittleRepository.save(spitter);
        return "redirect:/spitter/" +
                spitter.getUsername();
    }

    @RequestMapping(value = {"/{username}", "/profile/{username}"}, method = GET)
    public String showSpitterProfile(
            @PathVariable String username, Model model) {
        Spitter spitter = spittleRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }
}
