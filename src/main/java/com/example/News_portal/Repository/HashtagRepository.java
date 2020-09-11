package com.example.News_portal.Repository;

import com.example.News_portal.Model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository  extends JpaRepository<Hashtag,Long> {
}
