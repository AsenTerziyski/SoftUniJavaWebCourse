package bg.softuni.cloidinaryDemo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String url;
    private String publicId;

    public PictureEntity() {
    }

    public Long getId() {
        return Id;
    }

    public PictureEntity setId(Long id) {
        Id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
