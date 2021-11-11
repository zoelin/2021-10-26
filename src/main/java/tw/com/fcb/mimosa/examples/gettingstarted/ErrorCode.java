package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Translated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table
public class ErrorCode  implements Translated {


	@Id
	@GeneratedValue
	Long id;
	String category;
	String code;
	String codeName;

  @Override
  public String getTranslation() {
    return codeName;
  }
}
