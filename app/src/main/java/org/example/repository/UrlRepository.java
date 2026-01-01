package org.example.repository;

import org.example.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long>{

    @Query(value = "SELECT * FROM urls WHERE short_urls = :shortUrl",  nativeQuery = true)
    Url findByshortUrl(@Param("shortUrl") String shortUrl);

    boolean existsByShortUrl(String shortUrl);
    
}
