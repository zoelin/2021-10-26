package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;
import tw.com.fcb.mimosa.tracing.Traced;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Traced
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	static List<User> users = new ArrayList<User>();

	static int idno=1;


	final UserDtoMapper mapper;
	final UserService service;

	@PostMapping("/names")
	APIResponse<User> getByName(@RequestBody APIRequest<String> user) {
		return APIResponse.success(service.getByName(user.getBody()));
	}


	@GetMapping
	APIResponse<List<UserDto>>  getUser() {

//		return User.builder()
//				.age(18)
//				.name("Zoe")
//				.build();

		List<UserDto> dtos = new ArrayList<UserDto>();
		users.stream().map(mapper::from).forEach(dtos::add);


//		for (User user : users) {
////			UserDto dto = UserDto.builder().name(user.getName()).build();
//			UserDto dto = mapper.from(user);
//			dtos.add(dto);
//		}

		List<UserDto> java8dto = users.stream().map(mapper::from)
				.collect(Collectors.toList());

		return APIResponse.success(service.getUsers().stream()
				.map(mapper::from)
				.collect(Collectors.toList()));

	}

	@PostMapping
	APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto>  user) {

//		User user = mapper.to(userReq);
//		dto.setId(idno++);
//		User req = mapper.toRes(dto);
//		users.add(req);

		return APIResponse.success(service.createUser(user.getBody()))
				.map(User::getId);

	}


	//github repository name 2021-10-26 =>public
	//my homework
	//modify user
	@PutMapping
	long modifyUser(@RequestBody User user) {

		for(User us:users) {
			if(us.getId()==user.getId()) {
				us.setName(user.getName());
				us.setAge(user.getAge());
			}
		}
		return user.getId();
	}

	//delete user
	@DeleteMapping
	int deleteUser(@RequestParam int id) {
		for(int i= 0 ; i<users.size();i++ ) {
			if(users.get(i).getId()==id)
				users.remove(i);
		}
		return id;
	}



	/**
	 * teacher
	 *
	 */
	@PutMapping("/{id}")
	void replaceUser(@Min(0) @PathVariable("id") Long id, @Validated @RequestBody ReplaceUserDto user) {
//		for(User u : users) {
//			if(u.getId() == id) {
//				u.setName(user.getName());
//				u.setAge(user.getAge());
//			}
//		}

		service.replaceUser(id, user);

	}

	@DeleteMapping("/{id}")
	void deleteUser (@Min(0) @PathVariable("id") Long id) {
//		users.removeIf(user -> user.getId() == id);
		service.delete(id);

	}

}
