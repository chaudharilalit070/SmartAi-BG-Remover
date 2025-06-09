package in.rahul.bgremove.repository;

import in.rahul.bgremove.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    Optional<OrderEntity> findByOrderId(String OrderID);


}
