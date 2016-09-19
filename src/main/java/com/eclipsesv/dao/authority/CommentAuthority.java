package com.eclipsesv.dao.authority;

import com.eclipsesv.dao.AbstractPublicDAO;
import com.eclipsesv.model.Authority;
import com.eclipsesv.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Created by eclipse on 16/9/18.
 */
@Repository("CommentAuthDAO")
public class CommentAuthority extends AbstractPublicDAO implements IAuthorizationControl {
    @Override
    public Role getRole(String userID, String id) {
        return null;
    }

    @Override
    public List<Authority> getAuthority(String userID, String ID) {
        return null;
    }

    @Override
    public boolean isCreator(String userID, String id) {
        return false;
    }

    @Override
    public boolean isOwner(String userID, String id) {
        return false;
    }

    @Override
    public boolean canEdit(String userID, String id) {
        List<Authority> authorities = getAuthority(userID, id);
        return authConfirm("编辑", authorities);
    }

    @Override
    public boolean canDelete(String userID, String id) {
        List<Authority> authorities = getAuthority(userID, id);
        return authConfirm("删除", authorities);
    }

    @Override
    public boolean canRead(String userID, String id) {
        List<Authority> authorities = getAuthority(userID, id);
        return authConfirm("读取", authorities);
    }

    @Override
    public boolean canCreat(String userID, String id) {
        List<Authority> authorities = getAuthority(userID, id);
        return authConfirm("创建", authorities);
    }

    private boolean authConfirm(String kindofAuth,List<Authority> authorities) {
        for (Authority auth:
                authorities) {
            if (Objects.equals(auth.getDesc(), kindofAuth)) {
                return true;
            }
        }
        return false;
    }
}
