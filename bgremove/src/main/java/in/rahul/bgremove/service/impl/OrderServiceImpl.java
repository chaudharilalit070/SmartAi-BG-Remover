
package in.rahul.bgremove.service.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import in.rahul.bgremove.entity.OrderEntity;
import in.rahul.bgremove.repository.OrderRepository;
import in.rahul.bgremove.service.OrderService;
import in.rahul.bgremove.service.RazorpayService;
import in.rahul.bgremove.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final RazorpayService razorpayService;

    private final OrderRepository oderRepository;
    private static final Map<String, PlanDetails> PLAN_DETAILS = Map.of(
            "basic", new PlanDetails("Basic", 100, 499.00),
            "Premium", new PlanDetails("Premium", 250, 899.00),
            "Ultimate", new PlanDetails("Ultimate", 1000, 1499.00)
    );

    private record PlanDetails(String name, int credits, double amount) {
    }

    @Override
    public Order createOrder(String planId, String clerkId) throws RazorpayException {
        PlanDetails details = PLAN_DETAILS.get(planId);
        if (details == null) {
            throw new IllegalArgumentException("Invalide plne id" + planId);
        }
        try {
//            int amountInPaise = (int) (details.amount() * 100);
            Order razorpayOrder = razorpayService.createOrder(details.amount(), "INR");
            OrderEntity newOrder = OrderEntity.builder()
                    .clerkId(clerkId)
                    .plan(details.name())
                    .credits(details.credits())
                    .amount(details.amount())
                    .orderId(razorpayOrder.get("id"))
                    .build();
            oderRepository.save(newOrder);
            return razorpayOrder;
        } catch (RazorpayException e) {
            throw new RazorpayException("Error while creating the order  ", e);
        }
    }
}
