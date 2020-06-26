package warehouseManagement.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import warehouseManagement.dto.ListResult;
import warehouseManagement.dto.SupplierDTO;
import warehouseManagement.exception.commonException.DupplicateFieldException;
import warehouseManagement.exception.commonException.NoDataException;
import warehouseManagement.exception.commonException.NotFoundException;
import warehouseManagement.exception.commonException.UnknownException;
import warehouseManagement.exception.supplierException.SupplierException;
import warehouseManagement.service.impl.SupplierServiceImpl;

@RestController
@RequestMapping("suppliers")
@CrossOrigin
public class SupplierApi<T> {

    @Autowired
    SupplierServiceImpl supplierService;

    //Exception No Data
    @GetMapping("")
    public ResponseEntity<?> getAllSupplier() throws RuntimeException {
        try {
            List<SupplierDTO> supplierDTOS = supplierService.findAll();
            return new ResponseEntity<>(supplierDTOS, HttpStatus.OK);
        } catch (NoDataException ex) {
            throw ex;
        } catch (Exception exception) {
            throw new UnknownException();
        }
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<?> getSupplierByFilter(@PathVariable("filter") String search, @RequestParam("page") Integer pageNo, @RequestParam("size") Integer size) {
        try {
            //int size = 20;
            ListResult<SupplierDTO> listResult = new ListResult<>();
            List<SupplierDTO> supplier = supplierService.findByFilter(search, pageNo, size, listResult);
            listResult.setListResult(supplier);
            return new ResponseEntity<>(listResult, HttpStatus.OK);
        } catch (NoDataException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UnknownException();
        }
    }

    //Exception No data
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<?> getAllSupplierPagination(@PathVariable("page") int pageNo, @PathVariable("size") int size) throws RuntimeException {
        try {
            //int size = 5;
            List<SupplierDTO> supplierDTOs = supplierService.findAll(pageNo, size);
            return new ResponseEntity<>(supplierDTOs, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (RuntimeException exception) {
            throw new SupplierException("Không thể lấy dữ liệu");
        }
    }

    //Exception Khong ton tai
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getOneSupplier(@PathVariable("id") long id) throws RuntimeException {
        try {
            return new ResponseEntity<SupplierDTO>(supplierService.findById(id), HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new UnknownException();
        }
    }

    //Validate -- 
    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody SupplierDTO supplierDTO) {
        try {
            SupplierDTO supplier = supplierService.save(supplierDTO);
            return new ResponseEntity<SupplierDTO>(supplier, HttpStatus.OK);
        } catch (DupplicateFieldException ex) {
            throw ex;
        } catch (Exception exception) {
            //if (exception instanceof MethodArgumentNotValidException) throw new MethodArgumentNotValidException();
            throw new UnknownException();
        }
    }

    //Exception khong ton tai
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        try {
            supplierService.delete(id);
            return new ResponseEntity<Integer>(supplierService.getTotalItem(), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new SupplierException("Xóa nhà cung cấp không thành công");
        }
    }

    // Exception khong ton tai
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable("id") Long id, @Valid @RequestBody SupplierDTO supplierDTO) {
        try {
            SupplierDTO supplier = supplierService.update(supplierDTO, id);
            return new ResponseEntity<SupplierDTO>(supplier, HttpStatus.OK);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception exception) {
            throw new UnknownException();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalItem() {
        try {
            return new ResponseEntity<Integer>(supplierService.getTotalItem(), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new UnknownException();
        }
    }

    //    @GetMapping("/name/{name}")
//    public ResponseEntity<SupplierDTO> getOneSupplierByName(@PathVariable("name") String name)
//    {
//        try{
//            SupplierDTO supplier = supplierService.findByName(name);
//            return new ResponseEntity<SupplierDTO>(supplier,HttpStatus.OK);
//        }
//        catch(NotFoundException ex)
//        {
//            throw ex;
//        }catch(Exception ex)
//        {
//            throw new UnknownException();
//        }
//    }

    //
}