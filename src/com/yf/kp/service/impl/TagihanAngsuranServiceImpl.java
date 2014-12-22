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

import com.yf.kp.model.TagihanAngsuran;
import com.yf.kp.service.TagihanAngsuranService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class TagihanAngsuranServiceImpl extends AbstractServiceImpl<TagihanAngsuran> implements TagihanAngsuranService {

    public TagihanAngsuranServiceImpl() {
        super(TagihanAngsuran.class);
    }

    @Override
    public void saveBatch(TagihanAngsuran tagihanAngsuran, List<String> listSiswa) {
        connect();
        try {
            manager().persist(tagihanAngsuran);
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
    public List<TagihanAngsuran> findAllByNis(String nis) {
        List<TagihanAngsuran> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TA FROM TagihanAngsuran TA WHERE TA.nis = :nis");
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
    public List<TagihanAngsuran> findAllByNama(String nama) {
        List<TagihanAngsuran> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TA FROM TagihanAngsuran TA WHERE TA.nama LIKE :nama");
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
    public TagihanAngsuran findOneByNisAndNamaTagihan(String nis, String namaTagihan) {
        TagihanAngsuran angsuran = new TagihanAngsuran();
        connect();
        try {
            Query q = manager().createQuery("SELECT TA FROM TagihanAngsuran TA WHERE TA.nis =:nis AND TA.namaTagihan =:namaTagihan");
            q.setParameter("nis", nis);
            q.setParameter("namaTagihan", namaTagihan);
            angsuran = (TagihanAngsuran) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return angsuran;
    }

    @Override
    public List<TagihanAngsuran> findAllByNamaTagihanAndKelas(String namaTagihan, String kelas) {
        List<TagihanAngsuran> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TA FROM TagihanAngsuran TA WHERE TA.namaTagihan = :namaTagihan AND TA.kelas = :kelas");
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
