package com.common.validator;

import org.apache.commons.validator.UrlValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("urlValidator")
public class URLValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        verifyUrl(value.toString());
    }

    private void verifyUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();

        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://"))
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "URL must begin with http://", "URL must begin with http://"));

        if (!urlValidator.isValid(url)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "URL is not valid", "URL is not valid"));
        }
    }
}
