package warehouseManagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import warehouseManagement.constant.SystemConstant;
import warehouseManagement.dto.MyUser;
import warehouseManagement.entity.RoleEntity;
import warehouseManagement.entity.UserEntity;
import warehouseManagement.repository.UserRepository;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
      //Lấy role từ bảng role
      		List<GrantedAuthority> authorities = new ArrayList<>();
      		for (RoleEntity role: userEntity.getRoles()) {
      			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
      		}
      		
      		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),userEntity.getId(), 
      						true, true, true, true, authorities);
      		//Custom lại User để lất dc FullName
      		myUser.setFullName(userEntity.getFullName());
      		myUser.setId(userEntity.getId());
      		return myUser;
    }
}
