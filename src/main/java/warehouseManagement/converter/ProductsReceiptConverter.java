package warehouseManagement.converter;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import warehouseManagement.dto.ProductsReceiptDTO;

import warehouseManagement.entity.ProductsReciptEntity;



@Component
public class ProductsReceiptConverter {
	public List<ProductsReciptEntity> convertDTOToEntityList(List<ProductsReceiptDTO> dto){
		ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ProductsReciptEntity>>(){}.getType();
        List<ProductsReciptEntity> postDTOList = modelMapper.map(dto, listType);
        return postDTOList;
    }
	public List<ProductsReceiptDTO> convertEntityToDTOList(List<ProductsReciptEntity> dto){
		ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ProductsReceiptDTO>>(){}.getType();
        List<ProductsReceiptDTO> postDTOList = modelMapper.map(dto, listType);
        return postDTOList;
    }
	public ProductsReceiptDTO convertToDTO(ProductsReciptEntity entity) {
		ModelMapper modelMapper = new ModelMapper();
		ProductsReceiptDTO dto=modelMapper.map(entity, ProductsReceiptDTO.class);
		return dto;
	}
	public ProductsReciptEntity convertToEntity(ProductsReceiptDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ProductsReciptEntity entity=modelMapper.map(dto, ProductsReciptEntity.class);
		return entity;
	}
}
