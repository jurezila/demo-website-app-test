package co.c3.demo.website.form;

import co.c3.magnolia.module.video.C3MagnoliaVideoModule;
import info.magnolia.dam.app.assets.field.translator.AssetCompositeIdKeyTranslator;
import info.magnolia.dam.app.ui.field.DamFilePreviewComponent;
import info.magnolia.dam.jcr.DamConstants;
import info.magnolia.ui.form.field.converter.BaseIdentifierToPathConverter;
import info.magnolia.ui.form.field.definition.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Erdmann (C3 Creative Code and Content GmbH, stephan.erdmann@c3.co)
 */
public class MediaSwitchableFieldDefinition extends SwitchableFieldDefinition {

    public MediaSwitchableFieldDefinition() {
        super();
        this.setName("mediaType");
        this.setLabel("Media Type");
        this.setRequired(true);
        this.setSelectionType("radio");
        this.setType("String");
        this.setOptions(createOptions());
        this.setFields(createFields());
    }

    private List<SelectFieldOptionDefinition> createOptions() {
        List<SelectFieldOptionDefinition> options = new ArrayList<>();
        options.add(createAssetOption());
        options.add(createVideoOption());
        return options;
    }

    private SelectFieldOptionDefinition createAssetOption() {
        SelectFieldOptionDefinition asset = new SelectFieldOptionDefinition();
        asset.setName("asset");
        asset.setLabel("Asset");
        asset.setSelected(true);
        asset.setValue("asset");
        return asset;
    }

    private SelectFieldOptionDefinition createVideoOption() {
        SelectFieldOptionDefinition video = new SelectFieldOptionDefinition();
        video.setName("video");
        video.setLabel("Video");
        video.setSelected(false);
        video.setValue("video");
        return video;
    }


    private List<ConfiguredFieldDefinition> createFields() {
        List<ConfiguredFieldDefinition> fields = new ArrayList<>();
        fields.add(createAssetField());
        fields.add(createVideoField());
        return fields;
    }


    private ConfiguredFieldDefinition createAssetField() {
        LinkFieldDefinition asset = new LinkFieldDefinition();
        asset.setLabel("Asset");
        asset.setName("asset");
        asset.setTargetWorkspace(DamConstants.WORKSPACE);
        asset.setAppName("assets");
        asset.setIdentifierToPathConverter(new AssetCompositeIdKeyTranslator());
        ContentPreviewDefinition contentPreviewDefinition = new ContentPreviewDefinition();
        contentPreviewDefinition.setContentPreviewClass(DamFilePreviewComponent.class);
        asset.setContentPreviewDefinition(contentPreviewDefinition);
        asset.setType("String");
        asset.setRequired(false);
        return asset;
    }

    private ConfiguredFieldDefinition createVideoField() {
        LinkFieldDefinition video = new LinkFieldDefinition();
        video.setLabel("Video");
        video.setName("video");
        video.setTargetWorkspace(C3MagnoliaVideoModule.WORKSPACE);
        video.setAppName("video");
        video.setIdentifierToPathConverter(new BaseIdentifierToPathConverter());
        video.setType("String");
        video.setRequired(false);
        return video;
    }

}
