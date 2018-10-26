package co.c3.demo.website.validator;

import info.magnolia.ui.form.validator.definition.RegexpValidatorDefinition;

/**
 * @author Stephan Erdmann (C3 Creative Code and Content GmbH, stephan.erdmann@c3.co)
 */
public class ExternalLinkValidatorDefinition extends RegexpValidatorDefinition {

    private final static String URL_PATTERN ="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public ExternalLinkValidatorDefinition() {
        super();
        this.setPattern(URL_PATTERN);
    }

    @Override
    public void setPattern(String pattern) {
        super.setPattern(URL_PATTERN);
    }
}

