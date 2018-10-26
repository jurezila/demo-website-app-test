package co.c3.vw.tab.module.shortnews.service.impl;

import co.c3.magnolia.core.bean.AbstractPage;
import co.c3.magnolia.core.service.BeanService;
import co.c3.magnolia.core.service.Findable;
import co.c3.magnolia.core.service.PageService;
import co.c3.magnolia.core.service.impl.AbstractFindableJcrService;
import co.c3.vw.tab.module.shortnews.VwTabShortnewsAppModule;
import co.c3.vw.tab.module.shortnews.bean.Shortnews;
import co.c3.vw.tab.module.shortnews.service.ShortnewsService;
import info.magnolia.cms.i18n.I18nContentSupport;
import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.predicate.NodeTypePredicate;
import info.magnolia.jcr.util.NodeTypes;
import info.magnolia.jcr.util.NodeUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class DefaultShortnewsServiceImpl extends AbstractFindableJcrService<Shortnews> implements ShortnewsService, Findable<Shortnews> {
    private PageService pageService;
    private BeanService beanService;

    @Inject
    public DefaultShortnewsServiceImpl(BeanService beanService, @Nullable I18nContentSupport i18nContentSupport, PageService pageService) {
        super(beanService, i18nContentSupport);
        beanService.registerFindableFactory(Shortnews.class, this);
        this.pageService = pageService;
        this.beanService = beanService;
    }

    @Override
    public Shortnews find(String id) {
        return findById(id, new Shortnews(), VwTabShortnewsAppModule.WORKSPACE);
    }

    @Override
    public Shortnews findByIdAndLocale(String id, Locale locale) {
        Shortnews news = findByIdAndLocale(id, new Shortnews(),VwTabShortnewsAppModule.WORKSPACE, locale);
        if (news.getLinkType() != null) {
            setImage(news);
            setLink(news);
        }
        return news;
    }

    @Override
    public List<Shortnews> findByFolderIdAndLocale(String id, Locale locale) {
        return findByFolderIdAndLocale(id, locale, false);
    }

    @Override
    public List<Shortnews> findByFolderIdAndLocale(String id, Locale locale, boolean recursive) {
        Set<Shortnews> shortnewsSet = new HashSet<>();
        try {
            Node folder = MgnlContext.getJCRSession(VwTabShortnewsAppModule.WORKSPACE).getNodeByIdentifier(id);
            if (!folder.isNodeType(NodeTypes.Folder.NAME)) {
                return null;
            }

            Iterator<Node> it = recursive ? NodeUtil.collectAllChildren(folder).iterator() : folder.getNodes();
            while (it.hasNext()) {
                Node node = it.next();
                if (node.isNodeType(VwTabShortnewsAppModule.NODETYE)) {
                    Shortnews news = new Shortnews();
                    shortnewsSet.add(beanService.updateBean(node, news, locale));
                    if (news.getLinkType() != null) {
                        setImage(news);
                        setLink(news);
                    }
                }
            }
        } catch (RepositoryException e) {
            log.warn("Can't retrieve node data for id {}", id, e);
            return null;
        }


        List<Shortnews> shortnewsList = shortnewsSet.stream().collect(Collectors.toList());
        Collections.sort(shortnewsList, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return shortnewsList;
    }

    private void setImage(Shortnews news) {
        if (news.getMedia() != null) {
//            log("video stuff goes here");
            if ("video".equals(news.getMedia()) && news.getVideo() != null) {
                news.setImage(news.getVideo().getImage());
            }
        }
    }

    private void setLink(Shortnews news) {
        switch (news.getLinkType()) {
            case "page":
                AbstractPage page = (AbstractPage) pageService.find(news.getLinkTypepage());
                news.setLink(page != null ? page.getLink() : null);
                break;
            case "external":
                news.setLink(news.getLinkTypeexternal());
                break;
        }
    }
}
