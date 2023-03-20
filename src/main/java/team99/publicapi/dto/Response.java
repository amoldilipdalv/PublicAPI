package team99.publicapi.dto;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
