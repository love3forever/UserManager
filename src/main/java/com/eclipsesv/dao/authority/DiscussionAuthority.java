package com.eclipsesv.dao.authority;

import com.eclipsesv.dao.AbstractPublicDAO;
import com.eclipsesv.dao.DiscussionDAO;
import com.eclipsesv.model.*;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by eclipse on 16/9/18.
 */
@Repository("DiscussAuthDAO")
public class DiscussionAuthority extends AbstractPublicDAO implements IAuthorizationControl {
    @Override
    public Role getRole(String userID, String id) {
        if (userID != null && id != null) {
            Query discussQuery = getSession().createQuery("from DiscussionGroup where DISCUSSION_ID=:id");
            discussQuery.setParameter("id", id);
            DiscussionGroup discussionGroup = (DiscussionGroup) discussQuery.uniqueResult();
            if (discussionGroup != null) {
                // 判断该用户是否是该讨论组的创建者
                String ownID = discussionGroup.getOwnerID();
                if (Objects.equals(ownID, userID)) {
                    Query roleQuery = getSession().createQuery("from Role where ROLE_ID=:id");

                    roleQuery.setParameter("id", 1);
                    return (Role) roleQuery.uniqueResult();
                }

                String groupID = discussionGroup.getGroupID();
                if (groupID !=null) {
                    Query query= getSession().
                            createQuery("from UserGroup where GROUP_ID=:id and USER_ID=:userID");
                    query.setParameter("id", groupID);
                    query.setParameter("userID", userID);
                    UserGroup result = (UserGroup) query.uniqueResult();
                    if (result != null) {
                        Query roleQuery = getSession().createQuery("from Role where ROLE_ID=:id");
                        roleQuery.setParameter("id", result.getRole());
                        Role role = (Role) query.uniqueResult();
                        if (role != null) {
                            return role;
                        } else {
                            return null;
                        }
                    }
                    else
                        return null;
                }

            }
            return null;
        }
        return null;
    }

    @Override
    public List<Authority> getAuthority(String userID, String ID) {
        Role role = getRole(userID, ID);
        if (role != null) {
            List<Authority> result = new ArrayList<>();
            Query authorLinkQuery = getSession().createQuery("from AuthorityRoleLink where ROLE_ID=:id");
            authorLinkQuery.setParameter("id", role.getId());
            List authList = authorLinkQuery.list();
            for (Object auth:
                    authList) {
                AuthorityRoleLink arLink = (AuthorityRoleLink) auth;
                int authID = arLink.getAuthority_ID();
                Query authQuery = getSession().createQuery("from Authority where Authority_ID=:id");
                authQuery.setParameter("id", authID);
                Authority authority = (Authority) authQuery.uniqueResult();
                if (authority != null) {
                    result.add(authority);
                }
            }
            if (result.size() != 0) {
                return result;
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean isCreator(String userID, String id) {
        Query query = getSession().createQuery("from DiscussionGroup where DISCUSSION_ID=:id");
        query.setParameter("id", id);
        DiscussionGroup discussionGroup = (DiscussionGroup) query.uniqueResult();
        String groupID = discussionGroup.getGroupID();
        Query groupQuery = getSession().createQuery("from Groups where GROUP_ID=:id");
        groupQuery.setParameter("id", groupID);
        Groups group = (Groups) groupQuery.uniqueResult();
        return Objects.equals(group.getCreatorID(), userID);
    }

    @Override
    public boolean isOwner(String userID, String id) {
        Query query = getSession().createQuery("from DiscussionGroup where DISCUSSION_ID=:id");
        query.setParameter("id", id);
        DiscussionGroup discussionGroup = (DiscussionGroup) query.uniqueResult();
        return Objects.equals(userID, discussionGroup.getOwnerID());
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
