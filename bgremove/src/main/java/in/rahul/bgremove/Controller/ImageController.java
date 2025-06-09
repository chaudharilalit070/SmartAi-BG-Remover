////package in.rahul.bgremove.Controller;
////
////import in.rahul.bgremove.dto.UserDTO;
////import in.rahul.bgremove.response.RemoveBgResponse;
////import in.rahul.bgremove.service.RemoveBackgroundService;
////import in.rahul.bgremove.service.UserService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.MediaType;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.core.Authentication;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.RestController;
////import org.springframework.web.multipart.MultipartFile;
////
////import java.util.Base64;
////import java.util.HashMap;
////import java.util.Map;
////
////@RequiredArgsConstructor
////@RestController
////@RequestMapping("/api/images")
////public class ImageController {
////      private final RemoveBackgroundService removeBackgroundService;
////      private  final UserService userService;
////
////     @PostMapping("/remove-background")
////      public ResponseEntity<?> removeBackground(@RequestParam("file")MultipartFile file,
////                                                Authentication authentication){
////
////         RemoveBgResponse response=null;
////         Map<String,Object> responseMap=new HashMap<>();
////           //validation
////         try{
////             if (authentication.getName().isEmpty()||authentication.getName()==null) {
////                response= RemoveBgResponse.builder()
////                         .statCode(HttpStatus.FORBIDDEN)
////                         .success(false)
////                         .data("User does not have permission/access to this resources")
////                         .build();
////                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
////             }
////             //validation
////            UserDTO userDTO= userService.getUserByClerkId(authentication.getName());
////             if (userDTO.getClerkId() == 0) {
////                 responseMap.put("message","NO credit balance");
////                 responseMap.put("creditBalance",userDTO.getCredits());
////               response=  RemoveBgResponse.builder()
////                         .success(false)
////                         .data(responseMap)
////                         .statCode(HttpStatus.BAD_REQUEST)
////                         .build();
////                 return  ResponseEntity.ok(response);
////
////             }
////          byte[] imagebytes=removeBackgroundService.removeBackground(file);
////            String base64Image= Base64.getEncoder().encodeToString(imagebytes);
////            userDTO.setCredits(userDTO.getCredits()-1);
////            userService.saveUser(userDTO);
////            return ResponseEntity.ok()
////                    .contentType(MediaType.TEXT_PLAIN)
////                    .body(base64Image);
////         } catch (Exception e) {
////                response=RemoveBgResponse.builder()
////                        .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
////                        .success(false)
////                        .data("something went wrong")
////                        .build();
////                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
////         }
////
////      }
////
////}
//package in.rahul.bgremove.Controller;
//
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.RemoveBackgroundService;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/images")
//public class ImageController {
//
//    private final RemoveBackgroundService removeBackgroundService;
//    private final UserService userService;
//
//    @PostMapping("/remove-background")
//    public ResponseEntity<?> removeBackground(@RequestParam("file") MultipartFile file,
//                                              Authentication authentication) {
//
//        RemoveBgResponse response = null;
//        Map<String, Object> responseMap = new HashMap<>();
//
//        try {
//            if (authentication == null || authentication.getName() == null || authentication.getName().isEmpty()) {
//                response = RemoveBgResponse.builder()
//                        .statCode(HttpStatus.FORBIDDEN)
//                        .success(false)
//                        .data("User does not have permission/access to this resources")
//                        .build();
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//            }
//
//            UserDTO userDTO = userService.getUserByClerkId(authentication.getName());
//
//            if (userDTO.getCredits() == 0) {
//                responseMap.put("message", "NO credit balance");
//                responseMap.put("creditBalance", userDTO.getCredits());
//                response = RemoveBgResponse.builder()
//                        .success(false)
//                        .data(responseMap)
//                        .statCode(HttpStatus.BAD_REQUEST)
//                        .build();
//                return ResponseEntity.ok(response);
//            }
//
//            byte[] imageBytes = removeBackgroundService.removeBackground(file);
//            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//            userDTO.setCredits(userDTO.getCredits() - 1);
//            userService.saveUser(userDTO);
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.TEXT_PLAIN)
//                    .body(base64Image);
//
//        } catch (Exception e) {
//            response = RemoveBgResponse.builder()
//                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .success(false)
//                    .data("something went wrong")
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//}
package in.rahul.bgremove.Controller;

import in.rahul.bgremove.dto.UserDTO;
import in.rahul.bgremove.response.RemoveBgResponse;
import in.rahul.bgremove.service.RemoveBackgroundService;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final RemoveBackgroundService removeBackgroundService;
    private final UserService userService;

    @PostMapping("/remove-background")
    public ResponseEntity<?> removeBackground(@RequestParam("file") MultipartFile file,
                                              Authentication authentication) {

        RemoveBgResponse response = null;
        Map<String, Object> responseMap = new HashMap<>();

        try {
            if (authentication.getName() == null || authentication.getName().isEmpty()) {
                response = RemoveBgResponse.builder()
                        .statCode(HttpStatus.FORBIDDEN)
                        .success(false)
                        .data("User does not have permission/access to this resource")
                        .build();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            UserDTO userDTO = userService.getUserByClerkId(authentication.getName());

            if (userDTO.getCredits() == 0) {
                responseMap.put("message", "NO credit balance");
                responseMap.put("creditBalance", userDTO.getCredits());
                response = RemoveBgResponse.builder()
                        .success(false)
                        .data(responseMap)
                        .statCode(HttpStatus.BAD_REQUEST)
                        .build();
                return ResponseEntity.ok(response);
            }

            byte[] imageBytes = removeBackgroundService.removeBackground(file);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            userDTO.setCredits(userDTO.getCredits() - 1);
            userService.saveUser(userDTO);

            return ResponseEntity.ok()
                     .contentType(MediaType.TEXT_PLAIN)
                    .body(base64Image);

        } catch (Exception e) {
            e.printStackTrace();
            response = RemoveBgResponse.builder()
                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .success(false)
                    .data("Something went wrong")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
