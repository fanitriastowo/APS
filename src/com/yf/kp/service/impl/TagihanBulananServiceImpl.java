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

import com.yf.kp.model.TagihanBulanan;
import com.yf.kp.service.TagihanBulananService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class TagihanBulananServiceImpl extends AbstractServiceImpl<TagihanBulanan> implements TagihanBulananService {

    public TagihanBulananServiceImpl() {
        super(TagihanBulanan.class);
    }

    @Override
    public void saveBatch(TagihanBulanan tagihanBulanan, List<String> listNamaSiswa) {
        connect();
        try {
            manager().persist(tagihanBulanan);
            if (listNamaSiswa.size() % 20 == 0) {
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
    public List<TagihanBulanan> findAllByNis(String nis) {
        List<TagihanBulanan> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TB FROM TagihanBulanan TB WHERE TB.nis = :nis");
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
    public List<TagihanBulanan> findAllByNama(String nama) {
        List<TagihanBulanan> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TB FROM TagihanBulanan TB WHERE TB.nama LIKE :nama");
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
    public TagihanBulanan findOneByNisAndNamaTagihan(String nis, String namaTagihan) {
        TagihanBulanan bulanan = new TagihanBulanan();
        connect();
        try {
            Query q = manager().createQuery("SELECT TB FROM TagihanBulanan TB WHERE TB.nis =:nis AND TB.namaTagihan =:namaTagihan");
            q.setParameter("nis", nis);
            q.setParameter("namaTagihan", namaTagihan);
            bulanan = (TagihanBulanan) q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally {
            close();
        }
        return bulanan;
    }

    @Override
    public List<TagihanBulanan> findAllByNamaTagihanAndKelas(String namaTagihan, String kelas) {
        List<TagihanBulanan> list = new ArrayList<>();
        connect();
        try {
            Query q = manager().createQuery("SELECT TB FROM TagihanBulanan TB WHERE TB.namaTagihan = :namaTagihan AND TB.kelas = :kelas");
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
