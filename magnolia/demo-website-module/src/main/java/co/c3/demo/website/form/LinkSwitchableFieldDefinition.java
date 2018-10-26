package co.c3.demo.website.form;

import co.c3.demo.website.validator.ExternalLinkValidatorDefinition;
import info.magnolia.dam.app.assets.field.translator.AssetCompositeIdKeyTranslator;
import info.magnolia.dam.app.ui.field.DamFilePreviewComponent;
import info.magnolia.dam.jcr.DamConstants;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.ui.form.field.converter.BaseIdentifierToPathConverter;
import info.magnolia.ui.form.field.definition.*;
import info.magnolia.ui.form.validator.definition.FieldValidatorDefinition;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class LinkSwitchableFieldDefinition extends SwitchableFieldDefinition {

    private boolean internal = true;
    private boolean external = true;
    private boolean download = true;

    private List<SelectFieldOptionDefinition> additionalOptions = new ArrayList<>();
    private List<ConfiguredFieldDefinition> additionalFields = new ArrayList<>();

    public LinkSwitchableFieldDefinition() {
        super();
        this.setName("linkType");
        this.setLabel("Link");
        this.setRequired(true);
        this.setSelectionType("radio");
        this.setType("String");
        this.setOptions(createOptions());
        this.setFields(createFields());
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
        this.setOptions(createOptions());
        this.setFields(createFields());
    }

    public void setExternal(boolean external) {
        this.external = external;
        this.setOptions(createOptions());
        this.setFields(createFields());
    }

    public void setDownload(boolean download) {
        this.download = download;
        this.setOptions(createOptions());
        this.setFields(createFields());
    }

    public void setAdditionalOptions(List<SelectFieldOptionDefinition> additionalOptions) {
        this.additionalOptions = additionalOptions;
        this.setOptions(createOptions());
    }

    public void setAdditionalFields(List<ConfiguredFieldDefinition> additionalFields) {
        this.additionalFields = additionalFields;
        this.setFields(createFields());
    }

    private List<SelectFieldOptionDefinition> createOptions() {
        List<SelectFieldOptionDefinition> options = new ArrayList<>();
        if (internal) options.add(createPageOption());
        if (external) options.add(createExternalOption());
        if (download) options.add(createDownloadOption());
        options.addAll(additionalOptions);
        return options;
    }

    private SelectFieldOptionDefinition createPageOption() {
        SelectFieldOptionDefinition page = new SelectFieldOptionDefinition();
        page.setName("page");
        page.setLabel("Internal");
        page.setSelected(true);
        page.setValue("page");
        return page;
    }

    private SelectFieldOptionDefinition createExternalOption() {
        SelectFieldOptionDefinition external = new SelectFieldOptionDefinition();
        external.setName("external");
        external.setLabel("External");
        external.setSelected(false);
        external.setValue("external");
        return external;
    }

    private SelectFieldOptionDefinition createDownloadOption() {
        SelectFieldOptionDefinition download = new SelectFieldOptionDefinition();
        download.setName("download");
        download.setLabel("Download");
        download.setSelected(false);
        download.setValue("download");
        return download;
    }

    private List<ConfiguredFieldDefinition> createFields() {
        List<ConfiguredFieldDefinition> fields = new ArrayList<>();
        if (internal) fields.add(createPageField());
        if (external) fields.add(createExternalField());
        if (download) fields.add(createDownloadField());
        fields.addAll(additionalFields);
        return fields;
    }

    private ConfiguredFieldDefinition createPageField() {
        LinkFieldDefinition page = new LinkFieldDefinition();
        page.setName("page");
        page.setLabel(StringUtils.EMPTY);
        page.setTargetWorkspace(RepositoryConstants.WEBSITE);
        page.setAppName("pages");
        page.setIdentifierToPathConverter(new BaseIdentifierToPathConverter());
        page.setType("String");
        page.setRequired(false);
        return page;
    }

    private ConfiguredFieldDefinition createExternalField() {
        TextFieldDefinition external = new TextFieldDefinition();
        external.setName("external");
        external.setLabel(StringUtils.EMPTY);
        external.setType("String");
        external.setRequired(false);

        List<FieldValidatorDefinition> validators = new LinkedList<>();
        ExternalLinkValidatorDefinition externalLinkValidatorDefinition = new ExternalLinkValidatorDefinition();
        externalLinkValidatorDefinition.setName("link");
        externalLinkValidatorDefinition.setErrorMessage("Enter a valid link");
        validators.add(externalLinkValidatorDefinition);

        external.setValidators(validators);

        return external;
    }

    private ConfiguredFieldDefinition createDownloadField() {
        LinkFieldDefinition download = new LinkFieldDefinition();
        download.setName("download");
        download.setLabel(StringUtils.EMPTY);
        download.setTargetWorkspace(DamConstants.WORKSPACE);
        download.setAppName("assets");
        download.setIdentifierToPathConverter(new AssetCompositeIdKeyTranslator());
        ContentPreviewDefinition contentPreviewDefinition = new ContentPreviewDefinition();
        contentPreviewDefinition.setContentPreviewClass(DamFilePreviewComponent.class);
        download.setContentPreviewDefinition(contentPreviewDefinition);
        download.setType("String");
        download.setRequired(false);
        return download;
    }
}
