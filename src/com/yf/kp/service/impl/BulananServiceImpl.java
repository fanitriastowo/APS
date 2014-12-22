/*
 * Copyright (C) 2014 anonymous
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

import com.yf.kp.model.Bulanan;
import com.yf.kp.service.BulananService;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class BulananServiceImpl extends AbstractServiceImpl<Bulanan> implements BulananService {

    public BulananServiceImpl() {
        super(Bulanan.class);
    }

    @Override
    public Bulanan findOneByName(String nama) {
        Bulanan bulanan = new Bulanan();
        connect();
        try {
            Query q = manager().createQuery("SELECT B FROM Bulanan B WHERE B.nama = :nama");
            q.setParameter("nama", nama);
            bulanan = (Bulanan) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return bulanan;
    }

}
