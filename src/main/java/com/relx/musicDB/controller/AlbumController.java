package com.relx.musicDB.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Album;
import com.relx.musicDB.service.AlbumService;


@RestController
@RequestMapping("/artists")
public class AlbumController {

		@Autowired
	AlbumService albumService;
	

	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@RequestMapping(value = "/{artistId}/albums", method = RequestMethod.POST)
	public ResponseEntity<Void> RegisterAlbum(@RequestBody Album album,@PathVariable("artistId") String artistId, HttpServletRequest request) {
		try {
				album.artistId=artistId;
			if (albumService.saveAlbumDetails(album)) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} catch (RecordAlreadyExistsException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/{artistId}/albums", method = RequestMethod.GET)
	  public ResponseEntity<Optional<Album>> getAlbum(@PathVariable("artistId") String artistId, HttpServletRequest request) throws  RecordNotFoundException { 
	  Optional<Album> u=null; 
	  HttpHeaders responseHeaders = new HttpHeaders(); 
	  u = albumService.getAlbumById(artistId);
	  return ResponseEntity.status(HttpStatus.OK).body(u); 
}
	  //
	  @RequestMapping(value = "/{artistId}/albums/{albumId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateAlbum(@PathVariable("albumId") String albumId, @RequestBody Album album,HttpServletRequest request) { 
		  Album u = null; 
		  try {u = albumService.updateAlbumDetails(album,albumId); 
		}
	  catch(RecordNotFoundException e) { 
	  System.out.println("NOT_FOUND"); 
	  } 
		  if(u==null){
	 return new ResponseEntity<Void>(HttpStatus.CONFLICT); } return new
	  ResponseEntity<Void>(HttpStatus.OK); 
	}
	 
	
}
