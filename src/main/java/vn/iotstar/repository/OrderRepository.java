package vn.iotstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.iotstar.entity.Order;
import vn.iotstar.entity.StatusOrder;
import vn.iotstar.entity.User;

public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByStatusOrder(StatusOrder statusOrder);
	Optional<Order> findById(String id);

	List<Order> findByUser(User user);
	
	List<Order> findByUpdateat(String updateat);
 }
