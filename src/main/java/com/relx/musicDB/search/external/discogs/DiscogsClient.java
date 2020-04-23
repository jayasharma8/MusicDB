package com.relx.musicDB.search.external.discogs;

import com.relx.musicDB.model.*;
import reactor.core.publisher.Mono;

/**
 * Client class to call the Discogs API.
 */
public interface DiscogsClient {

    /**
     * It will fetch artist profile from Discogs API depending upon discog ID of the Artist.
     *
     * @param discogId of the artist
     * @return {@link Mono<ArtistProfile>} details
     */
    Mono<ArtistProfile> getArtistProfile(String discogId);
}
