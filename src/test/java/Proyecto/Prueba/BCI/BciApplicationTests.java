package Proyecto.Prueba.BCI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BciApplicationTests {

	@RequestMapping("/user")
	public String welcomepage() {
		return "Welcome to Yawin Tutor";
	}

}