package com.concretepage;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.concretepage.wsdl.AddArticleRequest;
import com.concretepage.wsdl.AddArticleResponse;
import com.concretepage.wsdl.ArticleInfo;
import com.concretepage.wsdl.DeleteArticleRequest;
import com.concretepage.wsdl.DeleteArticleResponse;
import com.concretepage.wsdl.GetAllArticlesRequest;
import com.concretepage.wsdl.GetAllArticlesResponse;
import com.concretepage.wsdl.GetArticleByIdRequest;
import com.concretepage.wsdl.GetArticleByIdResponse;
import com.concretepage.wsdl.UpdateArticleRequest;
import com.concretepage.wsdl.UpdateArticleResponse;

public class ArticleClient extends WebServiceGatewaySupport  {
	public GetArticleByIdResponse getArticleById(long articleId) {
		GetArticleByIdRequest request = new GetArticleByIdRequest();
		request.setArticleId(articleId);
		GetArticleByIdResponse response = (GetArticleByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getArticleByIdRequest"));
		return response;
	}
	public GetAllArticlesResponse getAllArticles() {
		GetAllArticlesRequest request = new GetAllArticlesRequest();
		GetAllArticlesResponse response = (GetAllArticlesResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getAllArticlesRequest"));
     	return response;
	}	
	public AddArticleResponse addArticle(String title, String category) {
		AddArticleRequest request = new AddArticleRequest();
		request.setTitle(title);
		request.setCategory(category);
		AddArticleResponse response = (AddArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/addArticleRequest"));
     	return response;
	}	
	public UpdateArticleResponse updateArticle(ArticleInfo articleInfo) {
		UpdateArticleRequest request = new UpdateArticleRequest();
		request.setArticleInfo(articleInfo);
		UpdateArticleResponse response = (UpdateArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/updateArticleRequest"));
     	return response;
	}	
	public DeleteArticleResponse deleteArticle(long articleId) {
		DeleteArticleRequest request = new DeleteArticleRequest();
		request.setArticleId(articleId);
		DeleteArticleResponse response = (DeleteArticleResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/deleteArticleRequest"));
     	return response;
	}		
}
