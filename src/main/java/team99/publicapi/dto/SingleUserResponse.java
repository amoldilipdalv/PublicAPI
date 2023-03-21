package team99.publicapi.dto;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.User;


import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.List;

@Component
public class SingleUserResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SingleUserResponse setSingleUserResponse(User user)
    {
        SingleUserResponse singleUserResponse = new SingleUserResponse();

        if(null != user)
        {
            singleUserResponse.setUser(user);
        }
        else
        {
            singleUserResponse.setUser(new User());
        }
        return singleUserResponse;
    }
}
