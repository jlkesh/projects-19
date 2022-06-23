package uz.jl.back.vo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class Data<T> {
    private T body;
    private Integer total;
    private boolean ok;
    private ErrorVO error;

    public Data(T body) {
        this(body, null);
    }

    public Data(T body, Integer total) {
        this.body = body;
        this.total = total;
        this.ok = true;
    }

    public Data(ErrorVO error) {
        this.error = error;
        this.ok = false;
    }

    public static ErrorVO errorBuilder() {
        return new ErrorVO();
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorVO {
        private String friendlyMessage;
        private String developerMessage;
        private Integer code = 500;
        private Timestamp timestamp;

        public ErrorVO(String friendlyMessage, String developerMessage) {
            this.friendlyMessage = friendlyMessage;
            this.developerMessage = developerMessage;
            this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        }

        public ErrorVO(String friendlyMessage, String developerMessage, Integer code) {
            this(friendlyMessage, developerMessage);
            this.code = code;
        }

        public ErrorVO friendlyMessage(String friendlyMessage) {
            this.friendlyMessage = friendlyMessage;
            return this;
        }

        public ErrorVO developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorVO code(Integer code) {
            this.code = code;
            return this;
        }

        public ErrorVO build() {
            return new ErrorVO(this.friendlyMessage, this.developerMessage, this.code);
        }
    }


}
