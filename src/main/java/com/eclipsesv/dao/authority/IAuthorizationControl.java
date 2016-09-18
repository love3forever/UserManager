package com.eclipsesv.dao.authority;

import com.eclipsesv.model.Authority;
import com.eclipsesv.model.Role;

import java.util.List;

/**
 * Created by eclipse on 16/9/18.
 */
public interface IAuthorizationControl {
    Role getRole(String userID, String id);

    List<Authority> getAuthority(String userID, String ID);

    boolean isCreator(String userID, String id);

    boolean isOwner(String userID, String id);

    boolean canEdit(String userID, String id);

    boolean canDelete(String userID, String id);

    boolean canRead(String userID, String id);

    boolean canCreat(String userID, String id);
}
