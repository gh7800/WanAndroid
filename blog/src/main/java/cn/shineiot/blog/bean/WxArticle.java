package cn.shineiot.blog.bean;

import java.util.List;

/**
 * @author GF63
 * 微信文章
 */
public class WxArticle {

	/**
	 * apkLink :
	 * audit : 1
	 * author : 鸿洋
	 * chapterId : 408
	 * chapterName : 鸿洋
	 * collect : false
	 * courseId : 13
	 * desc :
	 * envelopePic :
	 * fresh : false
	 * id : 9746
	 * link : https://mp.weixin.qq.com/s/qzyfKv6iOHlVobVYi-Q4Mw
	 * niceDate : 2019-10-15
	 * niceShareDate : 2019-10-17
	 * origin :
	 * prefix :
	 * projectLink :
	 * publishTime : 1571068800000
	 * selfVisible : 0
	 * shareDate : 1571314374000
	 * shareUser :
	 * superChapterId : 408
	 * superChapterName : 公众号
	 * tags : [{"name":"公众号","url":"/wxarticle/list/408/1"}]
	 * title : 换掉Retrofit+RxJava, 开启 LiveData+Retrofit之路
	 * type : 0
	 * userId : -1
	 * visible : 1
	 * zan : 0
	 */

	private String apkLink;
	private int audit;
	private String author;
	private int chapterId;
	private String chapterName;
	private boolean collect;
	private int courseId;
	private String desc;
	private String envelopePic;
	private boolean fresh;
	private int id;
	private String link;
	private String niceDate;
	private String niceShareDate;
	private String origin;
	private String prefix;
	private String projectLink;
	private long publishTime;
	private int selfVisible;
	private long shareDate;
	private String shareUser;
	private int superChapterId;
	private String superChapterName;
	private String title;
	private int type;
	private int userId;
	private int visible;
	private int zan;
	private List<TagsBean> tags;

	public String getApkLink() {
		return apkLink;
	}

	public void setApkLink(String apkLink) {
		this.apkLink = apkLink;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEnvelopePic() {
		return envelopePic;
	}

	public void setEnvelopePic(String envelopePic) {
		this.envelopePic = envelopePic;
	}

	public boolean isFresh() {
		return fresh;
	}

	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNiceDate() {
		return niceDate;
	}

	public void setNiceDate(String niceDate) {
		this.niceDate = niceDate;
	}

	public String getNiceShareDate() {
		return niceShareDate;
	}

	public void setNiceShareDate(String niceShareDate) {
		this.niceShareDate = niceShareDate;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getProjectLink() {
		return projectLink;
	}

	public void setProjectLink(String projectLink) {
		this.projectLink = projectLink;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public int getSelfVisible() {
		return selfVisible;
	}

	public void setSelfVisible(int selfVisible) {
		this.selfVisible = selfVisible;
	}

	public long getShareDate() {
		return shareDate;
	}

	public void setShareDate(long shareDate) {
		this.shareDate = shareDate;
	}

	public String getShareUser() {
		return shareUser;
	}

	public void setShareUser(String shareUser) {
		this.shareUser = shareUser;
	}

	public int getSuperChapterId() {
		return superChapterId;
	}

	public void setSuperChapterId(int superChapterId) {
		this.superChapterId = superChapterId;
	}

	public String getSuperChapterName() {
		return superChapterName;
	}

	public void setSuperChapterName(String superChapterName) {
		this.superChapterName = superChapterName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}

	public List<TagsBean> getTags() {
		return tags;
	}

	public void setTags(List<TagsBean> tags) {
		this.tags = tags;
	}

	public static class TagsBean {
		/**
		 * name : 公众号
		 * url : /wxarticle/list/408/1
		 */

		private String name;
		private String url;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
