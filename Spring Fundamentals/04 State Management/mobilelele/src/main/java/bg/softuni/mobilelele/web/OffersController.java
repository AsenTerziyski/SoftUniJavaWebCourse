package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.interfaces.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OffersController {
    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }
}
