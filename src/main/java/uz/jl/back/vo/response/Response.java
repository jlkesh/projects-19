package uz.jl.back.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Response<B> {
    private B data;
}
