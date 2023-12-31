package lk.ijse.springboot.vehicleService.api;

import jakarta.validation.Valid;
import lk.ijse.springboot.vehicleService.bo.VehicleBO;
import lk.ijse.springboot.vehicleService.bo.VehicleCategoryBO;
import lk.ijse.springboot.vehicleService.bo.impl.VehicleCategoryBoImple;
import lk.ijse.springboot.vehicleService.dto.VehicleCategoryDto;
import lk.ijse.springboot.vehicleService.dto.VehicleDTO;
import lk.ijse.springboot.vehicleService.entity.VehicleCategory;
import lk.ijse.springboot.vehicleService.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/vehicleService")
@CrossOrigin
public class VehicleAPI {

    private final VehicleBO vehicleBO;
    private final VehicleCategoryBO categoryBO;

    @Autowired
    public VehicleAPI(VehicleBO vehicleBO, VehicleCategoryBO categoryBO) {
        this.vehicleBO = vehicleBO;
        this.categoryBO = categoryBO;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil save(@ModelAttribute @Valid VehicleDTO vehicleDTO,
                             @ModelAttribute VehicleCategoryDto vehicleCategory) throws IOException {
        vehicleDTO.setVehicleCategory(vehicleCategory);
        vehicleBO.save(vehicleDTO);
        return new ResponseUtil(200,"Saved Success",null);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil update(@ModelAttribute @Valid VehicleDTO vehicleDTO,
                               @ModelAttribute VehicleCategoryDto vehicleCategory) throws IOException {

        vehicleDTO.setVehicleCategory(vehicleCategory);
        vehicleBO.update(vehicleDTO.getVehicleId(), vehicleDTO);
        return new  ResponseUtil(200,"OK",null);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil delete(@PathVariable String id){
       vehicleBO.delete(id);
        return new  ResponseUtil(200,"OK",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil search(@PathVariable String id){
        VehicleDTO vehicleDTO = vehicleBO.search(id);
        return new  ResponseUtil(200,"OK", vehicleDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAll(){
        List<VehicleDTO> all= vehicleBO.getAll();
        return new ResponseUtil(200,"OK",all);
    }



}
