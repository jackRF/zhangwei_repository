package com.jack.wechat.message.send;

import java.util.List;

import com.jack.wechat.entity.Article;
import com.jack.wechat.message.MessageType;
import com.jack.wechat.message.recieve.RecieveMessage;

public class SendArticleMessage extends SendMessage {
	private List<Article> articles;	
	public SendArticleMessage(RecieveMessage recieveMessage) {
		super(recieveMessage);
		this.msgType=MessageType.TYPE_NEWS;
	}
	
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	protected String toMessageBody() {
		StringBuilder sb=new StringBuilder();
		sb.append("<ArticleCount>"+this.articles.size()+"</ArticleCount>");
		sb.append("<Articles>");	
		for(Article article:articles){
			sb.append("<item>");
			sb.append("<Title><![CDATA["+article.getTitle()+"]]></Title>");
			sb.append("<Description><![CDATA["+article.getDescription()+"]]></Description>");
			sb.append("<PicUrl><![CDATA["+article.getPicUrl()+"]]></PicUrl>");
			sb.append("<Url><![CDATA["+article.getUrl()+"]]></Url>");					
			sb.append("</item>");
		}
		sb.append("</Articles>");
		return sb.toString();
	}

}
