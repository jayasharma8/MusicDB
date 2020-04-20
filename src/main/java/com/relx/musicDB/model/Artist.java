package com.relx.musicDB.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Artist {
	    @Id
	    private String artistId;
	    private String artistName;
	public Artist() {
		// TODO Auto-generated constructor stub
	}
    
    public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

		
	public Artist(String artistId, String artistName) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
	}
	
			
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName+"]";
	}
}
