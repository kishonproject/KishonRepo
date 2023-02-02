package com.ust.kishon.Controller;

import com.ust.kishon.Entity.Farmer;
import com.ust.kishon.Entity.Product;
import com.ust.kishon.Exception.FarmerNotFoundException;
import com.ust.kishon.Exception.ProductNotFoundException;
import com.ust.kishon.Repo.FarmerRepo;
import com.ust.kishon.Service.FarmerService;
import com.ust.kishon.Service.ProductService;
import com.ust.kishon.dto.FarmerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmer")
public class FarmerController {


    @Autowired
    private FarmerRepo farmerRepo;
    @Autowired
    private FarmerService farmerService;


    @PostMapping("/registerFarmer")
    public ResponseEntity<Farmer> registerfarmer(@RequestBody Farmer farmer){
        Farmer _farmer= farmerService.addFarmer(farmer);
        return new ResponseEntity<>(_farmer, HttpStatus.CREATED);
    }



    @PutMapping("/updatefarmer/{farmerId}")
    public ResponseEntity<Farmer> updateFarmerDet( @PathVariable("farmerId") int farmerId, @RequestBody Farmer farmer) throws FarmerNotFoundException {
       Farmer _farmer= farmerService.updateFarmer(farmerId,farmer);
       return new ResponseEntity<>(_farmer,HttpStatus.OK);
    }
    @DeleteMapping("/deletefarmer/{id}")
    public ResponseEntity<String> deletefarmer(@PathVariable("id") int id){
        farmerService.deleteFarmer(id);
        return new ResponseEntity<>("Farmer Deleted Successfully",HttpStatus.NO_CONTENT);
    }




        @GetMapping("/getfarmerById/{farmerId}")
        public ResponseEntity<Farmer> getfarmerdetails(@PathVariable int farmerId) throws FarmerNotFoundException {
            Farmer _farmer =   farmerService.getfarmerById(farmerId);
            return new ResponseEntity<>(_farmer, HttpStatus.OK);
        }

        @GetMapping("/getProductdetails/{farmerId}")
        public ResponseEntity<List<Product>> getproductDetails(@PathVariable int farmerId)
        {
           List<Product> _product= farmerService.getProductDetailsUsingFarmerId(farmerId);
            return new ResponseEntity<>(_product,HttpStatus.OK);
        }


}
