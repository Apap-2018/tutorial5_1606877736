package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.*;
import com.apap.tutorial5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "dealerId") Long dealerId, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        model.addAttribute("dealer", dealer);
        model.addAttribute("judul", "Add Car");
        return "addCar";
    }

    @RequestMapping(value = "/car/add", params={"addRow"})
    private String addRow(@ModelAttribute DealerModel dealer, Model model){
        CarModel car = new CarModel();
        car.setDealer(dealer);
        List<CarModel> newList = dealer.getListCar();
        if(newList == null) {
            newList = new ArrayList<CarModel>();
            newList.add(car);
        }else{
            newList.add(car);
        }
        dealer.setListCar(newList);

        model.addAttribute("dealer", dealer);
        model.addAttribute("judul", "Add Car");
        return "addCar";
    }

    @RequestMapping(value= "/car/add", params={"removeRow"})
    private String removeRow(@ModelAttribute DealerModel dealer, final HttpServletRequest req, Model model){
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        dealer.getListCar().remove(rowId.intValue());
        model.addAttribute("dealer", dealer);
        model.addAttribute("judul", "Add Car");
        return "addCar";
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.POST)
    private String addCarSubmit(@ModelAttribute DealerModel dealer, Model model){
        for(CarModel car : dealer.getListCar()){
            car.setDealer(dealerService.getDealerDetailById(dealer.getId()).get());
            carService.addCar(car);
        }
        model.addAttribute("judul", "Add Success");
        return "add";
    }

    @RequestMapping(value = "/car/delete", method = RequestMethod.POST)
    private String delete(@ModelAttribute DealerModel dealer, Model model){
        for (CarModel car : dealer.getListCar()){
            carService.deleteCar(car);
        }
        model.addAttribute("judul", "Delete Success");
        return "delete";
    }

    @RequestMapping(value = "/car/update/{carId}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "carId") Long carId, Model model){
        CarModel archive = carService.getCarById(carId).get();
        model.addAttribute("newCar", new CarModel());
        model.addAttribute("car", archive);
        model.addAttribute("judul", "Update Car");
        return "updateCar";
    }

    @RequestMapping(value = "/car/update/{carId}", method = RequestMethod.POST)
    private String updateSubmit(@PathVariable(value = "carId") Long carId, @ModelAttribute CarModel newCar, Model model){
        carService.updateCar(carId,newCar);
        model.addAttribute("judul", "Update Success");
        return "update";
    }

}
