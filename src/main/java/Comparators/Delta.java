package Comparators;

import javax.swing.text.Document;
import java.util.Comparator;
import java.util.List;

public class Delta implements Comparator<Document> {
    List<Document> authorProfile;
    Document queryDocument;

    public Delta(Document document, List<Document> authorProfile){
        this.authorProfile = authorProfile;
        this.queryDocument = document;
    }

    public int compare(Document o1, Document o2) {
        return 0;
    }
}
