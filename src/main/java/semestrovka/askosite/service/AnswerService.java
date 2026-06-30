package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import semestrovka.askosite.dto.AnswerCreateRequest;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.entity.Answer;
import semestrovka.askosite.entity.Personage;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.PersonageNotFoundException;
import semestrovka.askosite.repository.AnswerRepository;
import semestrovka.askosite.repository.PersonageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final FileStorageService fileStorageService;
    private final PersonageRepository personageRepository;

    @Transactional
    public void create(AnswerCreateRequest request, String personageUniqueName){
        List<String> images = new ArrayList<>();
        for (MultipartFile file : request.getImages()){
            images.add(fileStorageService.saveImage(file, FormType.ANSWER));
        }
        Personage personage = personageRepository.findByUniqueName(personageUniqueName).orElseThrow(PersonageNotFoundException::new);

        answerRepository.save(
                Answer.builder()
                        .text(request.getText())
                        .isPosted(true) // пока отложки нет
                        .personage(personage)
                        .imageUrls(images)
                        .build()
        );
        log.info("answer created");
    }

    public Slice<AnswerDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return answerRepository.findByIsPostedTrue(pageable).map(
                answer -> AnswerDto.builder()
                        .personageUniqueName(answer.getPersonage().getUniqueName())
                        .text(answer.getText())
                        .images(answer.getImageUrls())
                        .postTime(answer.getPostTime())
                        .build()
                );
    }

    public Slice<AnswerDto> getByPersonage(int page, int size, String personageUniqueName){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return convertToDto(answerRepository.findByPersonageUniqueNameAndIsPostedTrue(personageUniqueName, pageable));
    }

    public Slice<AnswerDto> getByAsk(int page, int size, String uniqueName){
        Pageable pageable = PageRequest.of(page, size, Sort.by("postTime").descending());
        return convertToDto(answerRepository.findByPersonage_Ask_UniqueNameAndIsPostedTrue(uniqueName, pageable));
    }

    public void delete(UUID answerId){
        answerRepository.safeDelete(answerId);
    }

    private Slice<AnswerDto> convertToDto(Slice<Answer> answers){
        return answers.map(
                answer -> AnswerDto.builder()
                        .personageUniqueName(answer.getPersonage().getUniqueName())
                        .text(answer.getText())
                        .images(answer.getImageUrls())
                        .postTime(answer.getPostTime())
                        .id(answer.getId())
                        .build()
        );
    }
}
