package cn.shineiot.login.bean;

/**
 * @author GF63
 */
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

    private boolean admin;
    private String email;
    private String icon;
    private int id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private int type;
    private String username;
//    private String[] chapterTops;
//    private String[] collectIds;

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

    public int getId() {
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
