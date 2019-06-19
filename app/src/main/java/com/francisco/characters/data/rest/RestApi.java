package com.francisco.characters.data.rest;

import com.francisco.characters.data.domain.Answer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("characters")
    Observable<Answer> fetchCharacters(
            @Query("ts") String ts,
            @Query("apikey") String apiKey,
            @Query("hash") String hash,
            @Query("offset") long offset);

    @GET("characters/{characterId}/comics")
    Observable<Answer> fetchComics(
            @Path("characterId") long characterId,
            @Query("ts") String ts,
            @Query("apikey") String apiKey,
            @Query("hash") String hash);
}
