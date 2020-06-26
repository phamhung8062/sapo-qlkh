package warehouseManagement.api;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import warehouseManagement.dto.*;

import warehouseManagement.service.IReceiptsService;
import warehouseManagement.service.ProductService;
import warehouseManagement.service.impl.SupplierServiceImpl;
import warehouseManagement.service.impl.SupplierServiceImpl;


@RestController
@RequestMapping("/admin/receipts")
@CrossOrigin("http://localhost:2020")
public class ReceiptAPI {
    @Autowired
    private IReceiptsService receiptsService;
    @Autowired
    SupplierServiceImpl supplierService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ListResult<ReceiptDTO>> reList(@RequestParam("page") int page,
                                                         @RequestParam("limit") int limit) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        List<ReceiptDTO> receiptDTOs = receiptsService.findAll(pageable);
        ListResult<ReceiptDTO> list = new ListResult<>();
        list.setListResult(receiptDTOs);
        list.setCount(receiptsService.getTotalItem());
        return new ResponseEntity<ListResult<ReceiptDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ListResult<ReceiptDTO>> reList(@RequestParam("page") int page,
                                                         @RequestParam("limit") int limit, @RequestParam("status") String status,
                                                         @RequestParam("statuspayment") String statuspayment,
                                                         @RequestParam("supplierName") String supplierName,
                                                         @RequestParam("code") String code) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        ReceiptSeachBuilder searchReceipt = new ReceiptSeachBuilder.Builder().setCode(code).setStatus(status).setStatuspayment(statuspayment).setSupplierName(supplierName).build();
        List<ReceiptDTO> receiptDTOs = receiptsService.search(pageable, searchReceipt);
        ListResult<ReceiptDTO> list = new ListResult<>();
        list.setListResult(receiptDTOs);
        list.setCount(receiptsService.getTotalItem());
        if(StringUtils.isNotBlank(code) || StringUtils.isNotBlank(status)||StringUtils.isNotBlank(statuspayment)||StringUtils.isNotBlank(supplierName)){
            list.setCount(receiptsService.getTotalItemFind(receiptDTOs));
        }

        return new ResponseEntity<ListResult<ReceiptDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("/haha")
    public ResponseEntity<ListResult<ReceiptDTO>> reListName(@RequestParam("page") int page,
                                                             @RequestParam("limit") int limit, @RequestParam("code") String code) throws ParseException {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        List<ReceiptDTO> receiptDTOs = receiptsService.findbyCode(code, pageable);
        ListResult<ReceiptDTO> list = new ListResult<>();
        list.setListResult(receiptDTOs);
        list.setCount(receiptsService.getTotalItem());
        if (StringUtils.isNotBlank(code)) {
            list.setCount(receiptsService.getTotalItemFind(receiptDTOs));
        }
        return new ResponseEntity<ListResult<ReceiptDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ReceiptDTO receiptDTO(@PathVariable Long id) throws ParseException {
        ReceiptDTO reDto = receiptsService.findOneRe(id);
        return reDto;
    }

    @PostMapping("/create")
    public Long save(@Valid @RequestBody ReceiptDTO dto) {
        Long id = receiptsService.save(dto);
        return id;
    }

    @PutMapping("/edit")
    public void edit(@RequestBody ReceiptDTO dto) {
        receiptsService.update(dto);
    }

    @PostMapping("/create/payment")
    public void updatepay(@RequestBody PaymentDTO dto) {
        receiptsService.savePayment(dto);
    }


    @PutMapping("/update/status")
    public void updatestatus(@RequestBody ReceiptDTO dto) {
        receiptsService.updateStatus(dto);
    }


    @RequestMapping(value = "/api/suppliers", method = RequestMethod.GET)
    public ResponseEntity<ListResult<SupplierDTO>> suList(@RequestParam("page") int page, @RequestParam("name") String name) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        List<SupplierDTO> receiptDTOs = receiptsService.findNameSupplier(name, pageable);
        ListResult<SupplierDTO> list = new ListResult<>();
        list.setListResult(receiptDTOs);
        list.setCount(supplierService.getTotalItem());
        if (StringUtils.isNotBlank(name)) {
            list.setCount(supplierService.getTotalItemFind(receiptDTOs));
        }
        return new ResponseEntity<ListResult<SupplierDTO>>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/products", method = RequestMethod.GET)
    public ResponseEntity<ListResult<ProductDTO>> proList(@RequestParam("page") int page, @RequestParam("name") String name) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        List<ProductDTO> productDTOS = receiptsService.findNameProduct(name, pageable);
        ListResult<ProductDTO> list = new ListResult<>();
        list.setListResult(productDTOS);
        list.setCount(productService.getTotalItem());
        if (StringUtils.isNotBlank(name)) {
            list.setCount(productService.getTotalItemFind(productDTOS));
        }
        return new ResponseEntity<ListResult<ProductDTO>>(list, HttpStatus.OK);

    }
    @PutMapping("/update")
    public void updateTagAndNote(@RequestBody ReceiptDTO dto) {
        receiptsService.updateNoteAndTag(dto);
    }

    @PostMapping("/create/supplier")
    public SupplierDTO supplier(@RequestBody SupplierDTO dto){
    SupplierDTO supplierDTO=receiptsService.quicksave(dto);
    return supplierDTO;
    }

}
