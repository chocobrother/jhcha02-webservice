package com.juhwan02.springboot.web.domain.posts;


import com.juhwan02.springboot.domain.posts.Posts;
import com.juhwan02.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.PushBuilder;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";

        String content = "테스트 본문";

        postsRepository.save(Posts.builder().title(title).content(content).author("jhcha02").build());


        //when
        List<Posts> postsList = postsRepository.findAll();

        System.out.println(postsList);

        Posts posts = postsList.get(0);

        //then
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록 () {

        LocalDateTime now = LocalDateTime.of(2021,6,4,0,0,0);

        postsRepository.save(Posts.builder().title("test").content("conteotn").author("author").build());

        //when

        List<Posts> postsList = postsRepository.findAll();

        // then

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>> create date = "+ posts.getCreatedDate()+", modified date = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
