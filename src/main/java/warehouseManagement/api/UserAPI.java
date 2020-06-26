package warehouseManagement.api;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import warehouseManagement.config.TokenProvider;
import warehouseManagement.dto.AuthToken;
import warehouseManagement.dto.MyUser;
import warehouseManagement.dto.UserDTO;

@RestController
public class UserAPI {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    @Qualifier("userCustomService")
    @Autowired
    private UserDetailsService userDetailsService;
    @CrossOrigin(origins = "http://localhost:2020")
    @PostMapping("/auth/token")
    public ResponseEntity<?> register(@NotNull @RequestBody UserDTO userDTO) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUserName(),
                        userDTO.getPassWord()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    @PostMapping("a/api/user/test")
    public MyUser myuser(@RequestBody UserDTO userDTO)  {
    	MyUser a=(MyUser) userDetailsService.loadUserByUsername(userDTO.getUserName());
        return a;
    }
}

