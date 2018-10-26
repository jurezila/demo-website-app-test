package co.c3.vw.tab.module.shortnews.bean;
import co.c3.magnolia.core.bean.AbstractAsset;
import co.c3.magnolia.core.bean.jcr.NodeId;
import co.c3.magnolia.core.bean.jcr.NodeName;
import co.c3.magnolia.core.bean.jcr.NodeProperty;
import co.c3.magnolia.module.tag.bean.Tag;
import co.c3.magnolia.module.video.bean.Video;
import co.c3.magnolia.standard.bean.StandardAsset;
import info.magnolia.jcr.util.NodeTypes;
import lombok.Getter;
import lombok.Setter;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Shortnews implements Serializable {
    private static final long serialVersionUID = ObjectStreamClass.lookupAny(Shortnews.class).getSerialVersionUID();

    @NodeId
    protected String identifier;

    @NodeName
    protected String name;

    @NodeProperty
    protected String title;

    @NodeProperty
    protected String text;

    @NodeProperty
    protected Date date;

    @NodeProperty
    private String media;

    @NodeProperty(name ="mediaasset")
    private StandardAsset image;

    @NodeProperty(name ="mediavideo")
    private Video video;

    @NodeProperty(name = "linkType")
    protected String linkType;

    @NodeProperty(name = "linkTypepage")
    protected String linkTypepage;

    @NodeProperty(name = "linkTypeexternal")
    protected String linkTypeexternal;

    protected String link;

}
