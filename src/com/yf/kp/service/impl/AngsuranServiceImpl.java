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

import com.yf.kp.model.Angsuran;
import com.yf.kp.service.AngsuranService;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class AngsuranServiceImpl extends AbstractServiceImpl<Angsuran> implements AngsuranService {

    public AngsuranServiceImpl() {
        super(Angsuran.class);
    }

    @Override
    public Angsuran findOneByName(String nama) {
        Angsuran angsuran = new Angsuran();
        connect();
        try {
            Query q = manager().createQuery("SELECT A FROM Angsuran A WHERE A.nama = :kode");
            q.setParameter("kode", nama);
            angsuran = (Angsuran) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return angsuran;
    }

}
