package com.relx.musicDB.service;

import com.relx.musicDB.model.ArtistDiscogs;
import com.relx.musicDB.search.*;
import com.relx.musicDB.search.external.discogs.DiscogsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistDiscogServiceImpl implements ArtistDiscogService {

    private final DiscogsClient discogsClient;

    @Override
    public Mono<ArtistDiscogs> getArtistDetails(String mbid) {
        return Mono.just(new ArtistDiscogs())
                .flatMap(artist -> fetchAndReturnArtist(mbid, artist));
    }
    private Mono<ArtistDiscogs> fetchAndReturnArtist(String mbid,
            ArtistDiscogs artist) {
artist.setArtistId(mbid);
return musicBrainzClient.getArtist(mbid)
.flatMap(artistDetails -> fetchAndReturnArtistDetails(artistDetails, artist));
}
    private Mono<Artist> fetchAndReturnArtistDetails(ArtistDetails artistDetails,
                                                     ArtistDiscogs artist) {
        val monoAlbum = fetchAlbums(artistDetails, artist);
        val monoArtist = fetchArtistProfile(artistDetails, artist);
        return Mono.just(artist).delayUntil(artist1 -> monoAlbum)
                .delayUntil(artist1 -> monoArtist)
                .doOnSuccess(Mono::just);
    }

    private Mono<AlbumCover> fetchAlbums(ArtistDetails artistDetails, ArtistDiscogs artist) {
        final Mono<AlbumCover>[] fAlbumCoverMono = new Mono[]{Mono.empty()};
        if (artistDetails.getReleaseGroups() != null && !artistDetails.getReleaseGroups().isEmpty()) {
            val albums = new ArrayList<AlbumDiscogs>();
            artistDetails.getReleaseGroups().parallelStream()
                    .filter(isReleaseGroupAnAlbum())
                    .forEach(album -> {
                        val albumCoverMono = coverArtArchiveClient.getAlbumCover(album.getId());
                        albumCoverMono.subscribe(albumCover -> {
                            if (albumCover.getImages() != null && !albumCover.getImages().isEmpty()) {
                                val images = new ArrayList<String>();
                                albumCover.getImages().parallelStream()
                                        .forEach(image -> images.add(image.getImage()));
                                albums.add(Album.builder().albumId(album.getId())
                                        .title(album.getTitle()).images(images).build());
                            }

                        });
                        fAlbumCoverMono[0] = albumCoverMono;
                    });
            artist.setAlbums(albums);
        }
        return fAlbumCoverMono[0];
    }

    private Predicate<ReleaseGroup> isReleaseGroupAnAlbum() {
        return releaseGroup -> releaseGroup != null &&
                "album".equalsIgnoreCase(releaseGroup.getPrimaryType());
    }

    private Mono<ArtistProfile> fetchArtistProfile(ArtistDetails artistDetails, Artist artist) {
        if (!artistDetails.getRelations().isEmpty()) {
            val discogRelationOptional = artistDetails.getRelations().parallelStream()
                    .filter(isRelationADiscog())
                    .findFirst();
            if (discogRelationOptional.isPresent()) {
                val resources = discogRelationOptional.get().getUrl().getResource().split("/");
                val discogId = resources[resources.length - 1];
                val artistProfileMono = discogsClient.getArtistProfile(discogId);
                artistProfileMono.subscribe(artistProfile -> artist.setDescription(artistProfile.getProfile()));
                return artistProfileMono;
            }
        }
        return Mono.just(new ArtistProfile());
    }

    private Predicate<Relation> isRelationADiscog() {
        return relation -> relation != null && relation.getType() != null &&
                "discogs".equalsIgnoreCase(relation.getType());
    }
}
