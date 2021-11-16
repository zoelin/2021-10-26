package tw.com.fcb.mimosa.examples.gettingstarted.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

import java.util.List;

@Service
public class UserRestTemplate {

  @Value("${server.port:8081}")
  int port;

  String url(){

    return "http://localhost:"+port +"/users";
  }

  RestTemplate buildRestTemplate(){
    return new RestTemplateBuilder()
      .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
      .build();
  }


  APIResponse<List<UserDto>> getUser() {
      return buildRestTemplate()
        .exchange(url(), HttpMethod.GET,null,
        new ParameterizedTypeReference<APIResponse<List<UserDto>>>() {
      }).getBody();
  }

  public APIResponse<Long> createUser(APIRequest<CreateUserDto> user) {
    return buildRestTemplate()
      .exchange(url(), HttpMethod.POST,new HttpEntity<>(user),
        new ParameterizedTypeReference<APIResponse<Long>>() {
        }).getBody();
  }

  public void replaceUser(Long id, ReplaceUserDto user) {
     buildRestTemplate().put(url()+"/" + id,user);
  }

  public void delete(Long id) {
    buildRestTemplate().delete(url()+"/" + id);
  }
}
