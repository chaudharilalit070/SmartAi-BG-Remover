//package in.rahul.bgremove.service.impl;
//
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.entity.UserEntity;
//import in.rahul.bgremove.repository.UserRepository;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//   private final UserRepository userRepository;
//
//    @Override
//    public UserDTO saveUser(UserDTO userDTO) {
//
//     Optional <UserEntity> optionalUser= userRepository.findByClerkId(userDTO.getClerkId());
//     if (optionalUser.isPresent()){
//         UserEntity existingUser=optionalUser.get();
//         existingUser.setEmail(userDTO.getEmail());
//         existingUser.setFirstName(userDTO.getFirstName());
//         existingUser.setLastName(userDTO.getLastName());
//         existingUser.setLastName(userDTO.getPhotoUrl());
//         if (userDTO.getClerkId()!=null){
//             existingUser.setCredits(userDTO.getCredits());
//         }
//         existingUser = userRepository.save(existingUser);
//         return mapToDTO(existingUser);
//     }
// UserEntity newUser=mapToEntity(userDTO);
//userRepository.save(newUser) ;
//
//     return mapToDTO(newUser);
//
//
//    }
//
//    @Override
//    public UserDTO getUserByClerkId(String clerkId) {
//        UserEntity userEntity= userRepository.findByClerkId(clerkId)
//                .orElseThrow(()->new UsernameNotFoundException("User not found"));
//
//        return  mapToDTO(userEntity);
//    }
//
//    private UserDTO mapToDTO(UserEntity newUser) {
//            return   UserDTO.builder()
//                      .clerkId(newUser.getClerkId())
//                      .credits(newUser.getCredits())
//                      .email(newUser.getEmail())
//                      .firstName(newUser.getFirstName())
//                      .lastName(newUser.getLastName())
//                      .build();
//
//    }
//
//    private UserEntity mapToEntity(UserDTO userDTO) {
//                 return UserEntity.builder()
//            .clerkId(userDTO.getClerkId())
//            .email(userDTO.getEmail())
//            .firstName(userDTO.getFirstName())
//            .lastName(userDTO.getLastName())
//            .photoUrl(userDTO.getPhotoUrl())
//            .build();
//    }
//}
////5:41 min

package in.rahul.bgremove.service.impl;

import in.rahul.bgremove.dto.UserDTO;
import in.rahul.bgremove.entity.UserEntity;
import in.rahul.bgremove.repository.UserRepository;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByClerkId(userDTO.getClerkId());

        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setPhotoUrl(userDTO.getPhotoUrl()); // fixed: was incorrectly set using setLastName
            if (userDTO.getCredits() != null) {
                existingUser.setCredits(userDTO.getCredits());
            }
             existingUser = userRepository.save(existingUser);
            return mapToDTO(existingUser);
        }

        UserEntity newUser = mapToEntity(userDTO);
        userRepository.save(newUser);
        return mapToDTO(newUser);
    }

    @Override
    public UserDTO getUserByClerkId(String clerkId) {
        UserEntity userEntity = userRepository.findByClerkId(clerkId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return mapToDTO(userEntity);
    }

    @Override
    public void deleteUserByClerkId(String clerkId) {
      UserEntity userEntity=userRepository.findByClerkId(clerkId)
                .orElseThrow(()->new UsernameNotFoundException("user not Found"));
        userRepository.delete(userEntity);

    }

    private UserDTO mapToDTO(UserEntity user) {
        return UserDTO.builder()
                .clerkId(user.getClerkId())
                .credits(user.getCredits())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
//                .photoUrl(user.getPhotoUrl())
                .build();
    }

    private UserEntity mapToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .clerkId(userDTO.getClerkId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .photoUrl(userDTO.getPhotoUrl())
//                .credits(userDTO.getCredits())
                .build();
    }
}
