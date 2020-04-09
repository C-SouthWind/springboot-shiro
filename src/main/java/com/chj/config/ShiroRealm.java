package com.chj.config;

import com.chj.model.User;
import com.chj.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：chj
 * @date ：Created in 2020/4/6 17:38
 * @params :
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("This is 认证");

        String username = (String)authenticationToken.getPrincipal();
        System.out.println("shiroRealm username---------"+username);
        User user = userService.selectByName(username);
        if (null == user) {
            return null;
        }
        ByteSource bytes = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),bytes, this.getName());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("This is 授权");
        return null;
    }


}
