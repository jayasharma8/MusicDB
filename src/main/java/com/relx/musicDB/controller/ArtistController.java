package com.relx.musicDB.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Artist;
import com.relx.musicDB.service.ArtistService;

@RestController
@RequestMapping("/api/v1/auth")
public class ArtistController {


	@Autowired
	ArtistService artistAlbumService;
	

	public ArtistController(ArtistService artistAlbumService) {
		this.artistAlbumService = artistAlbumService;
	}

	@RequestMapping(value = "/artists", method = RequestMethod.POST)
	public ResponseEntity<Void> RegisterArtist(@RequestBody Artist artist, HttpServletRequest request) {
		try {
			if (artistAlbumService.saveArtistDetails(artist)) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} catch (RecordAlreadyExistsException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/getAllArtist", method = RequestMethod.GET)
	  public ResponseEntity<List<Artist>> getArtist(@RequestParam(defaultValue = "0") Integer pageNo, 
              @RequestParam(defaultValue = "10") Integer pageSize,
              @RequestParam(defaultValue = "artistId") String sortBy, HttpServletRequest request) throws  RecordNotFoundException { 
	  List<Artist> u=null; 
	  HttpHeaders responseHeaders = new HttpHeaders(); 
	  u = artistAlbumService.findAllArtist(pageNo, pageSize, sortBy);
	  return ResponseEntity.status(HttpStatus.OK).body(u); 
}
	  @RequestMapping(value = "/artists/{id}", method = RequestMethod.PUT)
	  public ResponseEntity<Void> updateArtist(@PathVariable("id") String id, @RequestBody Artist artist,HttpServletRequest request)
	  { Artist u = null; 
	  try {
		  u = artistAlbumService.updateArtistDetails(artist.getArtistId(), artist); }
	  catch(RecordNotFoundException e) { // TODO Auto-generated catch block return
	  System.out.println("NOT_FOUND"); } 
	  if(u==null){ 
	  return new  ResponseEntity<Void>(HttpStatus.CONFLICT); } 
	  return new ResponseEntity<Void>(HttpStatus.OK); }
	 
	
}
