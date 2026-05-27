package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.entity.Answer;
import semestrovka.askosite.repository.AnswerRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Slice<AnswerDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return answerRepository.findByIsPostedTrue(pageable).map(answer -> new AnswerDto(
                answer.getPersonage().getUniqueName(),
                answer.getText(),
                answer.getPostTime(),
                answer.getImageUrls()
                ));
    }

    public Slice<Answer> getByPersonage(int page, int size, UUID personageId){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return answerRepository.findByPersonageId(personageId, pageable);
    }

    public Slice<Answer> getByAsk(int page, int size, String uniqueName){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return answerRepository.findByPersonage_Ask_UniqueNameAndIsPostedTrue(uniqueName, pageable);
    }

}
