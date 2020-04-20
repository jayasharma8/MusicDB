package com.relx.musicDB.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Album;
import com.relx.musicDB.repository.AlbumRepository;;

@Service
public class AlbumServiceImpl implements AlbumService {
 
    @Autowired
	AlbumRepository albumRepository;

      public AlbumServiceImpl(AlbumRepository albumRepository){
            this.albumRepository = albumRepository;
      }
	
	  @Override public boolean saveAlbumDetails(Album album1) throws
	  RecordAlreadyExistsException {
	  
	  Optional<Album> album = albumRepository.findById(album1.getAlbumId());
	  if(!album.isPresent()|| album.equals(null)){
	  albumRepository.save(album1);
	  return true;
	  }
	  else{ throw new RecordAlreadyExistsException("user Already exist");
	  }
	  }

	  public Album updateAlbumDetails(Album album,String id) throws RecordNotFoundException 
	  { 
	   Optional<Album> updateAlbum = albumRepository.findById(id);
	  if(updateAlbum.isPresent() && updateAlbum.get() != null) {
		  albumRepository.save(album); } 
	  else { 
		  throw new RecordNotFoundException("Album Not Found Exception"); } 
	  	  return album; 
	  }
	  
	  public Optional<Album> getAlbumById(String id) throws RecordNotFoundException { 
	   Optional<Album> findAlbum = albumRepository.findById(id);
	  if(findAlbum.isPresent() && findAlbum.get() != null) {
		 System.out.println(findAlbum); } 
	  else { 
		  throw new RecordNotFoundException("Album Not Found Exception"); } 
	  	  return findAlbum; 
	  }
}





