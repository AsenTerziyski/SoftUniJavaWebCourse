package com.example.myproject.web;

import com.example.myproject.model.binding.MessageSendBindingModel;
import com.example.myproject.service.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ContactsController {
    private final GuestService guestService;

    public ContactsController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    @ModelAttribute
    public MessageSendBindingModel messageSendBindingModel () {
        return new MessageSendBindingModel();
    }

    @PostMapping("/contacts/send")
    public String sendEmail (@Valid MessageSendBindingModel messageSendBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        boolean hasErrors = bindingResult.hasErrors();
        System.out.println();

        if (hasErrors) {
            redirectAttributes
                    .addFlashAttribute("messageSendBindingModel", messageSendBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.messageSendBindingModel", messageSendBindingModel);
            return "contacts";
        }

        String email = messageSendBindingModel.getEmail();
        String text = messageSendBindingModel.getText();
        boolean guestAdded = this.guestService.receiveEmailAndCreateNewGuestByEmailIfNotExistsOrAddsMessageToExistingGuest(email, text);

        return "index";
    }

}
