package com.relx.musicDB.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.relx.musicDB.model.Album;


/*
* This class is implementing the JpaRepository interface for User.
* Annotate this class with @Repository annotation
* */

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {

}
