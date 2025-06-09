//////package in.rahul.bgremove.Controller;
//////
//////import in.rahul.bgremove.dto.UserDTO;
//////import in.rahul.bgremove.response.RemoveBgResponse;
//////import in.rahul.bgremove.service.UserService;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.http.HttpStatus;
//////import org.springframework.security.core.Authentication;
//////import org.springframework.web.bind.annotation.*;
//////
//////@RestController
//////@RequestMapping("/api/users")
//////@RequiredArgsConstructor
//////public class UserController {
//////     @Autowired
//////    private  final UserService userService;
//////     @PostMapping
//////     public RemoveBgResponse createOrUpadteUser(@RequestBody UserDTO userDTO , Authentication authentication){
//////
//////       try {
//////           if (! authentication.getName().equals(userDTO.getClerkId())) {
//////              return RemoveBgResponse.builder()
//////                       .success(false)
//////                       .data("user does not have permission to access the resources")
//////                       .statCode(HttpStatus.FORBIDDEN)
//////                       .build();
//////           }
//////           UserDTO user =userService.saveUser(userDTO);
//////
//////           return  RemoveBgResponse.builder()
//////                   .success(true)
//////                   .data(user)
//////                   .statCode(HttpStatus.OK)
//////                   .build();
//////
//////       } catch (Exception e) {
//////           return  RemoveBgResponse.builder()
//////                   .success(false)
//////                   .data(e.getMessage())
//////                   .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//////                   .build();
//////
//////
//////     }
//////
//////     }
//////}
////////4:5
////
////
////package in.rahul.bgremove.Controller;
////
////import in.rahul.bgremove.dto.UserDTO;
////import in.rahul.bgremove.response.RemoveBgResponse;
////import in.rahul.bgremove.service.UserService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.core.Authentication;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/api/users")
////@RequiredArgsConstructor
////public class UserController {
////
////    @Autowired
////    private final UserService userService;
////
////    @PostMapping
////    public ResponseEntity<RemoveBgResponse> createOrUpadteUser(@RequestBody UserDTO userDTO, Authentication authentication) {
////        try {
////            if (!authentication.getName().equals(userDTO.getClerkId())) {
////                return new ResponseEntity<>(
////                        RemoveBgResponse.builder()
////                                .success(false)
////                                .data("User does not have permission to access the resources")
////                                .statCode(HttpStatus.FORBIDDEN)
////                                .build(),
////                        HttpStatus.FORBIDDEN
////                );
////            }
////
////            UserDTO user = userService.saveUser(userDTO);
////
////            return new ResponseEntity<>(
////                    RemoveBgResponse.builder()
////                            .success(true)
////                            .data(user)
////                            .statCode(HttpStatus.OK)
////                            .build(),
////                    HttpStatus.OK
////            );
////
////        } catch (Exception e) {
////            return new ResponseEntity<>(
////                    RemoveBgResponse.builder()
////                            .success(false)
////                            .data(e.getMessage())
////                            .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
////                            .build(),
////                    HttpStatus.INTERNAL_SERVER_ERROR
////            );
////        }
////    }
////}
//package in.rahul.bgremove.Controller;
//
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService; // removed @Autowired in favor of constructor injection
//
//    @PostMapping
//    public ResponseEntity<RemoveBgResponse> createOrUpadteUser(@RequestBody UserDTO userDTO, Authentication authentication) {
//        try {
//            if (!authentication.getName().equals(userDTO.getClerkId())) {
//                return new ResponseEntity<>(
//                        RemoveBgResponse.builder()
//                                .success(false)
//                                .data("User does not have permission to access the resources")
//                                .statCode(HttpStatus.FORBIDDEN)
//                                .build(),
//                        HttpStatus.FORBIDDEN
//                );
//            }
//
//            UserDTO user = userService.saveUser(userDTO);
//
//            return new ResponseEntity<>(
//                    RemoveBgResponse.builder()
//                            .success(true)
//                            .data(user)
//                            .statCode(HttpStatus.OK)
//                            .build(),
//                    HttpStatus.OK
//            );
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(
//                    RemoveBgResponse.builder()
//                            .success(false)
//                            .data(e.getMessage())
//                            .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                            .build(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//
//    @GetMapping("/credits")
//    public ResponseEntity<?> getUserCredits(Authentication authentication){
//       RemoveBgResponse bgResponse=null;
//
//        try {
//
//    if ( authentication.getName().isEmpty() || authentication.getName() ==null ) {
//       bgResponse=RemoveBgResponse.builder()
//                .statusCode(HttpStatus.FORBIDDEN)
//                .data("User does not have permission/access to this resources")
//                .success(false);
//
//    return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(bgResponse);
//    }
//
//    String clerkId=authentication.getName();
//    UserDTO exitingUser=userService.getUserByClerkId(clerkId);
//            Map<String,Integer> map=new HashMap<>();
//            map.put("credits",exitingUser.getCredits());
//            bgResponse=RemoveBgResponse.builder()
//                    .statusCode(HttpStatus.OK)
//                    .data(map)
//                    .success(true);
//                    .build();
//            return ResponseEntity.status(HttpStatus.OK).body(bgResponse);
//
//
//
//} catch (Exception e) {
//            bgResponse=RemoveBgResponse.builder()
//                    .statusCode(HttpStatus.OK)
//                    .data("something went wrong")
//                    .success(false);
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bgResponse);
//
//}
//    }
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//





package in.rahul.bgremove.Controller;

import in.rahul.bgremove.dto.UserDTO;
import in.rahul.bgremove.response.RemoveBgResponse;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RemoveBgResponse> createOrUpadteUser(@RequestBody UserDTO userDTO, Authentication authentication) {
        try {
            if (!authentication.getName().equals(userDTO.getClerkId())) {
                return new ResponseEntity<>(
                        RemoveBgResponse.builder()
                                .success(false)
                                .data("User does not have permission to access the resources")
                                .statCode(HttpStatus.FORBIDDEN)
                                .build(),
                        HttpStatus.FORBIDDEN
                );
            }

            UserDTO user = userService.saveUser(userDTO);

            return new ResponseEntity<>(
                    RemoveBgResponse.builder()
                            .success(true)
                            .data(user)
                            .statCode(HttpStatus.OK)
                            .build(),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    RemoveBgResponse.builder()
                            .success(false)
                            .data(e.getMessage())
                            .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/credits")
    public ResponseEntity<?> getUserCredits(Authentication authentication) {
        RemoveBgResponse bgResponse;

        try {
            if (authentication.getName() == null || authentication.getName().isEmpty()) {
                bgResponse = RemoveBgResponse.builder()
                        .statCode(HttpStatus.FORBIDDEN)
                        .data("User does not have permission/access to this resources")
                        .success(false)
                        .build();

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(bgResponse);
            }

            String clerkId = authentication.getName();
            UserDTO existingUser = userService.getUserByClerkId(clerkId);

            Map<String, Integer> map = new HashMap<>();
            map.put("credits", existingUser.getCredits());

            bgResponse = RemoveBgResponse.builder()
                    .statCode(HttpStatus.OK)
                    .data(map)
                     .success(true)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(bgResponse);

        } catch (Exception e) {
            bgResponse = RemoveBgResponse.builder()
                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data("something went wrong")
                    .success(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bgResponse);
        }
    }
}
//6:9 min