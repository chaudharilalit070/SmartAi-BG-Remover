//package in.rahul.bgremove.Controller;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayException;
//import in.rahul.bgremove.dto.RazorpayOrderDTO;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/orders")
//@RequiredArgsConstructor
//public class OrderController {
//    private  final OrderService orderService;
//     @PostMapping
//    public Repository<?> createRazorpayOrder(@RequestParam String planId, Authentication authentication)
//throws RazorpayException {
//         Map <String ,Object> responseMap=new HashMap<>();
//         RemoveBgResponse response=null;
//
//         if (authentication == null || authentication.getName() == null || authentication.getName().isEmpty()) {
//             response = RemoveBgResponse.builder()
//                     .statCode(HttpStatus.FORBIDDEN)
//                     .success(false)
//                     .data("User does not have permission/access to this resource")
//                     .build();
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//
//             try{
//               Order order= orderService.createOdrer(planId,authentication.getName());
//            RazorpayOrderDTO reponseDTO= convertTODTO(order);
//          response=  RemoveBgResponse.builder()
//                    .success(true)
//                    .data(reponseDTO)
//                    .statCode(HttpStatus.CREATED)
//                    .build();
//            return ResponseEntity.ok(response);
//         } catch (Exception e) {
//                 response=  RemoveBgResponse.builder()
//                         .success(false)
//
//                         .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                         .data(e.getMessage())
//                         .build();
//                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//         }
//    }
//}
//
//    private RazorpayOrderDTO convertTODTO(Order order) {
//     return     RazorpayOrderDTO.builder()
//                 .id(order.get("id"))
//                 .entity(order.get("entity"))
//                 .amount(order.get("currency"))
//                 .status(order.get("Status"))
//                 .created_at(order.get("created_at"))
//                 .receipt(order.get("receipt"))
//                 .build();
//    }
//    }
//package in.rahul.bgremove.Controller;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayException;
//import in.rahul.bgremove.dto.RazorpayOrderDTO;
//import in.rahul.bgremove.response.RemoveBgResponse;
//import in.rahul.bgremove.service.OrderService;
//import in.rahul.bgremove.service.RazorpayService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("/api/orders")
//@RequiredArgsConstructor
//public class OrderController {
//
//    private final OrderService orderService;
//    private final RazorpayService razorpayService;
//    @PostMapping
//    public ResponseEntity<?> createRazorpayOrder(@RequestParam String planId, Authentication authentication)
//            throws RazorpayException {
//
//        if (authentication == null || authentication.getName() == null || authentication.getName().isEmpty()) {
//            RemoveBgResponse response = RemoveBgResponse.builder()
//                    .statCode(HttpStatus.FORBIDDEN)
//                    .success(false)
//                    .data("User does not have permission/access to this resource")
//                    .build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//        }
//
//        try {
//            Order order = orderService.createOrder(planId, authentication.getName());
//            RazorpayOrderDTO responseDTO = convertToDTO(order);
//            RemoveBgResponse response = RemoveBgResponse.builder()
//                    .success(true)
//                    .data(responseDTO)
//                    .statCode(HttpStatus.CREATED)
//                    .build();
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } catch (Exception e) {
//            RemoveBgResponse response = RemoveBgResponse.builder()
//                    .success(false)
//                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .data(e.getMessage())
//                    .build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    private RazorpayOrderDTO convertToDTO(Order order) {
//        return RazorpayOrderDTO.builder()
//                .id(order.get("id"))
//                .entity(order.get("entity"))
//                .amount(order.get("amount"))
//                .currency(order.get("currency"))
//                .status(order.get("status"))
//                .created_at(order.get("created_at"))
//                .receipt(order.get("receipt"))
//                .build();
//    }
//
//@PostMapping("/verify")
//    public ResponseEntity<?> verifyOrder(RequestBody Map<String,Object> request) throws  RazorpayException{
//                 try {
//                     String razorpayOrder = request.get("razorpay_order_id").toString();
//                     Map<String, Object> returnvalue = razorpayServices.verifyPayment(razorpayOrderId);
//                    return ResponseEntity.ok(returnvalue);
//                 } catch (RazorpayException e) {
//                     Map<String,Object> errorResponse=new HashMap<>();
//                     errorResponse.put("success",false);
//                     errorResponse.put("message",e.getMessage());
//                     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err)
//
//
//                     throw new RuntimeException(e);
//                 }
//    }
//
//
//
//
//
//}




package in.rahul.bgremove.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import in.rahul.bgremove.dto.RazorpayOrderDTO;
import in.rahul.bgremove.response.RemoveBgResponse;
import in.rahul.bgremove.service.OrderService;
import in.rahul.bgremove.service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final RazorpayService razorpayService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestParam String planId, Authentication authentication)
            throws RazorpayException {

               Map<String,Object> responseMap=new HashMap<>();
        RemoveBgResponse response=null;
        if (authentication == null || authentication.getName() == null || authentication.getName().isEmpty()) {
             response = RemoveBgResponse.builder()
                    .statCode(HttpStatus.FORBIDDEN)
                    .success(false)
                    .data("User does not have permission/access to this resource")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        try {
            Order order = orderService.createOrder(planId, authentication.getName());
            RazorpayOrderDTO responseDTO = convertToDTO(order);
            response = RemoveBgResponse.builder()
                    .success(true)
                    .data(responseDTO)
                    .statCode(HttpStatus.CREATED)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
             response = RemoveBgResponse.builder()
                    .success(false)
                    .statCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .data(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private RazorpayOrderDTO convertToDTO(Order order) {
        return RazorpayOrderDTO.builder()
                .id(order.get("id"))
                .entity(order.get("entity"))
                .amount(order.get("amount"))
                .currency(order.get("currency"))
                .status(order.get("status"))
                .created_at(order.get("created_at"))
                .receipt(order.get("receipt"))
                .build();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOrder(@RequestBody Map<String, Object> request) {
        try {
            String razorpayOrderId = request.get("razorpay_order_id").toString();
            Map<String, Object> returnValue = razorpayService.verifyPayment(razorpayOrderId);
            return ResponseEntity.ok(returnValue);
        } catch (RazorpayException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}


