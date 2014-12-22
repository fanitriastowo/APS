package com.yf.kp.service;

import com.yf.kp.model.Kelas;

/**
 *
 * @author anonymous
 */
public interface KelasService extends AbstractService<Kelas> {

    public Kelas findOneByName(String toString);

}
