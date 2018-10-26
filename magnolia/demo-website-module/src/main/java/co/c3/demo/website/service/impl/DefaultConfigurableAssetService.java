package co.c3.demo.website.service.impl;

import co.c3.magnolia.core.bean.AbstractAsset;
import co.c3.magnolia.core.service.BeanService;
import co.c3.magnolia.core.service.ConfigService;
import co.c3.magnolia.core.service.impl.AbstractConfigurableAssetService;
import co.c3.magnolia.standard.bean.StandardAsset;
import info.magnolia.cms.i18n.I18nContentSupport;
import info.magnolia.dam.templating.functions.DamTemplatingFunctions;
import info.magnolia.init.MagnoliaConfigurationProperties;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jcr.RepositoryException;
import java.io.IOException;

@Singleton
@Slf4j
public class DefaultConfigurableAssetService extends AbstractConfigurableAssetService<StandardAsset> {
    private final I18nContentSupport i18n;


    @Inject
    public DefaultConfigurableAssetService(BeanService beanService, DamTemplatingFunctions damTemplatingFunctions, ConfigService configService, @Nullable I18nContentSupport i18n, MagnoliaConfigurationProperties config) {
        super(beanService, damTemplatingFunctions, configService, i18n, config);
        this.i18n = i18n;
        beanService.registerFindableFactory(AbstractAsset.class, this);
        beanService.registerFindableFactory(StandardAsset.class, this);
        beanService.registerFindableFactory(StandardAsset.class, this);
    }

    @Override
    public void indexImage(String identifier) throws RepositoryException, IOException {
    }

    @Override
    public void unindexImage(String identifier) throws IOException {
    }

    @Override
    public void indexPdf(String identifier) throws RepositoryException, IOException {
    }

    @Override
    public void unindexPdf(String identifier) throws RepositoryException, IOException {
        //TODO: implement unindexPdf method
        log.warn("TODO: implement unindexPdf method");
    }

    @Override
    public void indexAudio(String identifier) throws RepositoryException, IOException {
        //TODO: implement indexAudio method
        log.warn("TODO: implement indexAudio method");
    }

    @Override
    public void unindexAudio(String identifier) throws RepositoryException, IOException {
        //TODO: implement unindexAudio method
        log.warn("TODO: implement unindexAudio method");
    }

    @Override
    public void indexVideo(String identifier) throws RepositoryException, IOException {
        //TODO: implement indexVideo method
        log.warn("TODO: implement indexVideo method");
    }

    @Override
    public void unindexVideo(String identifier) throws RepositoryException, IOException {
        //TODO: implement unindexVideo method
        log.warn("TODO: implement unindexVideo method");
    }

    @Override
    protected StandardAsset createNewAsset() {
        return new StandardAsset();
    }

}
