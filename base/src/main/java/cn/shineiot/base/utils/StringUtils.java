package cn.shineiot.base.utils;


/**
 * @author GF63
 */
public class StringUtils {

	public static String delHtmlTags(String htmlStr) {
		//定义script的正则表达式，去除js可以防止注入
		String scriptRegex="<script[^>]*?>[\\s\\S]*?<\\/script>";
		//定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
		String styleRegex="<style[^>]*?>[\\s\\S]*?<\\/style>";
		//定义HTML标签的正则表达式，去除标签，只提取文字内容
		String htmlRegex="<[^>]+>";
		//定义空格,回车,换行符,制表符
		String spaceRegex = "\\s*|\t|\r|\n";

		String tagRegex = "&mdash;";

		// 过滤script标签
		htmlStr = htmlStr.replaceAll(scriptRegex, "");
		// 过滤style标签
		htmlStr = htmlStr.replaceAll(styleRegex, "");
		// 过滤html标签
		htmlStr = htmlStr.replaceAll(htmlRegex, "");
		// 过滤空格等
		htmlStr = htmlStr.replaceAll(spaceRegex, "");
		//下划线
		htmlStr = htmlStr.replaceAll(tagRegex, "-");

		htmlStr = htmlStr.replace("&ldquo;", "“");
		htmlStr = htmlStr.replace("&rdquo;", "”");
		htmlStr = htmlStr.replace("&nbsp;", " ");
		htmlStr = htmlStr.replace("&", "&amp;");
		htmlStr = htmlStr.replace("&#39;", "'");
		htmlStr = htmlStr.replace("&rsquo;", "’");
		htmlStr = htmlStr.replace("&mdash;", "—");
		htmlStr = htmlStr.replace("&ndash;", "–");

//		htmlStr = StringEscapeUtils.unescapeHtml3(htmlStr);

		return htmlStr.trim(); // 返回文本字符串
	}
}
