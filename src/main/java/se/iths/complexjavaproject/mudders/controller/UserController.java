package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import se.iths.complexjavaproject.mudders.entity.User;
import se.iths.complexjavaproject.mudders.exception.EmailExistsException;
import se.iths.complexjavaproject.mudders.model.UserModel;
import se.iths.complexjavaproject.mudders.service.UserService;

import javax.validation.Valid;

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        return "registration";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserModel userModel,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userModel, result).toEntity();
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", userModel);
        }
        else {
            return new ModelAndView("successRegister", "user", userModel);
        }
    }

    private UserModel createUserAccount(UserModel userModel, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userModel);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered.toModel();
    }

}
