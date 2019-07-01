package tvs.web.validators;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import tvs.web.model.FormDataWithFile;
@Service
public class MovieFormWithDatasValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FormDataWithFile.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		FormDataWithFile bucket = (FormDataWithFile) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dest_name",
                "movieFormPage.name");
//		System.out.println(bucket.getFile().getSize());
		if (bucket.getFile() != null && bucket.getFile().getSize() == 0){
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file",
//                    "movieFormPage.file.empty");
            errors.rejectValue("file", "movieFormPage.file.empty");
        }else if (bucket.getFile() == null) {
        	errors.rejectValue("file", "movieFormPage.file.null");
        }
	}

}
