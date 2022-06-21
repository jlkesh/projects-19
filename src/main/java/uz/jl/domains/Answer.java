package uz.jl.domains;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Answer {
    private long id;
    private String answerBody;
    private String questionId;
    private boolean correct;
}
