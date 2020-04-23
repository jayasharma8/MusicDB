package com.relx.musicDB.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

/**
 * POJO for the Artist response.
 */
@Data
@Builder
@NoArgsConstructor
@ApiModel(description = "Artist information including albums and description")
public class ArtistDiscogs extends ResourceSupport {

    @JsonCreator
    public ArtistDiscogs(@JsonProperty("artistId") String artistId,
                  @JsonProperty("description") String description,
                  @JsonProperty("albums") List<AlbumDiscogs> albums) {
        this.artistId = artistId;
        this.description = description;
        this.albums = albums;
    }

    public ArtistDiscogs() {
		// TODO Auto-generated constructor stub
	}

	@ApiModelProperty(value = "Artist Id, which is a mbid of the artist.")
    private String artistId;

    @ApiModelProperty(value = "Artist description fetch from Discogs API")
    private String description;

    @ApiModelProperty(value = "All of artist's albums")
    private List<AlbumDiscogs> albums;

}
