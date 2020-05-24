package ts.project.clinic.service.login;

import org.springframework.security.core.userdetails.UserDetailsService;
import ts.project.clinic.model.entity.LoginData;

public interface LoginDataService extends UserDetailsService {
    LoginData findByEmail(String email);
}
