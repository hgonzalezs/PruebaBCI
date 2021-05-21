package Proyecto.Prueba.BCI.Model;

public class ErrorControl {
	public ErrorControl(String message) {
		super();
		Message = message;
	}

	private String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
