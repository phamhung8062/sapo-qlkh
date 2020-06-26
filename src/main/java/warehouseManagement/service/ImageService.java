package warehouseManagement.service;

import org.springframework.web.multipart.MultipartFile;
import warehouseManagement.dto.Base64Image;
import warehouseManagement.dto.ImageDTO;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    List<ImageDTO> getAllImageByIdProduct(Long idProduct);
    ImageDTO addImageByIdProduct(Long idProduct, Base64Image base64Image) throws IOException;
    void deleteImageByIdProduct(Long idProduct, Long idImage);
    boolean changeImageActive(Long idProduct, Long idImage);

}
