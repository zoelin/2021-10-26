package tw.com.fcb.mimosa.examples.gettingstarted.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

import javax.validation.constraints.Min;
import java.util.List;

@FeignClient(name = "users" ,url ="http://localhost:8081/users")
public interface UserClient {

  @GetMapping
  APIResponse<List<UserDto>> getUser();

  @PostMapping
  APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> user) ;

  @PutMapping("/{id}")
  void replaceUser(@Min(0) @PathVariable("id") Long id, @Validated @RequestBody ReplaceUserDto user) ;

  @DeleteMapping("/{id}")
  void deleteUser (@Min(0) @PathVariable("id") Long id) ;

  }
