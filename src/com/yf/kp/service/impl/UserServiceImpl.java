/*
 * Copyright (C) 2014 khasdul
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.yf.kp.service.impl;

import com.yf.kp.model.User;
import com.yf.kp.service.UserService;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author khasdul
 */
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

    public UserServiceImpl() {
        super(User.class);
    }

    @Override
    public User login(String username, String password, Boolean asAdmin) {
        User user = new User();
        connect();
        try {
            Query q = manager().createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password AND u.asAdmin =:asAdmin");
            q.setParameter("username", username);
            q.setParameter("password", password);
            q.setParameter("asAdmin", asAdmin);
            user = (User) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "And tidak memiliki hak akses");
            rollback();
        } finally {
            close();
        }
        return user;
    }

    @Override
    public void changePassword(String oldPwd, String newPwd) {
        connect();
        try {
            Query q = manager().createQuery("SELECT u FROM User u WHERE u.password = :oldPassword");
            q.setParameter("oldPassword", oldPwd);
            User user = (User) q.uniqueResult();
            commit();
            if (user.getPassword().equals(newPwd)) {
                updateAdmin(user.getId(), newPwd);
            } else {
                JOptionPane.showMessageDialog(null, "Password Anda tidak valid");
            }
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
    }

    @Override
    public void updateAdmin(Long id, String password) {
        connect();
        try {
            Query q = manager().createQuery("UPDATE User AS U SET U.password =:password WHERE U.id = :id");
            q.setParameter("id", id);
            q.setParameter("password", password);
            q.executeUpdate();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
    }

    
}
