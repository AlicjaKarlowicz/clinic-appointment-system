package ts.project.clinic.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ts.project.clinic.exception.InvalidNameOrPasswordException;
import ts.project.clinic.model.entity.LoginData;
import ts.project.clinic.repository.LoginDataRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginService implements LoginDataService {

    @Autowired
    private LoginDataRepository loginDataRepository;


    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginData findByEmail(String email){
        return loginDataRepository.findByEmail(email);
    }

    public void save(LoginData loginData) {
        loginData.setPassword(bCryptPasswordEncoder.encode(loginData.getPassword()));
        loginData.setRole("ROLE_PATIENT");
        loginDataRepository.save(loginData);
    }

    public void isRegistered(String email) {
        LoginData user = loginDataRepository.findByEmail(email);
        if (user != null) {
            throw new InvalidNameOrPasswordException("Somebody with that email is already registered.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws InvalidNameOrPasswordException {

        LoginData user = loginDataRepository.findByEmail(email);

        if (user == null){
            throw new InvalidNameOrPasswordException("Invalid email or password.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);

    }








}
