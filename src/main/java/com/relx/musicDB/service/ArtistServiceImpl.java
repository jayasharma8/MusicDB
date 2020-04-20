package com.relx.musicDB.service;

import java.util.List;
import java.util.Optional;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Artist;
import com.relx.musicDB.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
 
      ArtistRepository artistRepository;

      @Autowired
      public ArtistServiceImpl(ArtistRepository artistRepository){

            this.artistRepository = artistRepository;

      }
      
    @Override
    public boolean saveArtistDetails(Artist artist1) throws RecordAlreadyExistsException {

      Optional<Artist> artist = artistRepository.findById(artist1.getArtistId());

      if(!artist.isPresent()|| artist.equals(null)){

    	  artistRepository.save(artist1);

       return true;

      }else{
            throw new RecordAlreadyExistsException("user Already exist");

      }
    }
    
    public Artist getArtistById(String userId) throws RecordNotFoundException {
		Artist u = artistRepository.findById(userId).get();
		if(u!=null){
		return u;
		}
		else{
			throw new RecordNotFoundException("");
		}
	}
    
    public Artist updateArtistDetails(String artistId,Artist artist) throws RecordNotFoundException {
		Optional<Artist> updateArtist = artistRepository.findById(artistId);
		
		if(updateArtist.isPresent() && updateArtist.get() != null) {
			artistRepository.save(artist);
		} else {
			throw new RecordNotFoundException("artist Not Found Exception");
		}		
		return artist;
	}

	@Override
	public List<Artist> findAllArtist(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Artist> pagedResult = artistRepository.findAll(paging);
		 return  artistRepository.findAll();		
	}


}