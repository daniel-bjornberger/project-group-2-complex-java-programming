package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        return "reg";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserModel userModel, BindingResult result) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userModel);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", userModel);
        }
        else {
            return new ModelAndView("login", "user", userModel);
        }
    }

    @PostMapping("/delete")
    public String deleteUserAccount(@RequestParam String email) {
        try {
            User userByEmail = userService.findUserByEmail(email);
            userService.deleteUserAccount(userByEmail);
        } catch (BadDataException e) {
            e.printStackTrace();
        }
        return "playercharacter";
    }

    private User createUserAccount(UserModel userModel) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(userModel);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}

