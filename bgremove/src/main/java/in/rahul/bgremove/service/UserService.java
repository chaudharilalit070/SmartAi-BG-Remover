package in.rahul.bgremove.service;

import in.rahul.bgremove.dto.UserDTO;

public interface UserService {

UserDTO saveUser(UserDTO userDTO);

    UserDTO getUserByClerkId(String clerkId);
    void deleteUserByClerkId(String clerkId);
}
