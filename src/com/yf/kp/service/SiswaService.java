package com.yf.kp.service;

import com.yf.kp.model.Siswa;
import java.util.List;

/**
 *
 * @author anonymous
 */
public interface SiswaService extends AbstractService<Siswa> {

    public List<Siswa> findByNis(String nis);

    public List<Siswa> findByNama(String nama);

    public List<Siswa> findAllByKelas(String kelas);

    public Siswa findOneByName(String nama);

    public void hapusSemua();

}
