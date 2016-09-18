package com.eclipsesv.authority;

import com.eclipsesv.model.Authority;
import com.eclipsesv.model.Role;

/**
 * Created by eclipse on 16/9/18.
 */
public class CommentAuthority implements IAuthorizationControl {
    @Override
    public Role getRole(String userID, String id) {
        return null;
    }

    @Override
    public Authority getAuthority(String userID, String ID) {
        return null;
    }
}
