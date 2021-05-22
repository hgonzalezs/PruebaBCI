package Proyecto.Prueba.BCI.Validations;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.Rule;
import org.passay.RuleResult;

public class Validate {
	
	   public static List<String> ValidatePassword(String Pass) {
		   try {
		      List<Rule> rules = new ArrayList<>();
		      rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
		      rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
		      rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));

		      Properties props = new Properties();
		     
				props.load(new FileInputStream("messages.properties"));
			
		      MessageResolver resolver = new PropertiesMessageResolver(props);

		      PasswordValidator validator = new PasswordValidator(resolver, rules);
		      PasswordData password = new PasswordData(Pass);
		      RuleResult result = validator.validate(password);
		      if(result.isValid()){
		    	  return null;
		      } else {
		         return validator.getMessages(result);            
		      }
		      
			  } catch (Exception e) {
				   List<String> error = Arrays.asList(new String[]{e.getMessage()});
				   return error;
				}
		   }

}
