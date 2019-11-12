package com.xDish.PaperTrail.Services;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorIdSearch {

    private int id;
    private String name;
    private String authorImageURL;
    private List<Book> books = new ArrayList<Book>();

    public AuthorIdSearch(int variant, String query) throws Exception {
        elementBuilder(variant, query);
    }

    private void parseAuthorInfo(Element authorInfo) {
        this.name = authorInfo.getElementsByTagName("name").item(0).getTextContent();
        this.id = Integer.parseInt(authorInfo.getElementsByTagName("id").item(0).getTextContent());
        this.authorImageURL = authorInfo.getElementsByTagName("image_url").item(0).getTextContent();
    }

    private void parseBooks(NodeList books) {
        for (int i = 0 ; i < books.getLength(); i++){
            Element book =(Element) books.item(i);
            Book currentBook = new Book();
            currentBook.setTitle(book.getElementsByTagName("title").item(0).getTextContent());
            currentBook.setBookImageURL(book.getElementsByTagName("image_url").item(0).getTextContent());
            currentBook.setAverageRating(Double.parseDouble(book.getElementsByTagName("average_rating").item(0).getTextContent()));
            currentBook.setIsbn(book.getElementsByTagName("isbn").item(0).getTextContent());
            currentBook.setPages(Integer.parseInt(book.getElementsByTagName("num_pages").item(0).getTextContent()));
            this.books.add(currentBook);
        }
    }

    private void elementBuilder(int variant, String query) throws Exception {
        Document document = BuildXmlDocument.XmlDocument(ApiUrlBuilder.buildUrl(variant, query));
        Node authorNode = document.getElementsByTagName("author").item(0);
        Element authorElement = (Element) authorNode.getChildNodes();
        parseAuthorInfo(authorElement);
        NodeList books = authorElement.getElementsByTagName("book");
        parseBooks(books);
    }


}



