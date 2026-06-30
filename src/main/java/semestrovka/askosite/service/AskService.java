package semestrovka.askosite.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semestrovka.askosite.dto.AskCreateRequest;
import semestrovka.askosite.dto.AskDto;
import semestrovka.askosite.entity.Ask;
import semestrovka.askosite.entity.AskAdmin;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.AskAlreadyExistsException;
import semestrovka.askosite.exceptions.AskNotFoundException;
import semestrovka.askosite.repository.AskAdminRepository;
import semestrovka.askosite.repository.AskRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class AskService {
    private final AskRepository askRepository;
    private final AskAdminRepository askAdminRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public void create(AskCreateRequest request, User user){
        if (askRepository.existsByUniqueName(request.getUniqueName())){
            throw new AskAlreadyExistsException();
        }
        Ask ask = Ask.builder()
                    .name(request.getName())
                    .iconUrl(fileStorageService.saveImage(request.getIcon(), FormType.ASK))
                    .uniqueName(request.getUniqueName())
                    .build();
        askRepository.save(ask);
        askAdminRepository.save(
                AskAdmin.builder()
                        .user(user)
                        .ask(ask)
                        .infoPostsEnabled(true)
                        .build()
        );
    }

    public AskDto getByUniqueName(String uniqueName, User user){
        Ask ask = askRepository.findByUniqueName(uniqueName).orElseThrow(AskNotFoundException::new);
        return AskDto.builder()
                .name(ask.getName())
                .uniqueName(ask.getUniqueName())
                .icon(ask.getIconUrl())
                .isAdmin(askAdminRepository.existsByAskAndUser(ask, user))
                .build();
    }
    public Slice<AskDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return convertToDto(askRepository.findAll(pageable));
    }
    public void checkAdmin(User user, String askUniqueName){
        Ask ask = askRepository.findByUniqueName(askUniqueName).orElseThrow(AskNotFoundException::new);
        if (!askAdminRepository.existsByAskAndUser(ask, user)){
            throw new AccessDeniedException("Вы не можете удалить ответ, так как не являетесь администратором аска");
        }
    }
    private Slice<AskDto> convertToDto(Slice<Ask> asks){
        return asks.map(
                ask -> AskDto.builder()
                        .name(ask.getName())
                        .uniqueName(ask.getUniqueName())
                        .icon(ask.getIconUrl())
                        .isAdmin(false)
                        .build()
        );
    }
}
