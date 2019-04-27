/**
 * 3/6/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.security;


import ir.aliloc.api.core.user.IUserService;
import ir.aliloc.api.core.user.models.UserPassDTO;
import ir.aliloc.api.core.util.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService mUserService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserPassDTO userD = null;
        try {
            userD = mUserService.getMainObjectById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userD == null) {
            throw new UsernameNotFoundException(String.format("No user found with mobile '%s'.", id));
        }
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(GlobalService.userRoles.get(1)));
        List<GrantedAuthority> authorities = new ArrayList<>(setAuths);
        return new User(String.valueOf(userD.getId()), userD.getMobile(), authorities);
    }
}
