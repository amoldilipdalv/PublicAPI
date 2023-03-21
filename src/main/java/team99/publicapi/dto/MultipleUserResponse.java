package team99.publicapi.dto;

import org.springframework.stereotype.Component;
import team99.publicapi.domain.User;


import java.util.Collections;
import java.util.List;
@Component
public class MultipleUserResponse extends Response{
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public MultipleUserResponse setMultipleUserResponse(List<User> userList)
    {
        MultipleUserResponse multipleUserResponse = new MultipleUserResponse();

        if(null != userList && !userList.isEmpty())
        {
            multipleUserResponse.setResult(true);
            multipleUserResponse.setUsers(userList);
        }
        else
        {
            multipleUserResponse.setResult(false);
            multipleUserResponse.setUsers(Collections.EMPTY_LIST);
        }
        return multipleUserResponse;
    }
}
