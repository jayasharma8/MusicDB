package com.relx.musicDB.service;

import java.util.List;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Artist;

public interface ArtistService {

    	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */


    boolean saveArtistDetails(Artist artist) throws RecordAlreadyExistsException;
    Artist updateArtistDetails(String artistId,Artist artist) throws RecordNotFoundException;

    Artist getArtistById(String artistId) throws RecordNotFoundException;
    List<Artist> findAllArtist(Integer pageNo, Integer pageSize, String sortBy);

}
