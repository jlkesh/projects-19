package uz.jl.back.domains;


import lombok.*;
import uz.jl.back.enums.Language;
import uz.jl.back.enums.Level;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Quiz {
    private long id;
    private String userId;
    private String subjectId;
    private Level level;
    private Language language;
    private int count;
    private boolean completed;
}
