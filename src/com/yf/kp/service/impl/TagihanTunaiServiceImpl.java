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

import com.yf.kp.model.TagihanTunai;
import com.yf.kp.service.TagihanTunaiService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class TagihanTunaiServiceImpl extends AbstractServiceImpl<TagihanTunai> implements TagihanTunaiService {

    public TagihanTunaiServiceImpl() {
        super(TagihanTunai.class);
    }

    @Override
    public void saveBatch(TagihanTunai tagihanTunai, List<String> listSiswa) {
        connect();
        try {
            manager().persist(tagihanTunai);
            if (listSiswa.size() % 20 == 0) {
                manager().flush();
                manager().clear();
            }
            commit();
        } catch (HibernateException ex) {
            rollback();
            throw ex;
        } finally {
            close();
        }
    }

    @Override
    public List<TagihanTunai> findAllByNis(String nis) {
        List<TagihanTunai> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TT FROM TagihanTunai TT WHERE TT.nis = :nis");
            q.setParameter("nis", nis);
            list = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<TagihanTunai> findAllByNama(String nama) {
        List<TagihanTunai> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TT FROM TagihanTunai TT WHERE TT.nama LIKE :nama");
            q.setParameter("nama", "%" + nama + "%");
            list = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public TagihanTunai findOneByNisAndNamaTagihan(String nis, String namaTagihan) {
        TagihanTunai tunai = new TagihanTunai();
        connect();
        try {
            Query q = manager().createQuery("SELECT TT FROM TagihanTunai TT WHERE TT.nis =:nis AND TT.namaTagihan =:namaTagihan");
            q.setParameter("nis", nis);
            q.setParameter("namaTagihan", namaTagihan);
            tunai = (TagihanTunai) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return tunai;
    }

    @Override
    public List<TagihanTunai> findAllByNamaTagihanAndKelas(String namaTagihan, String kelas) {
        List<TagihanTunai> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TT FROM TagihanTunai TT WHERE TT.namaTagihan = :namaTagihan AND TT.kelas = :kelas");
            q.setParameter("namaTagihan", namaTagihan);
            q.setParameter("kelas", kelas);
            list = q.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return list;
    }

}
