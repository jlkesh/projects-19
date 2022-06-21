package uz.jl.domains;

import lombok.*;
import uz.jl.enums.Language;
import uz.jl.enums.Level;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Question {
    private long id;
    private String questionBody;
    private Language language;
    private Level level;
    private String subjectId;
}
