package exception;

public class ProductPriceNoPositiveException extends Exception{
    public ProductPriceNoPositiveException(String message) {
        super(message);
    }
}
