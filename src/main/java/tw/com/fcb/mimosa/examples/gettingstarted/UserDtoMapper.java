package tw.com.fcb.mimosa.examples.gettingstarted;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserDtoMapper {

	@Mapping(source = "name" , target = "userName")
	UserDto from(User user);
	
	
	@Mapping(source = "name" , target = "userName")
	UserDto to(UserRequest userReq);
	
	@Mapping(source = "userName" , target = "name")
	User toRes(UserDto userdto);

}
