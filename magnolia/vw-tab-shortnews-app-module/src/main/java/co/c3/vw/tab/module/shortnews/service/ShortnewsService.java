package co.c3.vw.tab.module.shortnews.service;

import co.c3.magnolia.core.service.Findable;
import co.c3.vw.tab.module.shortnews.bean.Shortnews;

import java.util.List;
import java.util.Locale;

public interface ShortnewsService extends Findable<Shortnews> {
    List<Shortnews> findByFolderIdAndLocale(String id, Locale locale);
    List<Shortnews> findByFolderIdAndLocale(String id, Locale locale, boolean recursive);
}
