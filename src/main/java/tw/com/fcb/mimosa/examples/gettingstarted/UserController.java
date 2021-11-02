package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	static List<User> users = new ArrayList<User>();
	
	static int idno=1;
	static List<UserResponse> userresponse = new ArrayList<UserResponse>();

	@Autowired
	UserDtoMapper mapper;

	@GetMapping
	List<UserDto> getUser() {

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

		return java8dto;

	}

	@PostMapping
	void createUser(@RequestBody UserRequest userReq) {
		
		UserDto dto = mapper.to(userReq);
		dto.setId(idno++);
		User req = mapper.toRes(dto);
		users.add(req);

	}
	
	
	//github repository name 2021-10-26 =>public
	
	//modify user
	@PutMapping
	void modifyUser(@RequestBody User user) {
		for(User us:users) {
			if(us.getId()==user.getId()) {
				us.setName(user.getName());
				us.setAge(user.getAge());
			}
		}
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

}
