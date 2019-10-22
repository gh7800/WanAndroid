package cn.shineiot.base.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AndroidArticle {

    @Id(autoincrement = true)
    private long id;

    private String url;

    @Generated(hash = 898330906)
    public AndroidArticle(long id, String url) {
        this.id = id;
        this.url = url;
    }

    @Generated(hash = 1013300897)
    public AndroidArticle() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
