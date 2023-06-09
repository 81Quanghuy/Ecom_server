package vn.iotstar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.ERole;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CartRepository;
import vn.iotstar.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	CartRepository cartRepository;

	public <S extends User> S save(S entity) {
		
		return userRepo.save(entity);
	}

	public <S extends User> S insert(S entity) {
		return userRepo.insert(entity);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(String id) {
		return userRepo.findById(id).get();
	}

	public long count() {
		return userRepo.count();
	}
	

	public List<User> findByIsActive(Boolean isActive) {
		return userRepo.findByIsActive(isActive);
	}

	public void deleteById(String id) {
		userRepo.deleteById(id);
	}

	public void delete(User entity) {
		userRepo.delete(entity);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}

	public User updateUser(User userRequest) {
		// TODO Auto-generated method stub
		// get the existing document from DB
		// populate new value from request to existing object/entity/document
		User existingUser = userRepo.findById(userRequest.getId()).get();
		existingUser.setAddress(userRequest.getAddress());
		existingUser.setAvatar(userRequest.getAvatar());

//		existingUser.setCreateat(userRequest.getCreateat());

		existingUser.setEmail(userRequest.getEmail());
		existingUser.setFullName(userRequest.getFullName());
		existingUser.setIsActive(userRequest.getIsActive());
		existingUser.setPassword(userRequest.getPassword());
		existingUser.setPhone(userRequest.getPhone());
		existingUser.setUsername(userRequest.getUsername());

//		existingUser.setUpdateat(userRequest.getUpdateat());
		


		return userRepo.save(existingUser);
	}
	public User getUserByUsername(String username, String password) {
		return userRepo.getUserByUsernameAndPassword(username, password);
	}

	public User createUser(String username, String password) {
		User entity = new User();

		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setAddress("TP. Hồ Chí Minh");
		entity.setAvatar("https://ecomserver.up.railway.app/images/IT.jpg");
//		entity.setCreateat(currentDate);
//		entity.setUpdateat(currentDate);

		entity.setId(UUID.randomUUID().toString().split("-")[0]);
		entity.setAddress("TP. Hồ Chí Minh");
		entity.setAvatar("https://ecomserver.up.railway.app/images/IT.jpg");
		entity.setEmail("email@gmail.com");
		entity.setFullName("Nguyễn Văn A");
		entity.setIsActive(true);
		entity.setRole(ERole.ROLE_USER.toString());
		entity.setPhone("0352602596");
		entity.setResetpasswordtoken(password);
		entity.setUsername(username);
		entity.setPassword(password);

	
		
		return userRepo.save(entity);
	}

	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public boolean authenticate(String username, String password) {
		List<User> users = userRepo.findByUsername(username);
        User user = users.get(0);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    public boolean changePassword(String username, String newPassword) {
    	List<User> users = userRepo.findByUsername(username);
        User user = users.get(0);
        if (user != null) {
            user.setPassword(newPassword);
            userRepo.save(user);
            return true;
        }
        return false;
    }
    
    public boolean changePasswordByEmail(String mail, String newPassword) {
    	User user = userRepo.findByEmail(mail);
        if (user != null) {
            user.setPassword(newPassword);
            userRepo.save(user);
            return true;
        }
        return false;
    }
    
    public boolean isEmailExists(String email) {
        User user = userRepo.findByEmail(email);
        return user != null;
    }
    
    public void saveOTP(String email, String otp) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            user.setOtp(otp);
            userRepo.save(user);
        }
    }
    
    public boolean isValidOTP(String email, String otp) {
        User user = userRepo.findByEmail(email);
        return user != null && user.getOtp().equals(otp);
    }
    
    
    
	
}
