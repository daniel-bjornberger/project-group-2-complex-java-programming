package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.service.UserService;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        return "registration";
    }


    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserModel userModel,
            BindingResult result,
            WebRequest request,
            Errors errors) {
        System.out.println("UserModel: "+userModel.toString());
        User registered = new User();
        if (!result.hasErrors()) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            registered = createUserAccount(userModel, result).toEntity();
        }
        if (registered == null) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            System.out.println("££££££££££££££££££££££££££££££££££");
            System.out.println("Result: "+result);
            System.out.println("Registered: "+registered);
            return new ModelAndView("registration", "user", userModel);
        }
        else {
            System.out.println("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€");
            return new ModelAndView("successRegister", "user", userModel);
        }
    }

    private UserModel createUserAccount(UserModel userModel, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userModel);
            System.out.println(registered);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered.toModel();
    }

}
