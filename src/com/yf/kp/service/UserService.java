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
package com.yf.kp.service;

import com.yf.kp.model.User;

/**
 *
 * @author khasdul
 */
public interface UserService extends AbstractService<User> {
    
    public void updateAdmin(Long id, String password);

    public User login(String username, String password, Boolean asAdmin);
    
    public void changePassword(String oldPwd, String newPwd);
}
