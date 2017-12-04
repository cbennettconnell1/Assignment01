package northwind.exception;

@SuppressWarnings("serial")
public class NoProductsException extends Exception{
	public NoProductsException(String message) {
		super(message);
	}

}
