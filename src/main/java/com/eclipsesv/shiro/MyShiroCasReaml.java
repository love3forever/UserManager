package com.eclipsesv.shiro;

import com.eclipsesv.dao.UserDAO;
import com.eclipsesv.model.User;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eclipse on 16/9/1.
 */
public class MyShiroCasReaml extends CasRealm{
    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasReaml.class);

    @Autowired
    private UserDAO userDao;

    @PostConstruct
    public void initProperty(){
//      setDefaultRoles("ROLE_USER");
        setCasServerUrlPrefix(ShiroConfiguration.casServerUrlPerfix);
        // 客户端回调地址
        setCasService(ShiroConfiguration.shiroServerUrlPerfix + ShiroConfiguration.casFilterUrlPattern);
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);
        System.out.println(loginName);
        //到数据库查是否有此对象
        User user=userDao.findByName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            info.setRoles(user.getRolesName());
            return info;
        }
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        roles.add("manager");
        roles.add("normal");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;
    }


}
