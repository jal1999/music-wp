package com.music.app.repos;

import com.music.app.models.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
    Album findByAlbumName(String name);

    Album findByImage(String image);
}
