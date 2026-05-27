package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.dto.AnswerDto;
import semestrovka.askosite.dto.AskCreateRequest;
import semestrovka.askosite.dto.AskDto;
import semestrovka.askosite.entity.Ask;
import semestrovka.askosite.entity.AskAdmin;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.AskAlreadyExistsException;
import semestrovka.askosite.exceptions.AskNotFoundException;
import semestrovka.askosite.repository.AskAdminRepository;
import semestrovka.askosite.repository.AskRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AskService {
    private final AskRepository askRepository;
    private final AskAdminRepository askAdminRepository;
    private final FileStorageService fileStorageService;
    private final AnswerService answerService;

    @Transactional
    public void create(AskCreateRequest request, User user){
        if (askRepository.existsByUniqueName(request.getUniqueName())){
            throw new AskAlreadyExistsException();
        }
        Ask ask = Ask.builder()
                    .name(request.getName())
                    .iconUrl(fileStorageService.saveImage(request.getIcon()))
                    .uniqueName(request.getUniqueName())
                    .build();
        askRepository.save(ask);
        askAdminRepository.save(
                AskAdmin.builder()
                        .ask(ask)
                        .user(user)
                        .infoPostsEnabled(true)
                        .build()
        );
    }

    public AskDto getByUniqueName(String uniqueName, int page, int size){
        Ask ask = askRepository.findByUniqueName(uniqueName).orElseThrow(AskNotFoundException::new);
        return new AskDto(
                ask.getName(),
                ask.getUniqueName(),
                ask.getIconUrl(),
                answerService.getByAsk(page, size, uniqueName).map(
                        answer -> new AnswerDto(
                                answer.getPersonage().getUniqueName(),
                                answer.getText(),
                                answer.getPostTime(),
                                answer.getImageUrls()
                        )
                )
        );
    }
}
