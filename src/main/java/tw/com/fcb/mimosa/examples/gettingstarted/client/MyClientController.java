package tw.com.fcb.mimosa.examples.gettingstarted.client;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/client/users")
@RequiredArgsConstructor
public class MyClientController {

    //final UserRestTemplate template;
    final UserClient template;

  @GetMapping
  APIResponse<List<UserDto>> getUser() {
    return template.getUser();
  }

  @PostMapping
  APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> user) {
    return template.createUser(user);
  }

  @PutMapping("/{id}")
  void replaceUser(@Min(0) @PathVariable("id") Long id ,@Validated @RequestBody ReplaceUserDto user) {
    template.replaceUser(id,user);
  }

  @DeleteMapping("/{id}")
  void deleteUser(@Min(0) @PathVariable("id") Long id) {
  template.deleteUser(id);
  }


  }
