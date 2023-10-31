package lk.ijse.springboot.userService.api;

import jakarta.validation.Valid;
import lk.ijse.springboot.userService.dto.UserDTO;
import lk.ijse.springboot.userService.bo.UserBO;
import lk.ijse.springboot.userService.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userService")
@CrossOrigin
public class UserAPI {
    @Autowired
    UserBO userBO;

    @PostMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil save(@ModelAttribute @Valid UserDTO userDTO){
        userBO.save(userDTO);

        return new ResponseUtil(200,"Saved Success",null);
    }

    @PutMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseUtil update(String id,@RequestBody @Valid  UserDTO userDTO){
        userBO.update(id, userDTO);
        return new  ResponseUtil(200,"OK",null);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil delete(String id){
       userBO.delete(id);
        return new  ResponseUtil(200,"OK",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil search(@PathVariable String id){
        UserDTO userDTO = userBO.search(id);
        return new  ResponseUtil(200,"OK", userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAll(){
        List<UserDTO> all=userBO.getAll();
        return new ResponseUtil(200,"OK",all);
    }



}
