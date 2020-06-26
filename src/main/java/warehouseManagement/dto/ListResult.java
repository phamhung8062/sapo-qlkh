package warehouseManagement.dto;

import java.util.List;

public class ListResult<T> {
    private List<T> ListResult;
    private int  count;

    public List<T> getListResult() {
        return ListResult;
    }

    public void setListResult(List<T> listResult) {
        ListResult = listResult;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
