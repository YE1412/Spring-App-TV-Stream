package tvs.web.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tvs.daohibernate.model.User;
@Service
public class UserRegisterFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "userRegisterFormPage.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "userRegisterFormPage.password.empty");
		if (user.getPass().length() < 8) {
			errors.rejectValue("pass", "userRegisterFormPage.password.wrong");
		}
	}

}
