package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    private String home(Model model) {
        model.addAttribute("judul", "Home");
        return "home";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("judul", "Add Dealer");
        model.addAttribute("dealer", new DealerModel());
        return "addDealer";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute DealerModel dealer, Model model){
        dealerService.addDealer(dealer);
        model.addAttribute("judul", "Add Success");
        return "add";
    }

    @RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "dealerId") Long dealerId, Model model){
        DealerModel archive = dealerService.getDealerDetailById(dealerId).get();
        model.addAttribute("newDealer", new DealerModel());
        model.addAttribute("dealer", archive);
        model.addAttribute("judul", "Update Dealer");
        return "updateDealer";
    }

    @RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
    private String updateSubmit(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel newDealer, Model model){
        dealerService.updateDealer(dealerId,newDealer);
        model.addAttribute("judul", "Update Success");
        return "update";
    }

    @RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
    private String delete(@RequestParam(value = "dealerId", required = true) Long dealerId, Model model){
        DealerModel archive = dealerService.getDealerDetailById(dealerId).get();
        dealerService.deleteDealer(archive);
        model.addAttribute("judul", "Delete Success");
        return "delete";
    }

    @RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
    private String view(@RequestParam(value = "dealerId", required = true) Long dealerId, Model model){
        DealerModel archiveDealer = dealerService.getDealerDetailById(dealerId).get();
        List<CarModel> archive = archiveDealer.getListCar();
        Collections.sort(archive);
        archiveDealer.setListCar(archive);
        model.addAttribute("dealer", archiveDealer);
        model.addAttribute("judul", "View Dealer");
        return "view-dealer";
    }

    @RequestMapping(value = "/dealer/viewall", method = RequestMethod.GET)
    private String viewAll(Model model){
        List<DealerModel> listDealer = dealerService.getAllDealer();
        model.addAttribute("listdealer", listDealer);
        model.addAttribute("judul", "View All Dealer");
        return "view-all-dealer";
    }

}
