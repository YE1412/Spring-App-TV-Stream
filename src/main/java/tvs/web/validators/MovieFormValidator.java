package tvs.web.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tvs.daohibernate.model.Movie;
@Service
public class MovieFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Movie.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
//		Movie m = (Movie) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "movieFormPage.name");
	}

}
