package cn.shineiot.base.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author GF63
 */
@Entity
public class User {

    /**
     * admin : false
     * chapterTops : []
     * collectIds : [-1,7601]
     * email :
     * icon :
     * id : 1963
     * nickname : 请输入用户名
     * password :
     * publicName : 请输入用户名
     * token :
     * type : 0
     * username : 请输入用户名
     */
    @Id(autoincrement = true)
    private long id;

    private boolean admin;
    private String email;
    private String icon;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private int type;
    private String username;
//    private String[] chapterTops;
//    private String[] collectIds;

    @Generated(hash = 1472559659)
    public User(long id, boolean admin, String email, String icon, String nickname,
            String password, String publicName, String token, int type,
            String username) {
        this.id = id;
        this.admin = admin;
        this.email = email;
        this.icon = icon;
        this.nickname = nickname;
        this.password = password;
        this.publicName = publicName;
        this.token = token;
        this.type = type;
        this.username = username;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getAdmin() {
        return this.admin;
    }

   /* public String[] getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(String[] chapterTops) {
        this.chapterTops = chapterTops;
    }

    public String[] getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(String[] collectIds) {
        this.collectIds = collectIds;
    }*/
}
