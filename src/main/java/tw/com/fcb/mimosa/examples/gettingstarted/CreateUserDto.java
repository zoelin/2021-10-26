package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateUserDto {	
	@NotBlank
	String name;
	@Min(0)
	int age; 

}
