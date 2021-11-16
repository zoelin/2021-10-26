package tw.com.fcb.mimosa.examples.gettingstarted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.mimosa.domain.t9n.Translated;

import java.util.Optional;

@Repository
public interface ErrorCodeRepository extends JpaRepository<ErrorCode, Long> {

	Optional<Translated> findByCategoryAndCode (String category, String code);
}
