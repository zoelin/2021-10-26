package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
	Long id;
	String userName;
	int age; 
	
}
