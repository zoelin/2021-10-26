package tw.com.fcb.mimosa.examples.gettingstarted;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.domain.t9n.Term;
import tw.com.fcb.mimosa.domain.t9n.Translated;
import tw.com.fcb.mimosa.domain.t9n.TranslationService;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class MyErrorTranslation implements TranslationService {

	final ErrorCodeRepository repository;

	@Override
	public Optional<Translated> translate(@NonNull Term term) {
    return repository.findByCode(term.getCode()).map(Translated.class::cast);

//		if (term.getCode().equals("ERR1")) {
//
//			return Optional.of(
//					new MyTranslation(term.getCategory(), term.getCode(), "查無姓名"));
//		}
//		return Optional.empty();
	}

	//20211109 homework DB select errorName


	@Getter
	@RequiredArgsConstructor
	static class MyTranslation implements Translated {
		final String category;
		final String code;
		final String translation;
	}

}
