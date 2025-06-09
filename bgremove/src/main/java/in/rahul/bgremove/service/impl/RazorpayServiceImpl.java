//package in.rahul.bgremove.service.impl;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.entity.OrderEntity;
//import in.rahul.bgremove.repository.OrderRepository;
//import in.rahul.bgremove.service.RazorpayService;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class RazorpayServiceImpl implements RazorpayService {
//
//    @Value("${razorpay.key.id}")
//    private String razorpayKeyId;
//    @Value("${razorpay.key.Secret}")
//
//    private String razorpayKeySecret;
//    private  final OrderRepository orderRepository;
//    private final UserService userService;
//    @Override
//    public Order createOrder(Double amount, String currency) throws RazorpayException {
//        try {
//            RazorpayClient razorpayClient=new RazorpayClient(razorpayKeyId,razorpayKeySecret);
//            JSONObject orderRequest= new JSONObject();
//            orderRequest.put("amount",amount*100);
//            orderRequest.put("currency",currency);
//            orderRequest.put("receipt","order_rcptid_"+System.currentTimeMillis());
//            orderRequest.put("payment_capture",1);
//            return razorpayClient.orders.create(orderRequest);
//
//        } catch (RazorpayException e) {
//                 e.printStackTrace();
//                 throw new RazorpayException("Razorpay error"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Map<String, Object> verifyPayment(String razorpayOrderId) throws RazorpayException {
//        Map<String,Object> returnvalue=new HashMap<>();
//     try
//     {
//         RazorpayClient razorpayClient=new RazorpayClient(razorpayKeyId,razorpayKeySecret);
//         Order orderInfo =RazorpayClient,orders.fetch(razorpayOrderId);
//         if (orderInfo.get("status").toString().equalsIgnoreCase("paid")) {
//
//        OrderEntity exitingOrder= orderRepository.findByOrderId(razorpayOrderId)
//                .orElseThrow(()->new RuntimeException("order not found "+razorpayOrderId));
//
//             if (exitingOrder.getPayment()) {
//                 returnvalue.put("message","payment  failed");
//                 return  returnvalue;
//
//             }
//           UserDTO userDTO= userService.getUserByClerkId(exitingOrder.getClerkId());
//            userDTO.setCredits(userDTO.getCredits()+exitingOrder.getCredits());
//            userService.saveUser(userDTO);
//            exitingOrder.setPayment(true);
//            orderRepository.save(exitingOrder);
//returnvalue.put("message","Credits Added");
//return  returnvalue;
//         }
//
//     } catch (RazorpayException e) {
//         throw new RuntimeException(e);
//         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error  while verfiling the payment ");
//     }
//
//    }
//    package in.rahul.bgremove.service.impl;
//
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import in.rahul.bgremove.dto.UserDTO;
//import in.rahul.bgremove.entity.OrderEntity;
//import in.rahul.bgremove.repository.OrderRepository;
//import in.rahul.bgremove.service.RazorpayService;
//import in.rahul.bgremove.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.http.HttpStatus;
//
//import java.util.HashMap;
//import java.util.Map;
//
//    @Service
//    @RequiredArgsConstructor
//    public class RazorpayServiceImpl implements RazorpayService {
//
//        @Value("${razorpay.key.id}")
//        private String razorpayKeyId;
//
//        @Value("${razorpay.key.secret}")
//        private String razorpayKeySecret;
//
//        private final OrderRepository orderRepository;
//        private final UserService userService;
//
//        @Override
//        public Order createOrder(Double amount, String currency) throws RazorpayException {
//            try {
//                RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
//                JSONObject orderRequest = new JSONObject();
//                orderRequest.put("amount", (int)(amount * 100)); // Convert to smallest currency unit
//                orderRequest.put("currency", currency);
//                orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());
//                orderRequest.put("payment_capture", 1);
//                return razorpayClient.orders.create(orderRequest);
//            } catch (RazorpayException e) {
//                e.printStackTrace();
//                throw new RazorpayException("Razorpay error: " + e.getMessage());
//            }
//        }
//
//        @Override
//        public Map<String, Object> verifyPayment(String razorpayOrderId) throws RazorpayException {
//            Map<String, Object> returnValue = new HashMap<>();
//            try {
//                RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
//                Order orderInfo = razorpayClient.orders.fetch(razorpayOrderId);
//                if ("paid".equalsIgnoreCase(orderInfo.get("status").toString())) {
//                    OrderEntity existingOrder = orderRepository.findByOrderId(razorpayOrderId)
//                            .orElseThrow(() -> new RuntimeException("Order not found: " + razorpayOrderId));
//
//                    if (existingOrder.getPayment()) {
//                        returnValue.put("message", "Payment already processed");
//                        return returnValue;
//                    }
//
//                    UserDTO userDTO = userService.getUserByClerkId(existingOrder.getClerkId());
//                    userDTO.setCredits(userDTO.getCredits() + existingOrder.getCredits());
//                    userService.saveUser(userDTO);
//
//                    existingOrder.setPayment(true);
//                    orderRepository.save(existingOrder);
//
//                    returnValue.put("message", "Credits Added");
//                    return returnValue;
//                } else {
//                    returnValue.put("message", "Payment not completed");
//                    return returnValue;
//                }
//            } catch (RazorpayException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while verifying the payment", e);
//            }
//        }
//    }
//
//}
package in.rahul.bgremove.service.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import in.rahul.bgremove.dto.UserDTO;
import in.rahul.bgremove.entity.OrderEntity;
import in.rahul.bgremove.repository.OrderRepository;
import in.rahul.bgremove.service.RazorpayService;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RazorpayServiceImpl implements RazorpayService {

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order createOrder(Double amount, String currency) throws RazorpayException {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (amount * 100)); // Convert to smallest currency unit
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());
            orderRequest.put("payment_capture", 1);
            return razorpayClient.orders.create(orderRequest);
        } catch (RazorpayException e) {
            e.printStackTrace();
            throw new RazorpayException("Razorpay error: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> verifyPayment(String razorpayOrderId) throws RazorpayException {
        Map<String, Object> returnValue = new HashMap<>();
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            Order orderInfo = razorpayClient.orders.fetch(razorpayOrderId);


            if (orderInfo.get("status").toString().equalsIgnoreCase("paid")){
                OrderEntity existingOrder = orderRepository.findByOrderId(razorpayOrderId)
                        .orElseThrow(() -> new RuntimeException("Order not found: " + razorpayOrderId));

                if (existingOrder.getPayment()) {
                    returnValue.put("success",false);

                    returnValue.put("message", "Payment already processed");
                    return returnValue;
                }

                UserDTO userDTO = userService.getUserByClerkId(existingOrder.getClerkId());
                userDTO.setCredits(userDTO.getCredits() + existingOrder.getCredits());
                userService.saveUser(userDTO);

                existingOrder.setPayment(true);
                orderRepository.save(existingOrder);
                returnValue.put("success",true);

                returnValue.put("message", "Credits Added");
                return returnValue;
            } else {
                returnValue.put("message", "Payment not completed");
                return returnValue;
            }
        } catch (RazorpayException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while verifying the payment", e);
        }
    }
}
//8:15 min