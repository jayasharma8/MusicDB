package com.relx.musicDB.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error message from discogs.
 */
@Getter
@AllArgsConstructor
public class ErrorMessage {

    private String message;
}
