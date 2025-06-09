//package in.rahul.bgremove.Controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.entity.UserEntity;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/webhooks")
//@RequiredArgsConstructor
//public class ClerkWebhookController {
//     @Value("${clerk.webhook.secret}")
//    private  String webhookSecret;
//     private final UserService userService;
//     @PostMapping("/clerk")
//     public ResponseEntity<?> handleClerkWebhook(@RequestHeader("svix-id") String svixId,
//                                                 @RequestHeader("svix-timestmap") String svixTimestmap,
//                                                 @RequestHeader("svix-singature") String svixSingature,
//                                                 @RequestBody String payload){
//         RemoveBgResponse response=null;
//try {
//    boolean isValid = verifyWebhookSignature(svixId, svixSingature, svixTimestmap, payload);
//    if (!isValid) {
//         response = RemoveBgResponse.builder()
//                .statCode(HttpStatus.UNAUTHORIZED)
//                .data("Invalide webhook signtrue")
//                .success(false)
//                .build();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
//    ObjectMapper objectMapper = new ObjectMapper();
//    JsonNode rootNode = objectMapper.readTree(payload);
//
//    String eventType = rootNode.path("type").asText();
//    switch (eventType) {
//        case "user.created":
//            handleUserCreated(rootNode.path("data"));
//            break;
//        case "user.updated":
//            handleUserUpdtaed(rootNode.path("data"));
//            break;
//        case "user.deleted":
//            handleUserDeleted(rootNode.path("data"));
//            break;
//    }
//    return  ResponseEntity.ok().build();
//}
//catch (Exception e){
//    response = RemoveBgResponse.builder()
//            .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//            .data("Something went Wrong")
//            .success(false)
//            .build();
//
//    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//            .body(response);
//
//}
//
//     }
//
//    private void handleUserDeleted(JsonNode data) {
//
////     TODO: add the implementation later
//     }
//
//    private void handleUserUpdtaed(JsonNode data) {
//         String clerkId=data.path("id").asText();
//        UserDTO exisingtUser=userService.getUserByClerkId(clerkId);
//        exisingtUser.setEmail(data.path("email_addresses").path(0).path("email_address").asText());
//        exisingtUser.setFirstName(data.path("first_name").asText());
//        exisingtUser.setLastName(data.path("last_name").asText());
//        exisingtUser.setPhotoUrl(data.path("image_url").asText());
//
//        userService.saveUser(exisingtUser);
//
//
//    }
//
//    private void handleUserCreated(JsonNode data) {
//       UserDTO userDTO= UserDTO.builder()
//                .clerkId(data.path("id").asText())
//                .email(data.path("email_addresses").path(0).path("email_address").asText())
//                .firstName(data.path("first_name").asText())
//                .lastName(data.path("last_name ").asText())
//                .build();
//       userService.saveUser(userDTO);
//
//    }
//
//    private boolean verifyWebhookSignature(String svixId, String svixSingature, String svixTimestmap, String payload) {
//   return  true;
//    }
//}


