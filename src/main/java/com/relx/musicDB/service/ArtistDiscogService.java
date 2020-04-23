package com.relx.musicDB.service;

import com.relx.musicDB.model.*;
import reactor.core.publisher.Mono;

/**
 * Artist Service class to provide artist related operations
 */
public interface ArtistDiscogService {

    /**
     * Get artist details from the artist's MBID.
     *
     * @param artistId MBID given as input of the API
     * @return {@link Mono<Artist>}
     */
    Mono<ArtistDiscogs> getArtistDetails(String artistId);
}
