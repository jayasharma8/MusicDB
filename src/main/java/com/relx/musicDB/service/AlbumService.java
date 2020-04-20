package com.relx.musicDB.service;

import java.util.Optional;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Album;

public interface AlbumService {

    	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */
    boolean saveAlbumDetails(Album arlbumtist) throws RecordAlreadyExistsException;
    Album updateAlbumDetails(Album album,String albumId) throws RecordNotFoundException;
    Optional<Album> getAlbumById(String artistId) throws RecordNotFoundException;
}
