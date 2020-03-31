package fabrizio.teste.upvote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String user;
    @Getter @Setter
    private Date date;
    @Getter@Setter
    private String text;
    @Getter@Setter
    private int upvotes;

    public Post() {
    }
}
