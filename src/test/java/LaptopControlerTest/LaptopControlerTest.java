package LaptopControlerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.opspbejercicio.entities.Laptop;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LaptopControlerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void testCreate(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json ="""
                {
                
                "marca": "Acer",
                "modelo": "RCA",
                "precio": 40.99
                
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST , request, Laptop.class);
        
        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Laptop creada desde Spring test", result.getMarca());

    }

    @Test
    void testFindAll() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());

    }

    @Test
    void testFindOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }





}
