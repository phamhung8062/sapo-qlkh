package warehouseManagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import warehouseManagement.dto.Base64Image;
import warehouseManagement.dto.ImageDTO;
import warehouseManagement.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("products")
public class ImageAPI {

    @Autowired
    ImageService imageService;

    @GetMapping(path="/{idProduct}/images", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllImagesByIdProduct(
                                           @PathVariable("idProduct") Long idProduct) throws IOException {
        List<ImageDTO> imageDTOs = imageService.getAllImageByIdProduct(idProduct);
        return new ResponseEntity<>(imageDTOs, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @PostMapping(path = "/{idProduct}/images", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addImage(@RequestBody Base64Image base64Image,
                                           @PathVariable("idProduct") Long idProduct) throws IOException {
        ImageDTO imageDTO = imageService.addImageByIdProduct(idProduct, base64Image);
        return new ResponseEntity<>(imageDTO, HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @DeleteMapping(path = "/{idProduct}/images/{idImage}")
    public ResponseEntity<Object> deleteImage(@PathVariable("idProduct") Long idProduct,
                                           @PathVariable("idImage") Long idImage){
        imageService.deleteImageByIdProduct(idProduct, idImage);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }

    @CrossOrigin(value = "http://localhost:2020")
    @PutMapping(path = "/{idProduct}/images/{idImage}")
    public ResponseEntity<Object> changeImageActive(@PathVariable("idProduct") Long idProduct,
                                              @PathVariable("idImage") Long idImage){
        imageService.changeImageActive(idProduct, idImage);
        return new ResponseEntity<>("change image active success", HttpStatus.OK);
    }




}
