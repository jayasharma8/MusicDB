package com.relx.musicDB.test.service;

  
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import  org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.relx.musicDB.exception.RecordAlreadyExistsException;
import com.relx.musicDB.exception.RecordNotFoundException;
import com.relx.musicDB.model.Album;
import com.relx.musicDB.repository.AlbumRepository;
import com.relx.musicDB.service.AlbumServiceImpl;;
  
  public class UserAuthenticationServiceTest {
  
  @Mock private AlbumRepository autheticationRepository;
  
  private Album user;
  
  @InjectMocks private AlbumServiceImpl authenticationService;
  
  Optional<Album> optional;
  
  
  @Before public void setUp() throws Exception {
  MockitoAnnotations.initMocks(this); 
  user = new Album();
  user.setAlbumId("1"); 
  user.setAlbumName("Sample");
  user.setArtistId("3"); 
  user.setGenres("Admin");
  user.setYearOfRelease("2000"); 
  optional =  Optional.of(user); }
  
  @Test public void testSaveUserSuccess() throws RecordAlreadyExistsException {
  
  Mockito.when(autheticationRepository.save(user)).thenReturn(user); boolean
  flag = authenticationService.saveAlbumDetails(user);
  Assert.assertEquals("Cannot Add Album", true, flag);
  
  }
  
  
  @Test(expected = RecordAlreadyExistsException.class) public void
  testSaveUserFailure() throws RecordAlreadyExistsException {
  
  Mockito.when(autheticationRepository.findById("3")).thenReturn(optional
  ); Mockito.when(autheticationRepository.save(user)).thenReturn(user); boolean
  flag = authenticationService.saveAlbumDetails(user);
  Assert.assertEquals("Cannot Add Album", true, flag);
  
  }
  
  } 