//package in.rahul.bgremove.Controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/webhooks")
//@RequiredArgsConstructor
//public class ClerkWebhookController {
//
//    @Value("${clerk.webhook.secret}")
//    private String webhookSecret;
//
//    private final UserService userService;
//
//    @PostMapping("/clerk")
//    public ResponseEntity<?> handleClerkWebhook(@RequestHeader("svix-id") String svixId,
//                                                @RequestHeader("svix-timestamp") String svixTimestamp,
//                                                @RequestHeader("svix-signature") String svixSignature,
//                                                @RequestBody String payload) {
//        RemoveBgResponse response = null;
//        try {
//            boolean isValid = verifyWebhookSignature(svixId, svixSignature, svixTimestamp, payload);
//            if (!isValid) {
//                response = RemoveBgResponse.builder()
//                        .statCode(HttpStatus.UNAUTHORIZED)
//                        .data("Invalid webhook signature")
//                        .success(false)
//                        .build();
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode rootNode = objectMapper.readTree(payload);
//
//            String eventType = rootNode.path("type").asText();
//            switch (eventType) {
//                case "user.created":
//                    handleUserCreated(rootNode.path("data"));
//                    break;
//                case "user.updated":
//                    handleUserUpdated(rootNode.path("data"));
//                    break;
//                case "user.deleted":
//                    handleUserDeleted(rootNode.path("data"));
//                    break;
//            }
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            response = RemoveBgResponse.builder()
//                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .data("Something went wrong")
//                    .success(false)
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    private void handleUserDeleted(JsonNode data) {
//        // TODO: add the implementation later
//    }
//
//    private void handleUserUpdated(JsonNode data) {
//        String clerkId = data.path("id").asText();
//        UserDTO existingUser = userService.getUserByClerkId(clerkId);
//        existingUser.setEmail(data.path("email_addresses").path(0).path("email_address").asText());
//        existingUser.setFirstName(data.path("first_name").asText());
//        existingUser.setLastName(data.path("last_name").asText());
//        existingUser.setPhotoUrl(data.path("image_url").asText());
//
//        userService.saveUser(existingUser);
//    }
//
//    private void handleUserCreated(JsonNode data) {
//        UserDTO userDTO = UserDTO.builder()
//                .clerkId(data.path("id").asText())
//                .email(data.path("email_addresses").path(0).path("email_address").asText())
//                .firstName(data.path("first_name").asText())
//                .lastName(data.path("last_name").asText())
//                .photoUrl(data.path("image_url").asText())
//                .build();
//
//        userService.saveUser(userDTO);
//    }
//
//    private boolean verifyWebhookSignature(String svixId, String svixSignature, String svixTimestamp, String payload) {
//        return true;
//    }
//}





package in.rahul.bgremove.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.rahul.bgremove.dto.UserDTO;
import in.rahul.bgremove.response.RemoveBgResponse;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class ClerkWebhookController {

    @Value("${clerk.webhook.secret}")
    private String webhookSecret;

    private final UserService userService;

    @PostMapping("/clerk")
    public ResponseEntity<?> handleClerkWebhook(
            @RequestHeader("svix-id") String svixId,
            @RequestHeader("svix-timestamp") String svixTimestamp,
            @RequestHeader("svix-signature") String svixSignature,
            @RequestBody String payload
    ) {
        RemoveBgResponse response;
        try {
            // (1) Verify signature (stubbed)
            boolean isValid = verifyWebhookSignature(svixId, svixSignature, svixTimestamp, payload);
            if (!isValid) {
                response = RemoveBgResponse.builder()
                        .statCode(HttpStatus.UNAUTHORIZED)
                        .data("Invalid webhook signature")
                        .success(false)
                        .build();
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // (2) Parse payload
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(payload);

            // (3) If payload is malformed, this might be null/empty—but we still attempt to read safely
            String eventType = rootNode.path("type").asText("");
            JsonNode eventData = rootNode.path("data");

            // Logging: see what we actually received
            System.out.println("[Webhook] Received type = " + eventType);
            System.out.println("[Webhook] Received data JSON = " + eventData.toString());

            // (4) Dispatch based on type
//            switch (eventType) {
//                case "user.created":
//                    handleUserCreated(eventData);
//                    break;
//                case "user.updated":
//                    handleUserUpdated(eventData);
//                    break;
//                case "user.deleted":
//                    handleUserDeleted(eventData);
//                    break;
//                default:
//                    // Unrecognized event type → do nothing
//                    break;
//            }



            switch (eventType) {
                case "user.created":
                    handleUserCreated(rootNode.path("data"));
                    break;
                case "user.updated":
                    handleUserUpdated(rootNode.path("data"));
                    break;
                case "user.deleted":
                    handleUserDeleted(rootNode.path("data"));
                    break;
                default:
                    // Unrecognized event type → do nothing
                    break;
            }























            // (5) Always return 200 OK (Clerk/​Svix expects this if we processed successfully)
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            // Print full stacktrace so we can see exactly what failed
            e.printStackTrace();

            response = RemoveBgResponse.builder()
                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data("Something went wrong")
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private void handleUserDeleted(JsonNode data) {
         String clerkId =data.path("id").asText();
         userService.deleteUserByClerkId(clerkId);
    }

    private void handleUserUpdated(JsonNode data) {
        if (data == null || data.isMissingNode()) {
            System.out.println("[Webhook] user.updated: 'data' is missing or null");
            return;
        }

        String clerkId = data.path("id").asText("");


        if (clerkId.isEmpty()) {
            System.out.println("[Webhook] user.updated: no 'id' field");
            return;
        }

        // (A) Fetch existing
        UserDTO existingUser = userService.getUserByClerkId(clerkId);
        if (existingUser == null) {
            System.out.println("[Webhook] user.updated: no existing user found for clerkId=" + clerkId);
            return;
        }

        // (B) Safely extract email_addresses[0].email_address (if present)
        JsonNode emailArray = data.path("email_addresses");
        String email = "";
        if (emailArray.isArray() && emailArray.size() > 0) {
            email = emailArray.get(0).path("email_address").asText("");
        }


//        existingUser.setEmail(email);

        existingUser.setEmail(data.path("email_addresses").path(0).path("email_address").asText(""));

        // (C) Safely extract first/last name and image_url
        existingUser.setFirstName(data.path("first_name").asText(""));
        existingUser.setLastName(data.path("last_name").asText(""));
        existingUser.setPhotoUrl(data.path("image_url").asText(""));

        // (D) Save
        userService.saveUser(existingUser);
    }

    private void handleUserCreated(JsonNode data) {
        if (data == null || data.isMissingNode()) {
            System.out.println("[Webhook] user.created: 'data' is missing or null");
            return;
        }

        String clerkId = data.path("id").asText("");
        if (clerkId.isEmpty()) {
            System.out.println("[Webhook] user.created: no 'id' field");
            return;
        }

        // Safely extract email_addresses[0].email_address
        JsonNode emailArray = data.path("email_addresses");
        String email = "";
        if (emailArray.isArray() && emailArray.size() > 0) {
            email = emailArray.get(0).path("email_address").asText("");
        }

//        UserDTO userDTO = UserDTO.builder()
//                .clerkId(clerkId)
//                .email(email)
//                .firstName(data.path("first_name").asText(""))
//                .lastName(data.path("last_name").asText(""))
//                .photoUrl(data.path("image_url").asText(""))
//                .build();


        UserDTO newUser = UserDTO.builder()
                .clerkId(data.path("id").asText(""))
                .email(data.path("email_addresses").path(0).path("email_address").asText(""))
                .firstName(data.path("first_name").asText(""))
                .lastName(data.path("last_name").asText(""))
                .photoUrl(data.path("image_url").asText(""))
                .build();




        userService.saveUser(newUser);
    }

    private boolean verifyWebhookSignature(String svixId, String svixSignature, String svixTimestamp, String payload) {
        // Signature verification is currently stubbed out.
        // Later you’ll plug in Svix/Clerk’s HMAC‐SHA256 verification logic here.
        return true;
    }
}
//6:2 min