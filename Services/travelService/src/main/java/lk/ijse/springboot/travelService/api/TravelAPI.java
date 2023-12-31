package lk.ijse.springboot.travelService.api;

import jakarta.validation.Valid;
import lk.ijse.springboot.travelService.bo.TravelBO;
import lk.ijse.springboot.travelService.dto.DurationDto;
import lk.ijse.springboot.travelService.dto.PackageValueDto;
import lk.ijse.springboot.travelService.dto.TravelDTO;
import lk.ijse.springboot.travelService.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/travelService")
@CrossOrigin
public class TravelAPI {

    private final TravelBO travelBO;
    @Autowired
    public TravelAPI(TravelBO travelBO) {
        this.travelBO = travelBO;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil save(@ModelAttribute @Valid TravelDTO travelDTO,
                             @ModelAttribute @Valid DurationDto durationDto,
                             @ModelAttribute @Valid PackageValueDto packageValueDto) throws IOException {

        travelDTO.setTravelDurationDto(durationDto);
        travelDTO.setPackageValue(packageValueDto);
        travelBO.save(travelDTO);
        return new ResponseUtil(200,"Saved Success",null);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil update(@ModelAttribute @Valid TravelDTO travelDTO,
                               @ModelAttribute @Valid DurationDto travelDuration,
                               @ModelAttribute @Valid PackageValueDto packageValueDto) throws IOException {
        travelDTO.setTravelDurationDto(travelDuration);
        travelDTO.setPackageValue(packageValueDto);
        travelBO.update(travelDTO.getPackageId(), travelDTO);
        return new  ResponseUtil(200,"OK",null);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil delete(@PathVariable String id){
       travelBO.delete(id);
        return new  ResponseUtil(200,"OK",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil search(@PathVariable String id){
        TravelDTO travelDTO = travelBO.search(id);
        return new  ResponseUtil(200,"OK", travelDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAll(){
        List<TravelDTO> all= travelBO.getAll();
        return new ResponseUtil(200,"OK",all);
    }



}
