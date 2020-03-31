package fabrizio.teste.upvote;

import fabrizio.teste.upvote.controller.WebController;
import fabrizio.teste.upvote.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpvoteApplicationTests {

    @Autowired
    WebController webController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
        assertThat(webController).isNotNull();
    }

    @Test
    void canAddPost() {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/post?user=teste&text=oi", null, Post.class).getUser().equalsIgnoreCase("teste")).isTrue();
    }

    @Test
    void errorGettingID(){
        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/post?id=999",null).getStatusCodeValue()==404).isTrue();
    }
}
