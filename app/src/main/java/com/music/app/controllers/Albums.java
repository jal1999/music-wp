package com.music.app.controllers;

import com.music.app.models.Album;
import com.music.app.repos.AlbumRepository;
import com.music.app.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class Albums {
    private final AlbumRepository albumRepository;

    public Albums(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAlbums() {
        List<Album> albums = albumRepository.findAll();
        return ResponseEntity
                .status(200)
                .body(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbum(@PathVariable(value = "id") String albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body("There exists no album with the given ID");
        return ResponseEntity
                .status(200)
                .body(Map.of("album", album));
    }

    @PostMapping("/")
   public ResponseEntity<?> createAlbum(@RequestBody Album album, @CookieValue("token") String token) {
        if (!new JwtUtil().verifyToken(token))
            return ResponseEntity
                    .status(401)
                    .body("Invalid token");
        Album newAlbum = new Album(album.getAlbumName(), album.getImage(), album.getLinks());
        albumRepository.save(newAlbum);
        return ResponseEntity
                .status(201)
                .body(newAlbum);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editAlbum(@PathVariable(value = "id") String albumId, @RequestBody Album editedAlbum, @CookieValue("token") String token) {
        if (!new JwtUtil().verifyToken(token))
            return ResponseEntity
                    .status(401)
                    .body("Invalid token");
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body("There does not exist an album with the given ID");
        album.get().setAlbumName(editedAlbum.getAlbumName());
        album.get().setImage(editedAlbum.getImage());
        album.get().setLinks(editedAlbum.getLinks());
        albumRepository.save(album.get());
        return ResponseEntity
                .status(200)
                .body(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlbum(@PathVariable(value = "id") String albumId, @CookieValue("token") String token) {
        if (!new JwtUtil().verifyToken(token))
            return ResponseEntity
                    .status(401)
                    .body("Invalid token");
        Optional<Album> album = albumRepository.findById(albumId);
        if (album.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body("There does not exist an album with the given ID");
        albumRepository.deleteById(albumId);
        return ResponseEntity
                .status(200)
                .body(album.get());
    }
}