package semestrovka.askosite.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrovka.askosite.dto.PersonageCreateRequest;
import semestrovka.askosite.dto.PersonageDto;
import semestrovka.askosite.entity.Personage;
import semestrovka.askosite.entity.User;
import semestrovka.askosite.exceptions.AskNotFoundException;
import semestrovka.askosite.exceptions.PersonageNotFoundException;
import semestrovka.askosite.repository.AskRepository;
import semestrovka.askosite.repository.PersonageRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class PersonageService {
    private final PersonageRepository personageRepository;
    private final AskRepository askRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public void create(String askUniqueName, PersonageCreateRequest request, User user){
        String icon = fileStorageService.saveImage(request.getIcon(), FormType.PERSONAGE);
        personageRepository.save(
                Personage.builder()
                        .user(user)
                        .ask(askRepository.findByUniqueName(askUniqueName).orElseThrow(AskNotFoundException::new))
                        .uniqueName(request.getUniqueName())
                        .name(request.getName())
                        .isActive(true)
                        .info(request.getInfo())
                        .isOnProbation(false) // пока я не сделала опросы испытательного срока нет
                        .iconUrl(icon)
                        .build()
        );
    }

    public void checkPersonageOwner(String personageUniqueName, User user){
        if (!personageRepository.existsByUniqueNameAndUser(personageUniqueName, user)){
            throw new AccessDeniedException("Вы не являетесь автором персонажа " + personageUniqueName);
        }
    }

    public PersonageDto getByUniqueName(String name, User user){
        Personage personage = personageRepository.findByUniqueName(name).orElseThrow(PersonageNotFoundException::new);
        return PersonageDto.builder()
                .name(personage.getName())
                .uniqueName(personage.getUniqueName())
                .info(personage.getInfo())
                .icon(personage.getIconUrl())
                .isOwner(personage.getUser().equals(user))
                .build();
    }
    public Slice<PersonageDto> getAllByAsk(String askUniqueName, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return convertToDto(personageRepository.findByAskUniqueNameAndIsActiveTrue(askUniqueName, pageable));
    }
    public void delete(String personageUniqueName){
        Personage personage = personageRepository.findByUniqueName(personageUniqueName).orElseThrow(PersonageNotFoundException::new);
        personageRepository.safeDelete(personage.getId());
    }

    public void edit(PersonageCreateRequest request){
        Personage personage = personageRepository.findByUniqueName(request.getUniqueName()).orElseThrow(PersonageNotFoundException::new);
        String icon;
        if (request.getIcon() == null || request.getIcon().isEmpty()){
            icon = personage.getIconUrl();
        } else {
            icon = fileStorageService.saveImage(request.getIcon(), FormType.PERSONAGE);
        }
        personageRepository.update(personage.getId(), request.getName(), request.getInfo(), icon);
    }

    private Slice<PersonageDto> convertToDto(Slice<Personage> personages){
        return personages.map(
                personage -> PersonageDto.builder()
                        .name(personage.getName())
                        .uniqueName(personage.getUniqueName())
                        .icon(personage.getIconUrl())
                        .build()
        );
    }
}
