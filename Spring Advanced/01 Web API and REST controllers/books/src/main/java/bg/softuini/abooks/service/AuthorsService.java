package bg.softuini.abooks.service;

import bg.softuini.abooks.model.entity.AuthorEntity;

public interface AuthorsService {
    void save(AuthorEntity author);

    boolean saveAuthorInDB(AuthorEntity author);

    void initialiseAuthor(String authorName, String[] books);
}

