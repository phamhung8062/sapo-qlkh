package warehouseManagement.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


public class ReceiptSeachBuilder {

    private String code;
    private String status;
    private String statuspayment;
    private String supplierName;

    public String getCode() {
        return code;
    }


    public String getStatus() {
        return status;
    }

    public String getStatuspayment() {
        return statuspayment;
    }

    public String getSupplierName() {
        return supplierName;
    }

    private ReceiptSeachBuilder(Builder builder) {
        this.code = builder.code;
        this.status = builder.status;
        this.supplierName = builder.supplierName;
        this.statuspayment = builder.statuspayment;
    }

    public static class Builder {
        private String code;
        private String status;
        private String statuspayment;
        private String supplierName;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setStatuspayment(String statuspayment) {
            this.statuspayment = statuspayment;
            return this;
        }

        public Builder setSupplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public ReceiptSeachBuilder build() {
            return new ReceiptSeachBuilder(this);
        }
    }


}
