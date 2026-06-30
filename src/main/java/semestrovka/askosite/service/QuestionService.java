package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.dto.QuestionDto;
import semestrovka.askosite.entity.Personage;
import semestrovka.askosite.entity.Question;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.PersonageNotFoundException;
import semestrovka.askosite.repository.PersonageRepository;
import semestrovka.askosite.repository.QuestionRepository;
import semestrovka.askosite.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final PersonageRepository personageRepository;

    @Transactional
    public void create(User user, String personageUniqueName, String text) {
        Personage personage = personageRepository.findByUniqueName(personageUniqueName)
                                                .orElseThrow(PersonageNotFoundException::new);
        questionRepository.save(
                Question.builder()
                        .user(user)
                        .personage(personage)
                        .text(text)
                        .build()
        );
    }
    public Slice<QuestionDto> getByPersonage(String personageUniqueName, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return convertToDto(questionRepository.findByPersonageUniqueName(personageUniqueName, pageable));
    }

    private Slice<QuestionDto> convertToDto(Slice<Question> questions){
        return questions.map(
                question -> QuestionDto.builder()
                                                .nickname(question.getUser().getNickname())
                                                .text(question.getText())
                                                .build()
        );
    }
}
