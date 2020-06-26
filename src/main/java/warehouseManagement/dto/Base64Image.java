package warehouseManagement.dto;

public class Base64Image {

    private String base64Image;
    private String nameImage;

    public Base64Image(String base64Image, String nameImage) {
        this.base64Image = base64Image;
        this.nameImage = nameImage;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }
}
