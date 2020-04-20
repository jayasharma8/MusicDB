package com.relx.musicDB.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Album {
	    @Id
	    private String albumId;
	    public String getAlbumId() {
			return albumId;
		}
		public void setAlbumId(String albumId) {
			this.albumId = albumId;
		}
		public String getAlbumName() {
			return albumName;
		}
		public void setAlbumName(String albumName) {
			this.albumName = albumName;
		}
		public String getArtistId() {
			return artistId;
		}
		public void setArtistId(String artistId) {
			this.artistId = artistId;
		}

		private String albumName;
	    public String artistId;
	    private String yearOfRelease;
		private String genres;

	    
	    public String getYearOfRelease() {
			return yearOfRelease;
		}
		public void setYearOfRelease(String yearOfRelease) {
			this.yearOfRelease = yearOfRelease;
		}
		public String getGenres() {
			return genres;
		}
		public void setGenres(String genres) {
			this.genres = genres;
		}
	    
	public Album() {
	}
	public Album(String albumId, String albumName, String artistId, String yearOfRelease, String genres) {
		super();
		this.albumId = albumId;
		this.albumName = albumName;
		this.artistId=artistId;
		this.yearOfRelease=yearOfRelease;
		this.genres=genres;
	}  
	
	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName+",artistId="+artistId+",yearOfRelease="+yearOfRelease+",genres"+genres+"]";
	}
}
