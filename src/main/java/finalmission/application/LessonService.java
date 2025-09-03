package finalmission.application;

import finalmission.domain.lesson.entity.Lesson;
import finalmission.domain.lesson.model.Name;
import finalmission.domain.lesson.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LessonService {

    private final LessonRepository repository;

    @Transactional(readOnly = true)
    public Lesson findLesson(final String value) {
        final Name name = new Name(value);

        return repository.findByName(name);
    }
}
