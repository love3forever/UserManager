package com.eclipsesv.authority;

import com.eclipsesv.model.Authority;
import com.eclipsesv.model.Role;
import com.eclipsesv.model.User;

/**
 * Created by eclipse on 16/9/18.
 */
public interface IAuthorizationControl {
    Role getRole(String userID, String id);

    Authority getAuthority(String userID, String ID);
}
