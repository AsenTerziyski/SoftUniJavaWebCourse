package com.example.myproject.web;

import com.example.myproject.model.binding.OfferAddBindingModel;
import com.example.myproject.model.binding.PricesEditBindingModel;
import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.model.view.RoomPricesView;
import com.example.myproject.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/prices")
public class PricesController {
    private final RoomService roomService;

    public PricesController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public String getPricesPage(Model model) {
        List<RoomPricesView> allPrices = this.roomService.getAllPrices();
        model.addAttribute("allRoomsPrices", allPrices);
        return "prices-all";
    }


    @GetMapping("/edit")
    public String getEditPricesPage() {
        return "prices-edit";
    }

    @ModelAttribute
    public PricesEditBindingModel pricesEditBindingModel() {
        return new PricesEditBindingModel();
    }

    @PostMapping("/edit/post-price")
    public String editPrices(@Valid PricesEditBindingModel pricesEditBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("pricesEditBindingModel", pricesEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.pricesEditBindingModel",
                            pricesEditBindingModel);
            return "prices-edit";
        }

        this.roomService.editPrice(pricesEditBindingModel);
        return "priceEditConfirmation";
    }
}
